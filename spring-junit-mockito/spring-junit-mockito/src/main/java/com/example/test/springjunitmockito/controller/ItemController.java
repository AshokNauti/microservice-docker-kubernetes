package com.example.test.springjunitmockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.springjunitmockito.model.Item;
import com.example.test.springjunitmockito.service.ItemBusinessService;

@RestController
public class ItemController {
	
	@Autowired
	ItemBusinessService itemBusinessService;

	@GetMapping("/dummy-item")
	public Item dummyItem() {
		return new Item(1, "Ball", 10, 100);
	}
	
	@GetMapping("/item-from-business-service")
	public Item itemFromBusinessService() {
		return itemBusinessService.retrieveHardcodedItem();
	}
	
	@GetMapping("/all-items-from-database")
	public List<Item> allItemFromDatabase() {
		return itemBusinessService.retrieveAllItems();
	}
	
	@PostMapping("/items")
	public Item createItem(@RequestBody Item item) {
		return itemBusinessService.createItem(item);
	}
	
	@PutMapping("/items/{id}")
	public Item createItem(@RequestBody Item item, @PathVariable int id) {
		return itemBusinessService.updateItem(item, id);
	}
}
