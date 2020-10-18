---
title: Java - DukeJava 4-1 StepOne
date: 2020-09-14 11:11:11 -0400
description:
categories: [CourseFinished, JavaNote]
img: /assets/img/sample/rabbit.png
tags: [Java]
---

# DukeJava 4-1 StepOne

---

- The class <kbd>Movie</kbd>
- a Plain Old Java Object (POJO) class
- storing the data about one movie. 
- It includes the following items:
  - Eight private variables to represent information about a movie including:
    - id - a String variable representing the IMDB ID of the movie
    - title - a String variable for the movie’s title
    - year - an integer representing the year
    - genres - one String of one or more genres separated by commas
    - director - one String of one or more directors of the movie separated by commas
    - country - one String of one or more countries the film was made in, separated by commas
    - minutes - an integer for the length of the movie
    - poster - a String that is a link to an image of the movie poster if one exists, or “N/A” if no poster exists
  - A `constructor` with eight parameters to initialize the private variables
  - Eight `getter methods` to return the private information such as the method getGenres that returns a String of all the genres for this movie.
  - A `toString method` for representing movie information as a String so it can easily be printed.

---

The class <kbd>Rating</kbd>
- a POJO class
- storing the data about one rating of an item. 
- It includes
  - Two private variables to represent information about a rating:
    - item - a String description of the item being rated (for this assignment you should use the IMDB ID of the movie being rated)
    - value - a double of the actual rating
    - A constructor with two parameters to initialize the private variables.
  - Two `getter methods` getItem and getValue.
  - A `toString method` to represent rating information as a String.
  - A `compareTo method` to compare this rating with another rating.

---

The class <kbd>Rater</kbd> 
- keeps track of one rater and all their ratings. 
- This class includes:
  - Two private variables:
    - myID - a unique String ID for this rater
    - myRatings - an ArrayList of Ratings
  - A `constructor` with one parameter of the ID for the rater.
  - A `method addRating` that has two parameters, a String named item and a double named rating. A new Rating is created and added to myRatings.
  - A `method getID` with no parameters to get the ID of the rater.
  - A `method getRating` that has one parameter item. This method returns the double rating of this item if it is in myRatings. Otherwise this method returns -1.
  - A `method numRatings` that returns the number of ratings this rater has.
  - A `method getItemsRated` that has no parameters. This method returns an ArrayList of Strings representing a list of all the items that have been rated.

---

ratedmovies_short.csv

```
id,title,year,country,genre,director,minutes,poster
0006414,"Behind the Screen",1916,"USA","Short, Comedy, Romance","Charles Chaplin",30,"http://ia.media-imdb.com/images/M/MV5BMTkyNDYyNTczNF5BMl5BanBnXkFtZTgwMDU2MzAwMzE@._V1_SX300.jpg"
0068646,"The Godfather",1972,"USA","Crime, Drama","Francis Ford Coppola",175,"http://ia.media-imdb.com/images/M/MV5BMjEyMjcyNDI4MF5BMl5BanBnXkFtZTcwMDA5Mzg3OA@@._V1_SX300.jpg"
0113277,"Heat",1995,"USA","Action, Crime, Drama","Michael Mann",170,"http://ia.media-imdb.com/images/M/MV5BMTM1NDc4ODkxNV5BMl5BanBnXkFtZTcwNTI4ODE3MQ@@._V1_SX300.jpg"
1798709,"Her",2013,"USA","Drama, Romance, Sci-Fi","Spike Jonze",126,"http://ia.media-imdb.com/images/M/MV5BMjA1Nzk0OTM2OF5BMl5BanBnXkFtZTgwNjU2NjEwMDE@._V1_SX300.jpg"
0790636,"Dallas Buyers Club",2013,"USA","Biography, Drama","Jean-Marc VallÃ©e",117,"N/A"
```

ratings_short.csv:

```
rater_id,movie_id,rating,time
1,0068646,10,1381620027
1,0113277,10,1379466669
2,1798709,10,1389948338
2,0790636,7,1389963947
2,0068646,9,1382460093
3,1798709,9,1388641438
4,0068646,8,1362440416
4,1798709,6,1398043318
5,0068646,9,1364834910
5,1798709,8,1404338202
```


---

## Assignment 1

create a new class FirstRatings 
- to process the movie and ratings data and to answer questions about them. 
- use CSVParser and CSVRecord.
- FirstRatings will need the following three import statements:

```java
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
```

```
<!-- write the following methods
`loadMovies(String filename)`
  - process every record from the CSV file whose name is filename, a file of movie information
  - return an ArrayList of type Movie with all of the movie data from the file.
  
- Write a void method `testLoadMovies()` 
  - Call the method loadMovies on the file ratedmovies_short.csv and store the result in an ArrayList local variable . Print the number of movies, and print each movie. should see tfive movies in this file. After this works you should comment out the printing of the movies. If you run your program on the file ratedmoviesfull.csv, you should see there are 3143 movies in the file.
  - Add code to determine how many movies include the Comedy genre. In the file ratedmovies_short.csv, there is only one.
  - Add code to determine how many movies are greater than 150 minutes in length. In the file ratedmovies_short.csv, there are two.
  - Add code to determine the maximum number of movies by any director, and who the directors are that directed that many movies. Remember that some movies may have more than one director. In the file ratedmovies_short.csv the maximum number of movies by any director is one, and there are five directors that directed one such movie.
  
- `loadRaters(String filename)` 
  - process every record from the CSV file whose name is filename, a file of raters and their ratings
  - return an ArrayList of type Rater with all the rater data from the file.

- Write a void method named `testLoadRaters()` that should do several things.
  - Call the method loadRaters on the file ratings_short.csv and store the result in a local ArrayList variable. Print the total number of raters. Then for each rater, print the rater’s ID and the number of ratings they did on one line, followed by each rating (both the movie ID and the rating given) on a separate line. If you run your program on the file ratings_short.csv you will see there are five raters. After it looks like it works, you may want to comment out the printing of each rater and their ratings. If you run your program on the file ratings.csv, you should get 1048 raters.
  - Add code to find the number of ratings for a particular rater you specify in your code. For example, if you run this code on the rater whose rater_id is 2 for the file ratings_short.csv, you will see they have three ratings.
  - Add code to find the maximum number of ratings by any rater. Determine how many raters have this maximum number of ratings and who those raters are. If you run this code on the file ratings_short.csv, you will see rater 2 has three ratings, the maximum number of ratings of all the raters, and that there is only one rater with three ratings.
  - Add code to find the number of ratings a particular movie has. If you run this code on the file ratings_short.csv for the movie “1798709”, you will see it was rated by four raters.
  - Add code to determine how many different movies have been rated by all these raters. If you run this code on the file ratings_short.csv, you will see there were four movies rated. -->
```






























.