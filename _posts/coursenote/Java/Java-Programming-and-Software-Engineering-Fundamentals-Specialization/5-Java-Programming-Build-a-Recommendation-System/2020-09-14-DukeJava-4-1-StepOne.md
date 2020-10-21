---
title: Java - DukeJava 4-1 StepOne
date: 2020-09-14 11:11:11 -0400
description:
categories: [CourseNote, JavaNote]
tags: [Java]
img: /assets/img/sample/rabbit.png
---

# DukeJava 4-1 StepOne

[toc]

Java-Programming-and-Software-Engineering-Fundamentals-Specialization
- 5.Java-Programming-Build-a-Recommendation-System
  - Step One : get the rating, rater, movie from the file

Resource Link: https://www.coursera.org/learn/java-programming-recommender/supplement/ILMcl/programming-exercise-step-one

starter files: https://www.dukelearntoprogram.com//course5/files.php

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

## Assignment

```java
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
```






.
