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
		String host = "http://www.omdbapi.com/";
		String charset = "UTF-8";
		String omdbApiKey = "apikey=94136cdc";
		String imdbID, title, rel_year, imdbParam = null, titleYearParam = null;
		if(commands.length == 1) {
			imdbID = commands[0];
			imdbParam = "i=" + imdbID;
		} else {
			title = commands[0];
			rel_year = commands[1];
			titleYearParam = "t=" + titleYearParam + "&y=" + rel_year;
		}
		
		
		// String query = String.format("%s=%s", URLEncoder.encoder());

		// Send the appropriate request
		if(titleYearParam == null) {
			HttpResponse<JsonNode> response;
			try {
				response = Unirest.get(host + "?" + imdbParam + "&" + omdbApiKey).asJson();
				System.out.println(response.getStatus());
				System.out.println(response.getHeaders().get("Content-Type"));
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			HttpResponse<JsonNode> response;
			try {
				response = Unirest.get(host + "?" + titleYearParam + "&" + omdbApiKey).asJson();
				System.out.println(response.getStatus());
				System.out.println(response.getHeaders().get("Content-Type"));
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


	
}
