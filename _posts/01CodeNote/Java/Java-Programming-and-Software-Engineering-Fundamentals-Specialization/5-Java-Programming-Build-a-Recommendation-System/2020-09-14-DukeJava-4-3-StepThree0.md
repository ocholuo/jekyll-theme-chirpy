---
title: Java - DukeJava 4-3 Step Four Calculating Weighted Averages
date: 2020-09-14 11:11:11 -0400
description:
categories: [1CodeNote, JavaNote]
tags: [Java]
img: /assets/img/sample/rabbit.png
---

[toc]

---

# DukeJava 4-3 Step Four Calculating Weighted Averages

Java-Programming-and-Software-Engineering-Fundamentals-Specialization
- 5.Java-Programming-Build-a-Recommendation-System
  - Step One : get the rating, rater, movie from the file
  - Step Two : Simple recommendations
  - Step Three : Interfaces, Filters, Database
  - Step Four : Calculating Weighted Averages

Resource Link: https://www.coursera.org/learn/java-programming-recommender/supplement/KTrOQ/programming-exercise-step-two

starter files: https://www.dukelearntoprogram.com//course5/files.php

---


```java
Movie (String anID, String aTitle, String aYear, String theGenres, String aDirector, String aCountry, String aPoster, int theMinutes)

Rater(String id, ArrayList<Rating>())

EfficientRater (String id, HashMap<String,Rating>)

Rating (String anItem, double aValue)

MovieDatabase ( HashMap<String movieID, Movie m> ourMovies)

// ---------------------------------

public interface Rater {
    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    public String getID();
    public double getRating(String item);
    public int numRatings();
    public ArrayList<String> getItemsRated();
    public void printRatingHash();
}

public class PlainRater implements Rater{}

public class EfficientRater implements Rater{}

// ---------------------------------

public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies;
    private static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies("data/ratedmoviesfull.csv");
        }
    }
    public static HashMap<String, Movie> initialize(String moviefile){}
    private static void loadMovies(String filename) {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);
        for (Movie m : list) {ourMovies.put(m.getID(), m);}
    }
    public static boolean containsID(String id){}
    public static int getYear(String id){}
    public static String getGenres(String id){}
    public static String getTitle(String id){}
    public static Movie getMovie(String id){}
    public static String getPoster(String id){}
    public static int getMinutes(String id){}
    public static String getCountry(String id){}
    public static String getDirector(String id){}
    public static int size(){}
    public static ArrayList<String> filterBy(Filter f){}
}

// ---------------------------------

public interface Filter {
	public boolean satisfies(String id);
}

public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    public AllFilters() {filters = new ArrayList<Filter>();}
    public void addFilter(Filter f) {filters.add(f);}
    @Override
    public boolean satisfies(String id){}
}

public class TrueFilter implements Filter {
	@Override
	public boolean satisfies(String id) {return true;}
}

public class YearAfterFilter implements Filter {
	private int myYear;
	public YearAfterFilter(int year){}
	@Override
	public boolean satisfies(String id){}
}

// ---------------------------------

public class FirstRatings{
    private ArrayList<Movie> csvMovieme(CSVParser parser){}
    public ArrayList<Movie> loadMovies(String filename){}
    public void testLoadMovies(){}
    private ArrayList<Rater> csvRater(CSVParser parser){}
    public ArrayList<Rater> loadRaters(String filename){}
    public void testLoadRaters(int raterID, String movieID){}
}

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    public SecondRatings(){}
    public SecondRatings(String moviefile, String ratingsfile){}
    public int getMovieSize() {return myMovies.size();}
    public int getRaterSize() {return myRaters.size();}
    public double getAverageByID(String id, int minimalRaters){}
    public ArrayList<Rating> getAverageRatings(int minimalRaters){}
    public String getTitle(String id){}
    public String getID(String title){}
}

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    public ThirdRatings(){}
    public ThirdRatings(String ratingsfile){}
    public int getRaterSize() {return myRaters.size();}
    public double getAverageByID(String id, int minimalRaters){}
    public ArrayList<Rating> getAverageRatings(int minimalRaters){}
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){}
}

// ---------------------------------

public class MovieRunnerAverage {
    public void printAverageRatings(){}
    public void getAverageRatingOneMovie(){}
}

public class MovieRunnerWithFilters {
    private int helperMoviesAndRatings() {}
    public void printAverageRatings(){}
    public void printAverageRatingsByYear() {}
    public void printAverageRatingsByGenre() {}
    public void printAverageRatingsByMinutes() {}
}

```

---











.
