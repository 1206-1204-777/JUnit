package com.example.JUnit.JUnit_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.JUnit.TestCode.JUnit_test;
import com.example.JUnit.TestCode.Mockito;

public class JUnit_tester {
	@Test
	void testAdd() {
		JUnit_test jUnit_test = new JUnit_test();
		int result = jUnit_test.add(7, 9);
		assertEquals(16, result);
	}
	@Test
	void test() {
		Mockito mockito = new Mockito();
		int result = mockito.firstNum(10,20);
		assertEquals(30,result);
	}

}
