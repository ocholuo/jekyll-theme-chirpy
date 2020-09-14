---
title: Java - 4-2-2 Programming Exercise 2 Sorting at Scale
date: 2020-09-14 11:11:11 -0400
description:
categories: [Java, DukeCourse]
img: /assets/img/sample/rabbit.png
tags: [Java]
---

# 4-2-2 Programming Exercise 2 Sorting at Scale

[toc]

Java-Programming-and-Software-Engineering-Fundamentals-Specialization
- 4.Java-Programming-Principles-of-Software-Design
  - [4-2-1 Programming Exercise 1 Implementing Selection Sort](#4-2-1-programming-exercise-1-implementing-selection-sort)
  - [4-2-2 Programming Exercise 2 Sorting at Scale](#4-2-2-programming-exercise-2-sorting-at-scale)

Resource Link: http://www.dukelearntoprogram.com/course4/index.php

ProjectCode: https://github.com/ocholuo/language/tree/master/0.project/javademo

---

- The class `Location`, from the Android platform and revised for this course, a data class representing a geographic location. One of the constructors has parameters latitude and longitude, and one of the public methods is distanceTo.

- The class `QuakeEntry`, from the lesson, which has a constructor that requires latitude, longitude, magnitude, title, and depth. It has several get methods and a toString method. It also has a compareTo method to sort earthquakes by magnitude (and commented out code that previously sorted earthquakes by distance to a location). You will be modifying the compareTo method in the first assignment.

- The class `EarthQuakeParser`, from the lesson, which has a read method with one String parameter that represents an XML earthquake data file and returns an ArrayList of QuakeEntry objects.

- The `DifferentSorters` class includes several methods that are similar to code shown in the videos to test several of the classes/methods in this assignment. You’ll be adding additional tester methods to this class.

- The `MagnitudeComparator` class
  - implements `Comparator` to allow one to sort QuakeEntry’s by magnitude from smallest to largest magnitude.

- The `DistanceComparator` class
  - implements `Comparator` to allow one to sort QuakeEntry’s by their distance to a specified location that is passed in as a parameter.

---

![2020-09-13-EfficientSortStarterProgram](../../../img/javademo-EfficientSortStarterProgram.png)

---

## Assignment 1: compareTo Method

1. Modify the `compareTo` method in the `QuakeEntry` class.
   - (You may want to comment out the current code first).
   - The compareTo method should now sort quake by magnitude first, from smallest magnitude to largest magnitude,
   - and then break ties (use == operator to determine whether magnitudes are equal) by depth, from smallest (most negative) depth to largest depth.


2. Test the `compareTo` method by running the `sortWithCompareTo` method in the `DifferentSorters` class with any data file.
   - The sort used is `Collections.sort`.
   - You should be able to see that the earthquakes are sorted by magnitude, and those with the same magnitude are sorted by depth.
   - Modify this method to print out the QuakeEntry in the ArrayList in position 10 (which is actually the 11th element in the ArrayList) by adding the following code at the end of this method, after sorting and printing out all the elements.


---

## Assignment 2: Title Comparator

1. Write the `TitleAndDepthComparator` class that implements a `Comparator` of type `QuakeEntry`.
   - In this class you should write the `compare` metho
   - has two parameters, a QuakeEntry named q1 and a QuakeEntry named q2.
   - This method should compare the title of q1 and q2.
     - If q1’s title comes before q2’s title in alphabetical order, then this method should return a negative integer.
     - If q1’s title comes after q2’s title, then this method should return a positive integer.
     - If q1’s title is the same as q2’s title, then this method should compare the depth of the two earthquakes.
         - If q1’s depth is less than q2’s depth, then this method should return a negative number.
         - If q1’s depth is greater than q2’s depth, then this method should return a positive integer.
         - Otherwise, this method should return 0.


2. Write the void method `sortByTitleAndDepth` in the `DifferentSorters` class.
   - This method should create an `EarthQuakeParser`, read data from a file on earthquakes and create an ArrayList of QuakeEntry’s.
   - Then this method should call` Collections.sort on` this ArrayList and use the `TitleAndDepthComparator` to sort the earthquakes.
   - You should be able to see that the earthquakes are sorted by title first, and those with the same title are sorted by depth.
   - Modify this method to print out the QuakeEntry in the ArrayList in position 10 (which is actually the 11th element in the ArrayList) after sorting and printing out all the elements.



---

## Assignment 3: Last Word in Title Comparator

1. Write the `TitleLastAndMagnitudeComparator` class that implements a `Comparator` of type QuakeEntry.
   - In this class you should write the `compare` method
   - has two parameters, a QuakeEntry named q1 and a QuakeEntry named q2.
   - This method should compare the last word in the title of q1 and q2.
   - If q1’s last word comes before q2’s last word in alphabetical order, then this method should return a negative integer.
   - If q1’s last word comes after q2’s last word, then this method should return a positive integer.
   - If q1’s last word is the same as q2’s last word, then this method should compare the magnitude of the two earthquakes.
       - If q1’s magnitude is less than q2’s magnitude, then this method should return a negative number.
       - If q1’s magnitude is greater than q2’s magnitude, then this method should return a positive integer. Otherwise, this method should return 0.


2. Write the void method `sortByLastWordInTitleThenByMagnitude` in the `DifferentSorters` class.
   - This method should create an EarthQuakeParser, read data from a file on earthquakes and create an ArrayList of QuakeEntry’s.
   - Then this method should call Collections.sort on this ArrayList and use the `TitleLastAndMagnitudeComparator` to sort the earthquakes.
   - You should be able to see that the earthquakes are sorted by the last word in their title, and those with the same last word are sorted by magnitude.
   - Modify this method to print out the QuakeEntry in the ArrayList in position 10 (which is actually the 11th element in the ArrayList) after sorting and printing out all the elements.
