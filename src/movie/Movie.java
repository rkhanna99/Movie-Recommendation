package movie;

public class Movie {
	
	//Instance Variables
	private String name;
	private int rel_year;
	private double rating;
	private String[] genres;
	private String imdbID;
	private String tmdbID;
	
	//Constructor for a movie
	public Movie(String name, int rel_year, double rating, String[] genres, String imdbID, String tmdbID) {
		this.name = name;
		this.rel_year = rel_year;
		this.rating = rating;
		this.genres = genres;
		this.imdbID = imdbID;
		this.tmdbID = tmdbID;
	}

	//Getters and setters, so accessible through any package
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRel_year() {
		return rel_year;
	}

	public void setRel_year(int rel_year) {
		this.rel_year = rel_year;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getTmdbID() {
		return tmdbID;
	}

	public void setTmdbID(String tmdbID) {
		this.tmdbID = tmdbID;
	}
	
}
