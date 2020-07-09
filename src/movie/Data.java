package movie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {

	/* Here we will load in the txt/CSV file and then add the contents to a HashMap that will be of the form <movieID, Movie>
	 * For a more streamlined parsing experience I will exclude lines which have any of the following cases 
	 * 1. More than 5 semicolons (semicolons split up each of the fields)
	 * 2. Contains characters '<' or '>' there must have been some error in R which caused a few title name to be weirdly formatted
	 * 3. Contains characters '"' this makes it more difficult to extract any release year values
	 * There are very few lines like this so the impact on the dataset is negligible. Only 108/53889 lines are removed*/
	public static HashMap<Integer, Movie> parseCSV() {
		
		// HashMap that we will use to store all the movie contents
		HashMap<Integer, Movie> movieData = new HashMap<Integer, Movie>();
		
		// Location of the text file
		String csvFile = "src/final.txt";
        BufferedReader reader = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] placeholder;
        int movieID;
        Movie currMovie;
        
        // Read the file line by line (surround with try/catch/finally)
        try {
			reader = new BufferedReader(new FileReader(csvFile));
			while((line = reader.readLine()) != null) {
				if(line.contains("\"") || line.contains("<") || line.contains(">") || (countOccurences(line, ';', 0) > 5)) {
					continue;
				} else {
					// Now we parse the lines that have the correct formatting (movieId;title;genres;mean_rating;imdbId;tmdbId)
					placeholder = line.split(";");
					movieID = Integer.valueOf(placeholder[0]);
					currMovie = parseline(placeholder);
					movieData.put(movieID, currMovie);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return movieData;
	}
	
	/*Helper method where we will parse a part of line to get the fields of a movie
	 * line[0] -> movieID (This has already been parsed in the parseCSV method)
	 * line[1] -> title and year
	 * line[2] -> genres
	 * line[3] -> mean_rating
	 * line[4] -> imdbID
	 * line[5] -> tmdbID*/
	private static Movie parseline(String[] line) {
		// Movie to return
		Movie ret = new Movie(null, 0, 0, line, null, null);
		
		// Other variables to be used
		String yearRegex = "\\([0-9][0-9][0-9][0-9]\\)$";
		Pattern pattern = Pattern.compile(yearRegex);
		Matcher matcher = pattern.matcher(line[1]);
		String[] arr;
		String name, temp;
		int rel_year = 0;
		
		
		// Parse the first part which has title and year
		arr = line[1].split(yearRegex);
		name = arr[0].replaceAll("\\s+$", "");
		
		if(matcher.find()) {
			String temp1 = matcher.group();
			temp1 = temp1.replaceAll("\\(", "");
			temp1 = temp1.replaceAll("\\)", "");
			rel_year = Integer.parseInt(temp1);
		}
		
		ret.setName(name);
		ret.setRel_year(rel_year);
		
		
		// Parse the second part which has the genres
		temp = line[2];
		if(temp.contains("|")) {
			String[] genres = temp.split("[|]");
			ret.setGenres(genres);
		} else {
			String[] genre = {temp};
			ret.setGenres(genre);
		}
		
		// Parse the mean rating
		ret.setRating(Double.parseDouble(line[3]));
		
		// Parse the imdbID
		ret.setImdbID(line[4]);
		
		// Parse the tmdbID
		ret.setTmdbID(line[5]);
		
		return ret;
	}
	
	// Helper method to count the occurrences of a character in a string
	private static int countOccurences(String someString, char searchedChar, int index) {
		if (index >= someString.length()) {
			return 0;
		}

		int count = someString.charAt(index) == searchedChar ? 1 : 0;
		return count + countOccurences(someString, searchedChar, index + 1);
	}
}
