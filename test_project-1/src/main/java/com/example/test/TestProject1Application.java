package com.example.test;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.test.test_code.Runnble;
import com.example.test.test_code.Test_Code;

@SpringBootApplication
public class TestProject1Application {

	public static void main(String[] args) {

		Thread thread = new Thread(new Runnble());
		Test_Code test = new Test_Code();
		test.start();
		thread.start();
		
		System.out.println("通常処理："+test.add(20, 20));
		
		CompletableFuture.supplyAsync(() -> {
			return "Hello";
		}).thenAccept(result -> {
			System.out.println(result);
		});
		
		CompletableFuture.supplyAsync(() -> fetchData())
		.thenCompose(data -> processdata(data))
		.thenAccept(result1 -> System.out.println("result"));
		
		
		}
	//データの取得
	static String fetchData() {
		return "date";
	}
	//データの加工	
	static CompletableFuture<String> processdata(String data){
		return CompletableFuture.supplyAsync(() -> data);
	}

}
