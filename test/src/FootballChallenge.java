import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FootballChallenge {

	public static void main(String str[]) {

		System.out.println(run("manutd"));
	}

	public static int run(String teamKey) {
		/*
		 * Some work here; return type and arguments should be according to the
		 * problem's requirements
		 */
		if (teamKey == null || teamKey.isEmpty()) {
			return 0;
		}

		try {

			int goals = 0;

			String season = "English Premier League 2014/15";

			StringBuilder result = new StringBuilder();
			URL url = new URL(
					"https://raw.githubusercontent.com/openfootball/football.json/master/2014-15/en.1.json?format=json");
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

			JsonObject jsonObject = new JsonParser().parse(result.toString()).getAsJsonObject();

			if (jsonObject != null && jsonObject.getAsJsonObject().get("name") != null
					&& season.equals(jsonObject.getAsJsonObject().get("name").getAsString())) {

				if (jsonObject.getAsJsonArray("rounds") != null) {

					JsonArray rounds = jsonObject.getAsJsonArray("rounds");

					if (rounds != null) {
						for (JsonElement round : rounds) {

							if (round != null && round.getAsJsonObject() != null
									&& round.getAsJsonObject().getAsJsonArray("matches") != null) {

								JsonArray matches = round.getAsJsonObject().getAsJsonArray("matches");

								if (matches != null) {
									for (JsonElement match : matches) {

										if (match.getAsJsonObject() != null
												&& match.getAsJsonObject().getAsJsonObject("team1") != null
												&& match.getAsJsonObject().getAsJsonObject("team1")
														.get("key") != null) {
											if (teamKey
													.equals(match.getAsJsonObject().getAsJsonObject("team1").get("key")
															.getAsString())
													&& match.getAsJsonObject().get("score1") != null) {
												goals = goals + match.getAsJsonObject().get("score1").getAsInt();
											} else if (teamKey
													.equals(match.getAsJsonObject().getAsJsonObject("team2").get("key")
															.getAsString())
													&& match.getAsJsonObject().get("score2") != null) {
												goals = goals + match.getAsJsonObject().get("score2").getAsInt();
											}
										}
									}
								}
							}
						}
					}
				}
			}

			return goals;
		} catch (Exception e) {
			// e.printStackTrace();
			return 0;
		}
	}

}
