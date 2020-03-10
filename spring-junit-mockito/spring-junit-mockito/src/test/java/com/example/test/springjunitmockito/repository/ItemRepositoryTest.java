package com.example.test.springjunitmockito.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.test.springjunitmockito.model.Item;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	public void findAllTest() {
		List<Item> items = itemRepository.findAll();
		
		Assertions.assertEquals(3, items.size());
	}
}
