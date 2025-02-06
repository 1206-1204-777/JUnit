package com.example.test;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.test.test_code.Runnble;
import com.example.test.test_code.TestCode;

@SpringBootApplication
public class TestProject1Application {
	
	public static void main(String[] args) {

		Thread thread = new Thread(new Runnble());
		TestCode test = new TestCode();
		test.start();
		thread.start();

		System.out.println("通常処理：" + test.add(20, 20));
		
		Runnable runnable = () -> System.out.println("Hello, world!");
		runnable.run();
		
		TestCode num = (new TestCode());
		System.out.println(num.add(100, 200));


		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			return "Hello!Future!";
		});
		

		try {
			Thread.sleep(5000);
			future.thenAccept(System.out::println);//結果の表示
		} catch (InterruptedException e) {
		}

	}
}