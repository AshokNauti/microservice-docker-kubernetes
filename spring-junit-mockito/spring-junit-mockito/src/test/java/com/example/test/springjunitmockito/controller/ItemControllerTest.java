package com.example.test.springjunitmockito.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.test.springjunitmockito.model.Item;
import com.example.test.springjunitmockito.service.ItemBusinessService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private ItemBusinessService itemBusinessService;
	
	@Test
	public void dummyItemTest() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);
		
		//MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//Assertions.assertEquals("Hello World", result.getResponse().getContentAsString());
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
				.andReturn();
		
		//Assertions.assertEquals("Hello World", result.getResponse().getContentAsString());
	}
	
	@Test
	public void itemFromBusinessServiceTest() throws Exception {
		
		when(itemBusinessService.retrieveHardcodedItem()).thenReturn(new Item(2, "Item 2", 10, 100));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
											.get("/item-from-business-service")
											.accept(MediaType.APPLICATION_JSON);
		
		//MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//Assertions.assertEquals("Hello World", result.getResponse().getContentAsString());
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"id\":2,\"name\":\"Item 2\",\"price\":10,\"quantity\":100}"))
				.andReturn();
		
		//Assertions.assertEquals("Hello World", result.getResponse().getContentAsString());
	}
	
	@Test
	public void retrieveAllItemsTest() throws Exception {
		
		when(itemBusinessService.retrieveAllItems()).thenReturn(Arrays.asList(new Item(10001, "Item1", 10, 20),
				new Item(10002, "Item2", 5, 10),
				new Item(10003, "Item3", 6, 60)));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
											.get("/all-items-from-database")
											.accept(MediaType.APPLICATION_JSON);
		
		//MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//Assertions.assertEquals("Hello World", result.getResponse().getContentAsString());
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("[{\"id\":10001,\"name\":\"Item1\",\"price\":10,\"quantity\":20},{\"id\":10002,\"name\":\"Item2\",\"price\":5,\"quantity\":10},{\"id\":10003,\"name\":\"Item3\",\"price\":6,\"quantity\":60}]"))
				.andReturn();
		
		//Assertions.assertEquals("Hello World", result.getResponse().getContentAsString());
	}
	
	@Test
	public void createItem() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
										.post("/items")
										.accept(MediaType.APPLICATION_JSON)
										.content("{\"name\":\"Item5\",\"price\":55,\"quantity\":6}")
										.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder)
							.andExpect(status().is2xxSuccessful())
							//.andExpect(content().string("1"))
							.andReturn();
	}
}
