package com.example.test.springjunitmockito.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.test.springjunitmockito.model.Item;
import com.example.test.springjunitmockito.repository.ItemRepository;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(ItemController.class)
public class ItemBusinessServiceImplTest {

	@InjectMocks
	private ItemBusinessServiceImpl itemBusinessServiceImpl;
	
	@Mock
	private ItemRepository itemRepository;
	
	@Test
	public void retrieveAllItemsTest() {
		
		when(itemRepository.findAll()).thenReturn(Arrays.asList(new Item(10001, "Item1", 10, 20),
				new Item(10002, "Item2", 5, 10),
				new Item(10003, "Item3", 6, 60)));
		
		List<Item> items = itemBusinessServiceImpl.retrieveAllItems();
		
		Assertions.assertEquals(200, items.get(0).getValue());
		Assertions.assertEquals(50, items.get(1).getValue());
		Assertions.assertEquals(360, items.get(2).getValue());
		
	}
	
}
