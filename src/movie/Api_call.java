package movie;

import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Api_call {

	// Method that will handle an api request
	// Remember that a user can search for a movie by using either the title or the imdbID
	public static void handleReq(String[] commands) {

		// Setup the fields for the request
		HttpResponse<JsonNode> response = null;
		String host = "http://www.omdbapi.com/";
		String charset = "UTF-8";
		String omdbApiKey = "apikey=94136cdc";
		String imdbID, title = "", rel_year, imdbParam = null, titleYearParam = null;
		if(commands[1] == null) {
			imdbID = commands[0];
			imdbParam = "i=" + imdbID;
		} else {
			//title = commands[0];
			// Handle the case where the name of the movie has spaces in it, we have to adjust the api call accordingly
			if(commands[0].contains(" ")) {
				String[] temp = commands[0].split(" ");
				for(String s: temp) {
					title = title + s + "+";
				}
				title = title.substring(0, title.length() - 1);
			} else {
				title = commands[0];
			}
			rel_year = commands[1];
			titleYearParam = "t=" + title + "&y=" + rel_year;
		}
		
		
		// String query = String.format("%s=%s", URLEncoder.encoder());

		// Send the appropriate request
		if(titleYearParam == null) {
			try {
				response = Unirest.get(host + "?" + imdbParam + "&" + omdbApiKey).asJson();
				System.out.println(host + "?" + imdbParam + "&" + omdbApiKey);
				
				//Displays the status of the API call, if we get 200 then everything is running fine
				System.out.println(response.getStatus());
				System.out.println(response.getHeaders().get("Content-Type"));
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			try {
				response = Unirest.get(host + "?" + titleYearParam + "&" + omdbApiKey).asJson();
				System.out.println(host + "?" + titleYearParam + "&" + omdbApiKey);
				
				//Displays the status of the API call, if we get 200 then everything is running fine
				System.out.println(response.getStatus());
				System.out.println(response.getHeaders().get("Content-Type"));
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Use GSON library to "prettify" the results from the query
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(response.getBody().toString());
		String prettyJsonString = gson.toJson(je);
		System.out.println(prettyJsonString);
	}


	
}
