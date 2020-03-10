package com.example.test.springjunitmockito.controller;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.test.springjunitmockito.controller.HelloWorldController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void helloWorldTest() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello-world").accept(MediaType.APPLICATION_JSON);
		
		//MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//Assertions.assertEquals("Hello World", result.getResponse().getContentAsString());
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().is2xxSuccessful())
				//.andExpect(content().contentTypeCompatibleWith)
				.andReturn();
		
		Assertions.assertEquals("Hello World", result.getResponse().getContentAsString());
	}

}
