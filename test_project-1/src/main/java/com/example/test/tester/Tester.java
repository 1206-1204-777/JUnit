package com.example.test.tester;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;

import com.example.test.test_code.Futures;
import com.example.test.test_code.TestCode;

public class Tester {
	private final Futures futures = new Futures();
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	PrintStream originalOut = System.out;
	TestCode test = new TestCode();

	@Test
	void addTest() throws InterruptedException, ExecutionException {
		int result = test.add(10, 20);
		assertEquals(30, result);

		//出力内容を取得
		System.setOut(new PrintStream(outputStream));

		test.start();
		test.join();

		System.setOut(originalOut);
		String ouput = outputStream.toString().trim();
		assertEquals(ouput, "test");

	}

	@Test
	void Future() throws ExecutionException, InterruptedException {
		//処理の実行
		CompletableFuture<String> future = futures.fetchData();
		
		//結果の取得
		String result = future.get();
		//結果の検証
		assertEquals("Hello",result);

	}
	
	//例外処理用のテストコード
	@Test
	void Exception() {
	    Futures futures = new Futures() {
	        @Override
	        public CompletableFuture<String> fetchData() {
	            return CompletableFuture.failedFuture(new RuntimeException("error"));
	        }
	    };

	    CompletableFuture<String> future = futures.fetchData();

	    // 例外が適切に処理されるか
	    Throwable exception = assertThrows(CompletionException.class, future::join);
	    assertTrue(exception.getCause() instanceof RuntimeException);
	    assertEquals("error", exception.getCause().getMessage());
	}

}
