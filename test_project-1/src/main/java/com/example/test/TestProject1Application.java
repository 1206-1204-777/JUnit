package com.example.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.test.test_code.Test_Code;



@SpringBootApplication
public class TestProject1Application {

	public static void main(String[] args) {

		Test_Code async = new Test_Code();
		async.start();
		
		System.out.println("test");
	}

}
