package com.example.test.tester;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import com.example.test.test_code.TestCode;

public class Tester {
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	PrintStream originalOut = System.out;
	TestCode test = new TestCode();
	@Test
	void addTest() throws InterruptedException {
		int result = test.add(10, 20);
		assertEquals(30, result);
		
		//出力内容を取得
		System.setOut(new PrintStream(outputStream));
		
		test.start();
		test.join();
		
		System.setOut(originalOut);
		String ouput = outputStream.toString().trim();
		assertEquals(ouput,"test");
		
		

	}
}

