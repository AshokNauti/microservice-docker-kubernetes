package com.example.test.springjunitmockito.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.test.springjunitmockito.model.Item;

@Component
public interface ItemBusinessService {
	
	public Item retrieveHardcodedItem();
	
	public List<Item> retrieveAllItems();
	
	public Item createItem(Item item);
	
	public Item updateItem(Item item, int id);
}
