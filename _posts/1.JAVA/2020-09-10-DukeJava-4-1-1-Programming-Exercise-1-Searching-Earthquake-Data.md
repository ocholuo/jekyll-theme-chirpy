---
title: Java - DukeJava - 4-1-1 - Programming Exercise - Searching Earthquake Data
date: 2020-09-10 11:11:11 -0400
description:
categories: [Java, DukeCourse]
img: /assets/img/sample/rabbit.png
tags: [Java]
---


# DukeJava - 4-1-1 - Programming Exercise - Searching Earthquake Data

[toc]

Java-Programming-and-Software-Engineering-Fundamentals-Specialization
- 4.Java-Programming-Principles-of-Software-Design
  - 4-1-1 Programming Exercise 1 - Searching Earthquake Data

Resource Link: http://www.dukelearntoprogram.com/course4/index.php

ProjectCode: https://github.com/ocholuo/language/tree/master/0.project/javademo

---

For the following assignments, you will start with the files provided, using most of the classes, and modifying only a few of them, and create a new class. The classes provided are:

- The class Location, from the Android platform and revised for this course, a data class representing a geographic location. One of the constructors has parameters latitude and longitude, and one of the public methods is distanceTo.
- The class QuakeEntry, from the lesson, which has a constructor that requires latitude, longitude, magnitude, title, and depth. It has several get methods and a toString method.
- The class EarthQuakeParser, from the lesson, which has a read method with one String parameter that represents an XML earthquake data file and returns an ArrayList of QuakeEntry objects.
- The class EarthQuakeClient, which has been started for you and creates an EarthQuakeParser to read in an earthquake data file, creating an ArrayList of QuakeEntrys. You can test the program with the method createCSV to store an ArrayList of the earthquake data and print a CSV file. You will complete the methods that filter magnitude and distance in this class and add additional methods to it.
- The class ClosestQuakes, which has been started for you to find the ten closest quakes to a particular location. You will complete this method.

---

## Assignment 1: Filtering by Magnitude and Distance

In this assignment you will complete the program to filter earthquake data by magnitude and distance, which was described in this lesson in the videos “Coding a Magnitude Filter” and “Coding a Distance Filter.”

Specifically, for this assignment, you will only modify one class, the EarthQuakeClient class:

1. Write the method `filterByMagnitude` that has already been started for you. This method has two parameters, an ArrayList of type QuakeEntry named quakeData, and a double named magMin.
    - This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that have a magnitude larger than magMin. Notice that we have already created an ArrayList named answer for you to store those earthquakes that satisfy this requirement.

2. Modify the method `bigQuakes` that has no parameters to use filterByMagnitude and print earthquakes above a certain magnitude, and also print the number of such earthquakes.
    - this method reads data on earthquakes from a file, stores a QuakeEntry for each earthquake read in the ArrayList named list, and prints out the number of earthquakes read in.
    - After making modifications, when you run your program on the file nov20quakedatasmall.atom for quakes larger than 5.0, you should get the output:

3. Write the method `filterByDistanceFrom`. This method has three parameters, an ArrayList of type QuakeEntry named quakeData, a double named distMax, and a Location named from.
    - This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that are less than distMax from the location from.

4. Modify the method `closeToMe` that has no parameters to call `filterByDistance` to print out the earthquakes within 1000 Kilometers to a specified city (such as Durham, NC).
    - For each earthquake found, print the distance from the earthquake to the specified city, followed by the information about the city (use getInfo()).
    - Currently this method reads data on earthquakes from a URL, stores a QuakeEntry for each earthquake read in the ArrayList named list, and prints out the number of earthquakes read in. It also gives the location for two cities, Durham, NC (35.988, -78.907) and Bridgeport, CA (38.17, -118.82).
    - After making modifications, when you run your program on the file `nov20quakedatasmall.atom` for the city location `Durham, NC`, no earthquakes are found. But if you then run the program for the city location `Bridgeport, CA`, seven earthquakes are found, and you should get the output:

---

## Assignment 2: Filtering by Depth
In this assignment you will filter earthquakes by their depth, finding those earthquakes whose depth is between a minimum and maximum value. For more information on what the "depth" of an earthquake means, see the information here: [1](http://earthquake.usgs.gov/learn/topics/seismology/determining_depth.php)

Specifically, for this assignment, you will add new methods to one class, the EarthQuakeClient class:

1. Write the method `filterByDepth` that has three parameters, an ArrayList of type QuakeEntry named quakeData, a double named minDepth and a double named maxDepth.
    - This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData whose depth is between minDepth and maxDepth, exclusive. (Do not include quakes with depth exactly minDepth or maxDepth.)

2. Write the void method `quakesOfDepth` that has no parameters to use `filterByDepth`
    - print all the earthquakes from a data source whose depth is between a given minimum and maximum value.
    - also print out the number of earthquakes found.
    - After writing this method, when you run your program on the file nov20quakedatasmall.atom for quakes with depth between -10000.0 and -5000.0 you should find five such quakes and get the output:

---
