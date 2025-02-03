package com.example.test.tester;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import com.example.test.test_code.Test_Code;

public class Tester {
	Test_Code test = new Test_Code();

	@Test
	void addTest() {
		int result = test.add(10, 20);
		assertEquals(30, result);
	}
}
