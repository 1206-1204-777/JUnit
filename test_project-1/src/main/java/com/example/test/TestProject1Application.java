package com.example.test;

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
		
		
	}

}
