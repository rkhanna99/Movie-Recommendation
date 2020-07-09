package movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class rec_main {
	
	// Main method to run the program
	public static void main(String[] args) {
		// Parse the CSV file and add contents to a HashMap
		HashMap<Integer, Movie> movieData = Data.parseCSV();
		
		ArrayList<Movie> temp = new ArrayList<Movie>();
		
		// Start the program
		System.out.println("Hi welcome to my Movie Recommendation/Movie Search program");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				// Beginning prompt
				System.out.println("To get Movie Recommendations enter 1, for search enter 2, or exit/quit to quit");
				
				String s = br.readLine();
				if(s.contentEquals("1")) {
					System.out.println("For recommendations by Genre(s), Year, and min Rating (out of 5) type main");
					System.out.println("For 10 random recommendations type random");
					s = br.readLine();
					
					// Random Movies
					if(s.contentEquals("random")) {
						System.out.println("10 random movies:");
						temp = Utils.randomMovies(movieData);
						Utils.printMovie(temp);
					} 
					
					// Main Recommendations
					else if(s.contentEquals("main")) {
						System.out.println("Type in genres separated by commas, ex (Adventure,Action,Fantasy)");
						String genres = br.readLine();
						System.out.println("Type in a year or range of years separated by a -, ex (2004-2005)");
						String years = br.readLine();
						System.out.println("Type in a minimum rating, ratings are out of 5, ex (3.25)");
						String rating = br.readLine();
						
						//Validate input
						if(Utils.validateRec(genres, years, rating) == true) {
							//Get recommendations if input is valid
							temp = Utils.recommend(genres, years, rating, movieData);
							if(temp.isEmpty() == false) {
								Utils.printMovie(temp);
							} else {
								System.out.println("Sorry no recommendations found based based off your criteria");
							}
						} else {
							System.out.println("Invalid Input");
						}
						
					} 
					
					// Invalid Command
					else {
						System.out.println("Invalid Command");
					}
				} else if(s.contentEquals("2")) {
					System.out.println("Search for a movie by Name, imdbID, or tmdbID and get more info");
				} else if(s.contentEquals("exit") || s.contentEquals("quit")) {
					break;
				} else {
					System.out.println("Invalid Command");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		// Close the buffered reader
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
