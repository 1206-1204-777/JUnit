package com.example.test.test_code.createFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

public class CreateFaile {
	private Path folderPath;
	private Path newFolderPath;
	private AtomicBoolean running = new AtomicBoolean();

	//フォルダー作成メソッド
	public void FolderCreate(String folderName) {
		this.folderPath = Paths.get(folderName);
		this.newFolderPath = folderPath.resolve("test2");//作成するフォルダー名を指定
		try {	
			if (!Files.exists(folderPath)) {
				Files.createDirectories(folderPath);
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	public void stop() {
		running.set(false);
	}
}
