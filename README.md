# Movie Recommendation/Search App

In this project I wanted to make an application that was capable of providing movie recommendations and allowing a user to search for any movie and get infromation regarding it. For this project I used a combination of R(to handle data) and Java(main project functionality). This project runs directly on the command line. I've encountered some issues trying to make it work on all devicees but I'm in the process of trying to fix them.

## The Process

1. Data Considerations
- For the reccomendation side of the application I downloaded the full MovieLens Latest Dataset from https://grouplens.org/datasets/movielens/
- After downloading the Dataset, I then loaded the various csv files into Rstudio and then used some Data manipulation techniques that can be seen in the Rmarkdown file
- Once I had tidyed the Dataset and combined all the desired fields into one table, I saved the file as a csv and then coverted that csv to a text file (final.txt)

2. Building the Application
- Now that I had successfully created a text file that served as an expansive movie Dataset, I was free to start building the application. I decided to use Eclipse/Java for the project. Although at points I also used a VsCode on a different device to work on it.
- The first step I took in Java was to make create a new type Movie and store them in a HashMap. The movie type consisted of the following fields:
  - String name
  - int rel_year
  - double rating
  - String[] genres
  - String imdbID
  - String tmdbID
- Then I parsed the text file(final.txt) and added each movie to the HashMap that was being used to store all the data.
