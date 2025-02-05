package com.example.test.tester;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.test.test_code.CreateFolder;

public class Create_fileTester {
    private static final String TEST_FOLDER = "test";
    private static final String TEST_FILE = "test/test.txt"; // 作成するファイルの名前とパス
    private static final String TEST_FOLDER2 = "test/test2"; // 監視後に作成されるフォルダ
    private CreateFolder createFolder;
    private ExecutorService executor;

    @BeforeEach
    public void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_FOLDER));
        createFolder = new CreateFolder();
        createFolder.folderWriter(TEST_FOLDER);
        
        executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> createFolder.watchFolder());
        
        try {
            future.get(); // 例外が発生した場合、ここでキャッチされる
        } catch (Exception e) {
            throw new RuntimeException("watchFolder() 内で例外発生", e);
        }
    }


    @AfterEach
    public void tearDown() throws IOException {
        createFolder.stopCreatFolder(); // スレッド停止
        executor.shutdown(); // Executorをシャットダウン

        // テスト用フォルダーの削除
        Files.walk(Paths.get(TEST_FOLDER))
             .sorted((a, b) -> b.compareTo(a)) // 下の階層から削除
             .forEach(path -> {
                 try {
                     Files.deleteIfExists(path);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             });
    }

    @Test
    void testCreate() throws IOException, InterruptedException {
        Path path = Paths.get(TEST_FILE);
        Path path2 = Paths.get(TEST_FOLDER2);

        // テストフォルダー内にファイルを作成
        Files.createFile(path);
        Files.exists(path2);
        // `test2` フォルダー作成完了まで最大50秒待機


        assertTrue(Files.exists(path), "テストファイルが存在しない");
        assertTrue(Files.exists(path2), "監視によるフォルダ 'test2' が作成されていない");
    }
}
