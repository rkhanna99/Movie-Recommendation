# Movie Recommendation/Search App

In this project I wanted to make an application that was capable of providing movie recommendations and allowing a user to search for any movie and get infromation regarding it. For this project I used a combination of R (to handle data) and Java (main project functionality). This project runs directly on the command line through eclipse. Also note this is just running locally on my machine. Read the Future Considerations part to see how this can be improved.

## The Process

**1. Data Considerations**
- For the recommendation side of the application I downloaded the full MovieLens Latest Dataset from https://grouplens.org/datasets/movielens/
- For the search side of the application the online api I used was http://www.omdbapi.com
- After downloading the Dataset, I then loaded the various csv files into Rstudio and then used some Data manipulation techniques that can be seen in the Rmarkdown file
- Once I had tidyed the Dataset and combined all the desired fields into one table, I saved the file as a csv and then coverted that csv to a text file (final.txt)

**2. Building the Application**
- Now that I had successfully created a text file that served as an expansive movie Dataset, I was free to start building the application. I decided to use Eclipse/Java for the project. Although at points I also used a VsCode on a different device to work on it.
- The first step I took in Java was to make create a new type Movie and store them in a HashMap. The movie type consisted of the following fields:
  - String name
  - int rel_year
  - double rating
  - String[] genres
  - String imdbID
  - String tmdbID
- Then I parsed the text file(final.txt) and added each movie to the HashMap that was being used to store all the data.
- After setting up the data I focused on the main functionality of the program. rec_main.java is where the main method is located.
- Many of the helper functions used throughout the program are located in Utils.java

**3. Functionality**
- The application first starts out by welcoming the user to the program and then asks the user whether they want movie recommendations or to search for a movie.
- Movie Recommendations (User enters "1")
  - They now have the option of either getting a list of 10 random movies or get recommendations by Genre, Year, and mimimum rating.
    - Random (User enters "random")
      - 10 random movies are printed out
    - Filtered by user (User enters "main")
      - Prompted for Genre(s), Year(s), and Minimum rating
      - Input is then validated by regex
      - A maximum of 5 movies are listed based off the user input
- Movie Search (User enters "2")
  - Now the user will be able to search for any movie based of imdbID or a combination of name and year.
  - After a user types the required info a call to the online api will be made and the info will be presented in JSON format.
  - To make the API call I used the unirest library which I incorporated into the project using Maven.
- The application will continue to run as long as the user has a request to make, once the user is done using the app they can either type "exit" or "quit" to terminate the program.

**4. Displaying Functionality**
- 10 Random Movies

![random](https://user-images.githubusercontent.com/59949597/90561276-77283f80-e16e-11ea-9918-c3123e7cfa4d.JPG)

- Main Functionality

![main](https://user-images.githubusercontent.com/59949597/90561748-2fee7e80-e16f-11ea-8252-ee256cf77538.JPG)

- Search Movie

![search](https://user-images.githubusercontent.com/59949597/90561945-77750a80-e16f-11ea-9902-053669631d93.JPG)

## Future Considerations

- The biggest change to consider in the future is how I store the data. Currently I have the data stored as a csv file locally. To improve usability I could transition the data to an online database management system like MySQL, MongoDB, etc. This would allow the app to easily run on different devices.
- Another thing that could be implemented would be a comprehensive User Interface that would remove the need for text based actions.
