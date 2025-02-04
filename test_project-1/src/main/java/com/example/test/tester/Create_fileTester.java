package com.example.test.tester;

import static org.junit.Assert.*;

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
	private final String TEST_FOLDER = "test";
	private final String TEST_FILE = "test/test.txt";//作成するファイルの名前とパスを指定
	private final String TEST_FOLDER2 = "test/test2";//ファイルの際と同じ動作
	private CreateFolder createFolder;
	private ExecutorService executor;//動作の非同期
	
	@BeforeEach
	//処理の開始
	public void setUp() throws IOException{
		Files.createDirectories(Paths.get(TEST_FOLDER));//フォルダーの作成
		createFolder = new CreateFolder();//フォルダーのインスタンスを作成
		executor = Executors.newSingleThreadExecutor();
		executor.submit(()-> createFolder.watchFolder());
	}
	@AfterEach
	//処理を完了
	public void testDown() throws IOException{
		Files.walk(Paths.get(TEST_FOLDER))
		.sorted((a,b) -> b.compareTo(a))//テスト用フォルダーの削除
		.forEach(path -> {
			try {
				Files.deleteIfExists(path);
			}catch(IOException e) {
				
			}
		});
	}
	
	@Test
	//ファイル作成をテスト
	void testCreate() throws IOException, InterruptedException{
		//testフォルダーの中にテキストファイルを作成
		Path path = Paths.get(TEST_FILE);
		//testフォルダー内にtest2フォルダーを作成
		Path path2 = Paths.get(TEST_FOLDER2);
		//フォルダー作成完了まで５秒待機
		for(int i = 0; i < 10; i++) {
			if(Files.exists(path2)) {
				break;
			}
			Thread.sleep(500);
		}
		assertTrue(Files.exists(path));
	}

}
