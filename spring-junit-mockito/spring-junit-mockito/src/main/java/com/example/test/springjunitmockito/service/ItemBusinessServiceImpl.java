package com.example.test.springjunitmockito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.test.springjunitmockito.model.Item;
import com.example.test.springjunitmockito.repository.ItemRepository;

@Component
public class ItemBusinessServiceImpl implements ItemBusinessService {

	@Autowired
	private ItemRepository itemRepository;
	
	public Item retrieveHardcodedItem() {
		return new Item(1, "Ball", 10, 100);
	}
	
	public List<Item> retrieveAllItems(){
		List<Item> items =  itemRepository.findAll();
		
		for(Item item : items) {
			item.setValue(item.getPrice() * item.getQuantity());
		}
		
		return items;
	}

	@Override
	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item updateItem(Item item, int id) {
		
		return itemRepository.findById(id).map(
			i -> {
				i.setName(item.getName());
				i.setPrice(item.getPrice());
				i.setQuantity(item.getQuantity());
				return itemRepository.save(i);
			})
		.orElseGet(() -> {
			item.setId(id);
			return itemRepository.save(item);
		});
	}
}
