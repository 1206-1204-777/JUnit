package com.example.test.test_code;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.atomic.AtomicBoolean;

public class CreateFolder {
	private Path folderPath;
	private Path newFolderPath;
	private final AtomicBoolean running = new AtomicBoolean(true);

	public void foldeWriter(String folderName) {
		this.folderPath = Paths.get(folderName);
		this.newFolderPath = folderPath.resolve("test2");

		try {
			if (!Files.exists(folderPath)) {
				Files.createDirectories(folderPath);
			}
			// **追記: フォルダの作成パスを表示**
			System.out.println("フォルダ作成: " + folderPath.toAbsolutePath());
		} catch (IOException e) {
			throw new RuntimeException("フォルダ作成エラー: " + e.getMessage());
		}
	}

	public void stopCreatFolder() {
		running.set(false);
	}

	public void watchFolder() {
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			
			// **追記: 監視対象のフォルダを表示**
			System.out.println("フォルダーの監視を開始: " + folderPath.toAbsolutePath());

			while (running.get()) {
				WatchKey key = watchService.poll();
				if (key == null) {
					Thread.sleep(500); // CPU負荷軽減
					continue;
				}
				for (WatchEvent<?> event : key.pollEvents()) {
					Path create = folderPath.resolve((Path) event.context());
					if (create.toString().endsWith(".txt") || create.toString().endsWith(".java")) {
						if (!Files.exists(newFolderPath)) {
							Files.createDirectories(newFolderPath);
							// **追記: test2 の作成パスを表示**
							System.out.println("フォルダ 'test2' 作成: " + newFolderPath.toAbsolutePath());
						}
					}
				}
				key.reset();
			}
		} catch (IOException | InterruptedException e) {
			System.err.println("エラー: " + e.getMessage());
		}
	}

	public void stopWatching() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
