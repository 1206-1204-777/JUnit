package com.example.JUnit.TestCode;

import java.util.ArrayList;
import java.util.List;

import com.example.JUnit.item.Item;

public class Order {
	private List<Item> items;

	public Order() {
		this.items = new ArrayList<>();
	}

	public void addItem(Item item) {
		//itemを追加
		items.add(item);
	}

	//itemの削除
	public void remove(Item item) {
		items.remove(item);
	}

	//最初のitemを表示
	public Item getTotalItems() {
		return items.get(0);
	}

	//listの変更を外部からできないようにする
	public List<Item> getItems() {
		return new ArrayList<>(items);
	}
}
