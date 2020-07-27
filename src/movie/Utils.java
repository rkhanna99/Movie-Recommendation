package movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Utils {
	
	//Utility methods that will be used to get information based on the users request
	
	//Get 10 random unique movies from the dataset
	public static ArrayList<Movie> randomMovies(HashMap<Integer, Movie> data) {
		
		//Put the all the movies into an ArrayList
		List<Movie> valuesList = new ArrayList<Movie>(data.values());
		
		ArrayList<Movie> ret = new ArrayList<Movie>();
		int count = 0;
		
		//Add the 10 random movies
		while(count != 10) {
			int randomIndex = new Random().nextInt(valuesList.size());
			Movie randomVal = valuesList.get(randomIndex);
			//Check to see if the movie is already in the return array
			if(ret.contains(randomVal)) {
				continue;
			} else {
				ret.add(randomVal);
				count++;
			}
		}
		return ret;
	}
	
	//Get Movie recommendations based off genre(s), year(s), and minimum rating
	public static ArrayList<Movie> recommend(String genres, String years, String rating, HashMap<Integer, Movie> data) {
		
		ArrayList<Movie> ret = new ArrayList<Movie>();
		int count = 0;
		
		//Put the all the movies into an ArrayList
		List<Movie> valuesList = new ArrayList<Movie>(data.values());
		
		//Filter out the movies that don't have the specified genre(s)
		if(genres.contains(",")) {
			String[] genreList = genres.split(",");
			for(String genre: genreList) {
				valuesList.removeIf(n -> (checkGenre(n.getGenres(), genre) == false));
			}	
		} else {
			String genre = genres;
			valuesList.removeIf(n -> (checkGenre(n.getGenres(), genre) == false));
		}
		
		//Filter out the movies based on year(s)
		if(years.contains("-")) {
			String[] temp = years.split("-");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			valuesList.removeIf(n -> (checkRange(start, end, n.getRel_year()) == false));
		} else {
			int year = Integer.parseInt(years);
			valuesList.removeIf(n -> n.getRel_year() != year);
		}
		
		//Filter out movies based on rating
		double wantedRating = Double.parseDouble(rating);
		valuesList.removeIf(n -> n.getRating() < wantedRating);
		
		//Now that we have parsed and filtered movies based off the desired parameters we can return 5 random movies of the ones left
		if(valuesList.size() > 5) {
			while(count != 5) {
				int randomIndex = new Random().nextInt(valuesList.size());
				Movie randomVal = valuesList.get(randomIndex);
				//Check to see if the movie is already in the return array
				if(ret.contains(randomVal)) {
					continue;
				} else {
					ret.add(randomVal);
					count++;
				}
			}
		} else {
			ret = (ArrayList<Movie>) valuesList;
		}
		
		return ret;
	}
	
	//Print out any movies
	public static void printMovie(ArrayList<Movie> movies) {
		for(Movie m: movies) {
			System.out.println("" + m.getName() + ", Year Released: " + m.getRel_year() + ", Rating: " + m.getRating() + ", ImdbID: " + m.getImdbID());
		}
	}
	
	//Check if a specified genre is in the array
	public static boolean checkGenre(String[] genres, String genre) {
		boolean hasGenre = false;
		for(String g: genres) {
			if(g.contentEquals(genre)) {
				hasGenre = true;
			}
		}
		return hasGenre;
	}
	
	//Check if a movies release year is within the passed in parameters
	public static boolean checkRange(int start, int end, int rel_year) {
		boolean inRange = false;
		if(rel_year >= start && rel_year <= end) {
			inRange = true;
		} else {
			inRange = false;
		}
		return inRange;
	}
	
	//Validate recommendation input
	public static boolean validateRec(String genres, String years, String rating) {
		boolean[] checks = new boolean[3];;
		int count = 0;
		
		//Check the format of the genres, FIX THIS REGEX IT IS CAUSING VALIDATION TO FAIL
		if(genres.matches("[a-zA-Z]+(,[a-zA-Z]+)*")) {
			checks[0] = true;
		}
		
		//Check the format of the years
		if(years.matches("^((\\d\\d\\d\\d)-(\\d\\d\\d\\d))|(\\d\\d\\d\\d)$")) {
			checks[1] = true;
		}
		
		//Check the format of the ratings
		if(rating.matches("^[0-9]+(\\.[0-9]+)?$")) {
			checks[2] = true;
		}
		
		for(boolean b: checks) {
			if(b == true) {
				count++;
			}
		}
		if(count == 3) {
			return true;
		} else {
			return false;
		}
	}

	// Format data for the API call (Split movie and years)
	public static String[] formatDataAPI(String info) {
		String[] ret = info.split(",");
		return ret;
	}
	
}
