---
title: Java - DukeJava - 4-3-2 Interface and Abstract Class
date: 2020-09-14 11:11:11 -0400
description:
categories: [Java, DukeCourse]
img: /assets/img/sample/rabbit.png
tags: [Java]
---

# DukeJava - 4-3-1 Programming Exercise 1 Generating Random Text

[toc]

Java-Programming-and-Software-Engineering-Fundamentals-Specialization
- 4.Java-Programming-Principles-of-Software-Design
  - N-Grams: Predictive Text
    - 4-3-1 Programming Exercise 1 Generating Random Text
    - 4-3-2-Interface-and-Abstract-Class

Resource Link: http://www.dukelearntoprogram.com/course4/index.php
ProjectCode: https://github.com/ocholuo/language/tree/master/0.project/javademo

---

```
The class MarkovRunnerWithInterface for running your program to generate random text. This class has several methods:
    A void method named runModel that has three parameters: an IMarkovModel variable named markov, a String named text and an int named size. This method will work with any markov object that implements IMarkovModel.

    A void method named runMarkov. This method creates one of the types of Markov models, and calls runModel with it to generate random text.

    A void method named printOut that formats and prints the randomly generated text.


Two protected fields myText, a String, and myRandom, of type Random.
    A constructor that creates myRandom.

    A setTraining method that is public. This method sets the the private String variable myText to the parameter text.

    A signature for the abstract method getRandomText that has one integer parameter named numChars indicating the length of the randomly generated text.
```

---

## Assignment 1: IMarkovModel Interface

```
Copy over four of the .java files from the the work you did with the previous assignment. (You will need to keep a copy of the program you created from the first two assignments for testing.) Specifically you should copy over the four Java files: MarkovZero.java, MarkovOne.java, MarkovFour.java, and MarkovModel.java. You can ignore the following files from the previous lesson—MarkovRunner.java and Tester.java. We will modify the program to organize it in a different way, using an interface and an abstract class.

Modify your classes MarkovZero, MarkovOne, MarkovFour, and MarkovModel to implement the IMarkovModel interface. Each of these classes should already have the two required methods setTraining and getRandomText, so the only change needed is the first line to add: implements IMarkovModel

Run the method runMarkov that is in the MarkovRunnerWithInterface class. This method should run several Markov objects and generate random text for a MarkovZero, a MarkovOne, a MarkovModel with number 3, and a MarkovFour. Notice that runMarkov is called with each one of these. You can observe that the text gets more like the training text as you move from MarkovZero to MarkovFour.
```

---
