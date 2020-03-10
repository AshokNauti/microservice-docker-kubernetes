import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StarWarSimplified {

	public static void main(String str[]) {
		
		/*
		* Some work here; return type and arguments should be according to the problem's requirements
		*/
		int numberOfFilms = 0;
		
		try {
			
			StringBuilder result = new StringBuilder();
	        URL url = new URL("https://challenges.hackajob.co/swapi/api/people/?search=Luke%20Skywalker");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        //conn.setRequestProperty("Content-Type", "application/json; utf-8");
	        conn.setRequestProperty("Accept", "application/json");
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        while ((line = rd.readLine()) != null) {
	          result.append(line);
	        }
	        rd.close();

			JsonObject jsonObject = new JsonParser().parse(result.toString()).getAsJsonObject();

	        System.out.println(jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().getAsJsonArray("films").size());
	        
	        numberOfFilms = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().getAsJsonArray("films").size();
	        
		} catch (Exception e) {
			e.printStackTrace();
        	numberOfFilms = 0;
    	}  
		
	}
	
	
}
	