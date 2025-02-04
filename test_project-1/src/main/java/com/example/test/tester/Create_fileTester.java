package com.example.test.tester;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        Files.createDirectories(Paths.get(TEST_FOLDER)); // テスト用フォルダを作成
        createFolder = new CreateFolder();
        createFolder.folderWriter(TEST_FOLDER); // `folderPath` の初期化を確実に実行
        executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> createFolder.watchFolder());
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

        // `test2` フォルダー作成完了まで最大50秒待機
        for (int i = 0; i < 10; i++) {
            if (Files.exists(path2)) {
                break;
            }
            Thread.sleep(1000);
        }

        assertTrue(Files.exists(path), "テストファイルが存在しない");
        assertTrue(Files.exists(path2), "監視によるフォルダ 'test2' が作成されていない");
    }
}
