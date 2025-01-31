package com.example.JUnit.JUnit_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.JUnit.TestCode.JUnit_test;
import com.example.JUnit.TestCode.Mockito;
import com.example.JUnit.TestCode.Order;
import com.example.JUnit.item.Item;

public class JUnit_tester {
	private Order order;
	private Item item1;
	private Item item2;

	@BeforeEach
	void setItem() {
		order = new Order();
		item1 = new Item("laptop");
		item2 = new Item("mouse");
	}

	@Test
	void testAdd() {
		JUnit_test jUnit_test = new JUnit_test();
		int result = jUnit_test.add(7, 9);
		assertEquals(16, result);
	}

	@Test
	void test() {
		Mockito mockito = new Mockito();
		int result = mockito.firstNum(10, 20);
		assertEquals(30, result);
	}

	@Test
	void addItems() {
		order.addItem(item1);//item1を追加
		order.addItem(item2);
		List<Item> items = order.getItems();

		assertEquals(2, items.size(),"Order should contain 2 items.");//itemの数が一致しているか
		assertTrue(items.contains(item1));//item名があっているか
		assertTrue(items.contains(item2));
	}
	@Test
	void removeItem() {
		order.addItem(item1);
		order.addItem(item2);
		order.remove(item1);
		
		List<Item> items = order.getItems();
		assertEquals(1,items.size());
		assertTrue(items.contains(item2));
	}

}
