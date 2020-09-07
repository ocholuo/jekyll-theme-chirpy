---
title: Linux - Learning Path
date: 2020-09-04 11:11:11 -0400
categories: [Programming]
tags: [OnePage, Programming]
math: true
image: 
---


# Programming learning Path


| ᐕ)⁾⁾ como estas~~~~ bien~~~~ y tu?~~~~ yes

---

## java project

Index | Name | Date | Course material | Note
---|---|---|---|---
1 | [PerimeterRunner](./javademo/2020-09-04-PerimeterRunner/PerimeterRunner.java) | 2020-09-04 | DukeU
2 | [FindingWebLinks](./javademo/2020-09-04-FindingWebLinks/FindingWebLinks.java) | 2020-09-04 | DukeU
3 | [FindGenieinDNA](./javademo/2020-09-05-FindGenieinDNA/FindGenieinDNA.java) | 2020-09-05 | DukeU
4 | [CSVofCountryExport](./javademo/2020-09-05-CSVofCountryExport/CSVofCountryExport.java) | 2020-09-05 | DukeU
5 | [FindHottestDay](./javademo/2020-09-05-FindHottestDay/FindHottestDay.java) | 2020-09-05 | DukeU
6 | [BabyNames](./javademo/2020-09-06-BabyNames/BabyNames.java) | 2020-09-06 | DukeU | **use CSVParser to process multiple line** <br>`String fname = "yob" + year + ".csv";` <br> `FileResource fr = new FileResource(fname); ` <br> `CSVParser parser = fr.getCSVParser(false);` <br> `for(CSVRecord rec : parser){` <br> `System.out.println("Name " + rec.get(0) + " Gender "+ rec.get(1) + " Num Born" + rec.get(2));}`
7 | [RollsSimulate](./javademo/2020-09-06-RollsSimulate/RollsSimulate.java) | 2020-09-06 | DukeU | **sum two random roll number, make a record array** <br> `Random rand = new Random();` <br> `for(int i = 0; i < rolls; i++){` <br> `int d1 = rand.nextInt(6) + 1`
8 | [CountShakespeareWords](./2020-09-07-CountShakespeareWords/CountShakespeareWords.java) | 2020-09-07| DukeU | **make a word record array** <br> crate getCommon().method to pur common word list into a array `String[] common;` <br> to store counts create `int[] counts = new int[common.length];` <br> `for(String word : resource.words())` <br> crate indexOf().method to get the index of common word `if (common[i].equals(word)); return i` <br> crate countWords().method to ++1 according to the index, `if (index != -1) {counts[index] += 1;}` <br> print out line as index `System.out.println(common[k] + "\t" + counts[k]);`

---

## python project

Index | Name | Date | Course material
---|---|---|---
1 | [Little Turtles Adventure](./pycode/2020-03-21-LittleTurtlesAdventure.md) | 2020-03-21 | University of Michigan in Coursera Chapter ?? : P
2 | [Are Your Words Happy](./pycode/2020-03-25-AreYourWordsHappy.md) | 2020-03-25 | University of Michigan in Coursera Chapter 16.10
3 | [Nested Pokemon](./pycode/2020-03-25-NestedPokemon.md) | 03/25/2020 | University of Michigan in Coursera Chapter 17.8
4 | [Hi MyTamagotchiJiang](./pycode/2020-03-28-HiMyTamagotchiJiang.md) | 03/28/2020 | University of Michigan in Coursera Chapter 20.13
5 | [Hi MyPokemon](./pycode/2020-03-20-HiMyPokemon.md) | 03/30/2020 | University of Michigan in Coursera Chapter 22.7
6 | [Wheel Of Python](./pycode/2020-03-20-WheelOfPython.md) | 03/30/2020 | University of Michigan in Coursera Chapter 22.8
7 | [Taste Dive](./pycode/2020-04-03-TasteDive.md) | 2020-04-03 | University of Michigan in Coursera Chapter 24.14
8 | [Beautiful Soup](./pycode/2020-05-14-BeautifulSoup.md) | 2020-05-14 | University of Michigan in Coursera Chapter 12
9 | [Building a Better Contact Sheet](./pycode/2020-05-31-Building-a-Better-Contact-Sheet.md) | 2020-05-31 | University of Michigan in Coursera 
10| [Newspaper Face Search](./pycode/2020-08-30-NewspaperFaceSearch.py) | 2020-08-30 | University of Michigan in Coursera

---

:purple_heart: some link:

:star: when the runestone error: use [this link](https://runestone.academy/runestone/books/published/fopp/AdvancedAccumulation/toctree.html) to access the text book

---

Data Structure | [Arrays 101]()





