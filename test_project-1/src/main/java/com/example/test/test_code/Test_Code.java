package com.example.test.test_code;

public class Test_Code extends Thread{
	public int add(int x, int y) {
		return x + y;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);//２秒後に実行
			System.out.println("test");
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}

}
