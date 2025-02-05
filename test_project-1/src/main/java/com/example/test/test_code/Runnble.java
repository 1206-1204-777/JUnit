package com.example.test.test_code;

public class Runnble implements Runnable {
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}
