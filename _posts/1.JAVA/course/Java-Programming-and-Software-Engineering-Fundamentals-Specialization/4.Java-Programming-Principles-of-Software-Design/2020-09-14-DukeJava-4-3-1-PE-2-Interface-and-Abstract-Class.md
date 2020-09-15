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
