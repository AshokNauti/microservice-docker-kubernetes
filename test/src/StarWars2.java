import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StarWars2 {

	public static void main(String[] strs) {

		System.out.println(run("A New Hope", "Raymus Antilles"));
		// System.out.println(run("The Force Awakens", "Poggle the Lesser"));
		// System.out.println(run("The Force Awakens", "Walter White"));
	}

	public static String run(String film, String character) {

		String filmsAndCharacters = "";

		try {

			if (film.isBlank() || character.isBlank()) {
				return "";
			}
			// TesT Case 1: filmName = "A New Hope"; characterName = "Raymus Antilles";
			// TesT Case 2: filmName = "The Force Awakens"; characterName = "Poggle the Lesser";
			// TesT Case 3: filmName = "The Force Awakens"; characterName = "Walter White";

			filmsAndCharacters = getResponse(film, character);
		} catch (Exception e) {
			// e.printStackTrace();
			return "";
		}

		return filmsAndCharacters;
	}

	private static JsonObject callSwapiApi(URL url) throws IOException {

		StringBuilder result = new StringBuilder();

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		// conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setRequestProperty("Accept", "application/json");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();

		return new JsonParser().parse(result.toString()).getAsJsonObject();

	}

	private static String getResponse(String film, String character) throws IOException {

		List<String> filmHasCharactersList = new ArrayList<>();
		List<String> characterFilmsList = new ArrayList<>();

		JsonObject jsonObject;
		URL url;
		JsonArray filmHasCharactersArray;
		JsonArray characterFilmsArray;

		List<String> filmHasCharactersSortedList = new ArrayList<>();
		List<String> characterFilmsSortedList = new ArrayList<>();

		String filmsAndCharacters;

		// Part 1
		url = new URL("https://challenges.hackajob.co/swapi/api/films/?search=" + film.replace(" ", "%20"));

		jsonObject = callSwapiApi(url);

		if (jsonObject != null && jsonObject.getAsJsonArray("results").size() > 0
				&& jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().getAsJsonArray("characters") != null) {

			filmHasCharactersArray = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject()
					.getAsJsonArray("characters");

			for (JsonElement jsonElement : filmHasCharactersArray) {

				url = new URL(jsonElement.getAsString());
				jsonObject = callSwapiApi(url);

				if (jsonObject.get("name") != null) {
					filmHasCharactersList.add(jsonObject.get("name").getAsString());
				}
			}
		}

		// Part 2
		url = new URL("https://challenges.hackajob.co/swapi/api/people/?search=" + character.replace(" ", "%20"));

		jsonObject = callSwapiApi(url);

		if (jsonObject != null && jsonObject.getAsJsonArray("results").size() > 0
				&& jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().getAsJsonArray("films") != null) {

			characterFilmsArray = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().getAsJsonArray("films");

			for (int i = 0; i < characterFilmsArray.size(); i++) {

				url = new URL(characterFilmsArray.get(i).getAsString());
				jsonObject = callSwapiApi(url);

				if (jsonObject != null && jsonObject.get("title") != null) {
					characterFilmsList.add(jsonObject.get("title").getAsString());
				}
			}
		}

		// Sorting the lists
		filmHasCharactersSortedList = filmHasCharactersList.stream().sorted().collect(Collectors.toList());
		characterFilmsSortedList = characterFilmsList.stream().sorted().collect(Collectors.toList());

		filmsAndCharacters = film + ": ";

		// Forming Output in desired format
		if (filmHasCharactersSortedList.size() == 0) {
			filmsAndCharacters = filmsAndCharacters + "none; ";
		} else {
			for (int i = 0; i < filmHasCharactersSortedList.size(); i++) {
				filmsAndCharacters = filmsAndCharacters + filmHasCharactersSortedList.get(i)
						+ ((i == filmHasCharactersSortedList.size() - 1) ? "; " : ", ");
			}
		}

		filmsAndCharacters = filmsAndCharacters + character + ": ";

		if (characterFilmsSortedList.size() == 0) {
			filmsAndCharacters = filmsAndCharacters + "none";
		} else {
			for (int i = 0; i < characterFilmsSortedList.size(); i++) {
				filmsAndCharacters = filmsAndCharacters + characterFilmsSortedList.get(i)
						+ ((i == characterFilmsSortedList.size() - 1) ? "" : ", ");
			}
		}

		return filmsAndCharacters;

	}
}
