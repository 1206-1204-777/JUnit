package com.example.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.test.test_code.Test_Code;

@SpringBootApplication
public class TestProject1Application {

	public static void main(String[] args) {

		Test_Code test = new Test_Code();
		test.start();

		System.out.println(test.add(10, 20));
	}

}
