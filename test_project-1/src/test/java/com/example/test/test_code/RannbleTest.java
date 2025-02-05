package com.example.test.test_code;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class RannbleTest {

	@Test
	void test() throws InterruptedException{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PrintStream original = System.out;
		System.setOut(new PrintStream(output));
		try {
			
			Thread thread = new Thread(new Runnble());
			
			thread.start();
			thread.join();
			
			String originalOutput = output.toString().trim();
			assertFalse(originalOutput.isEmpty());
			
		}finally {
			System.setOut(original);
		}
	}

}
