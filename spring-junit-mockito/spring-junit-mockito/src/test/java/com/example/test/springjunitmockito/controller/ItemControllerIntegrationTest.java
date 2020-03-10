package com.example.test.springjunitmockito.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.test.springjunitmockito.model.Item;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//Below annotation has the highest priority, if present. 
//If not present then if there is any resource folder under test directory will be searched for the application.properties file file first
//If above 2 setups are not available them resource folder under java will be looked at for the application.properties file
@TestPropertySource(locations= {"classpath:test-specific-configuration.properties"})
public class ItemControllerIntegrationTest {
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	public void contextLoads() throws JSONException {
		
		String response = this.testRestTemplate.getForObject("/all-items-from-database", String.class);
		
		JSONAssert.assertEquals("[{id:10001},{id:10002},{id:10003}]", response, false);
		

        Item item = new Item();
        item.setName("Item6");
        item.setPrice(100);
        item.setQuantity(15);
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Item> request = new HttpEntity<>(item, headers);
        
		ResponseEntity<Item> res = this.testRestTemplate.postForEntity("/items", request, Item.class);
		
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
		//JSONAssert.assertEquals("{name:Item6,price:100,quantity:15}", res.getBody().toString(), false);
	}
	
}
