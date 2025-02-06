package com.example.test.test_code;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

@Service
public class Futures{
	private final ExecutorService executor = Executors.newFixedThreadPool(10);

	public CompletableFuture<String> fetchData() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(6000);
				return "Hello";
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		},executor).exceptionally(ex -> ex.getMessage());
	}
}
