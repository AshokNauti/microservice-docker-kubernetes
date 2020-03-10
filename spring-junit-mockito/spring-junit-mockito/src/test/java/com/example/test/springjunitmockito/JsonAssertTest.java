package com.example.test.springjunitmockito;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {

	String actualResponse = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
	
	@Test
	public void JsonAssertStrictTrue() throws JSONException{
		
		String expectedResponse = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
		
		//If the flag is true so all the json attributes to be there otherwise test fails but if false few missing attributes are fine
		JSONAssert.assertEquals(expectedResponse, actualResponse, true);
	}
	
	@Test
	public void JsonAssertStrictFalse() throws JSONException{
		
		String expectedResponse = "{\"id\":1,\"name\":\"Ball\",\"price\":10}";
		
		//If the flag is true so all the json attributes to be there otherwise test fails but if false few missing attributes are fine
		JSONAssert.assertEquals(expectedResponse, actualResponse, false);
	}
	
	@Test
	public void JsonAssertWithoutEscapeCharacters() throws JSONException{
		
		//String expectedResponse = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
		String expectedResponse = "{id:1,name:Ball,price:10}";
		
		//If the flag is true so all the json attributes to be there otherwise test fails but if false few missing attributes are fine
		JSONAssert.assertEquals(expectedResponse, actualResponse, false);
	}
}
