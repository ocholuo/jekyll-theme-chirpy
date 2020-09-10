---
title: Programming - demo & Learning Path
date: 2020-09-04 11:11:11 -0400
categories: [Project]
tags: [OnePage, Project]
math: true
image: 
---

# Programming - demo & Learning Path


| ᐕ)⁾⁾ como estas~~~~ bien~~~~ y tu?~~~~ yes


---

## Code Demo

---

### java project

Path: `https://github.com/ocholuo/language/blob/master/0.project/javademo/`

Index | Name | Date | Course material | Note
---|---|---|---|---
1 | [PerimeterRunner](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-04-PerimeterRunner/PerimeterRunner.java) | 2020-09-04 | DukeU
2 | [FindingWebLinks](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-04-FindingWebLinks/FindingWebLinks.java) | 2020-09-04 | DukeU
3 | [FindGenieinDNA](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-05-FindGenieinDNA/FindGenieinDNA.java) | 2020-09-05 | DukeU
4 | [CSVofCountryExport](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-05-CSVofCountryExport/CSVofCountryExport.java) | 2020-09-05 | DukeU
5 | [FindHottestDay](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-05-FindHottestDay/FindHottestDay.java) | 2020-09-05 | DukeU
6 | [BabyNames](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-06-BabyNames/BabyNames.java) | 2020-09-06 | DukeU | **use CSVParser to process multiple line:** <br>`String fname = "yob" + year + ".csv";` <br> `FileResource fr = new FileResource(fname); ` <br> `CSVParser parser = fr.getCSVParser(false);` <br> `for(CSVRecord rec : parser){` <br> `System.out.println("Name " + rec.get(0) + " Gender "+ rec.get(1) + " Num Born" + rec.get(2));}`
7 | [RollsSimulate](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-06-RollsSimulate/RollsSimulate.java) | 2020-09-06 | DukeU | **sum two random roll number, make a record array:** <br> `Random rand = new Random();` <br> `for(int i = 0; i < rolls; i++){` <br> `int d1 = rand.nextInt(6) + 1`
8 | [CountShakespeareWords](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-07-CountShakespeareWords/CountShakespeareWords.java) | 2020-09-07 | DukeU | **make a word record array:** <br> crate getCommon().method to pur common word list into a array `String[] common;` <br> to store counts create `int[] counts = new int[common.length];` <br> `for(String word : resource.words())` <br> crate indexOf().method to get the index of common word `if (common[i].equals(word)); return i` <br> crate countWords().method to ++1 according to the index, `if (index != -1) {counts[index] += 1;}` <br> print out line as index `System.out.println(common[k] + "\t" + counts[k]);`
9 | [CaesarCipherAlgorithm](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-07-CaesarCipherAlgorithm/CaesarCipherAlgorithm.java) | 2020-09-07 | DukeU | **encrypt and decrypt the cipher with 1 key or 2 key**
10 | [GladLibs](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-07-GladLibs/GladLibs.java) | 2020-09-07 | DukeU | **ArrayList create random story**
11 | [WordFrequencies](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-07-WordFrequencies/WordFrequencies.java) | 2020-09-07 | DukeU |
12 | [WordFrequenciesMap](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-07-WordFrequenciesMap/WordFrequenciesMap.java) | 2020-09-07 | DukeU | **HashMap**
13 | [CodonCount](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-08-CodonCount/CodonCount.java) | 2020-09-08 | DukeU | **use hashmap to operate dna codon** <br> `buildCodonMap(int start, String dna)` check if idex+=3 dna existed in HashMap <br> `getMostCommonCodon()` check max num in HashMap
14 | [WordsinFiles](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-08-WordsinFiles/WordsinFiles.java) | 2020-09-08 | DukeU | **word - file - directory HashMap create**
14 | [LogEntry](https://github.com/ocholuo/language/blob/master/0.project/javademo/2020-09-09-LogEntry/LogEntry.java) | 2020-09-09 | DukeU | **analyst web log entry**


---

### python project

Path: `https://github.com/ocholuo/language/blob/master/0.project/pycode/`

Index | Name | Date | Course material
---|---|---|---
1 | [Little Turtles Adventure](https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-03-21-LittleTurtlesAdventure.md) | 2020-03-21 | University of Michigan in Coursera Chapter ?? : P
2 | [Are Your Words Happy](https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-03-25-AreYourWordsHappy.md) | 2020-03-25 | University of Michigan in Coursera Chapter 16.10
3 | [Nested Pokemon](https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-03-25-NestedPokemon.md) | 03/25/2020 | University of Michigan in Coursera Chapter 17.8
4 | [Hi MyTamagotchiJiang](https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-03-28-HiMyTamagotchiJiang.md) | 03/28/2020 | University of Michigan in Coursera Chapter 20.13
5 | [Hi MyPokemon](https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-03-20-HiMyPokemon.md) | 03/30/2020 | University of Michigan in Coursera Chapter 22.7
6 | [Wheel Of Python](https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-03-20-WheelOfPython.md) | 03/30/2020 | University of Michigan in Coursera Chapter 22.8
7 | [Taste Dive](https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-04-03-TasteDive.md) | 2020-04-03 | University of Michigan in Coursera Chapter 24.14
8 | [Beautiful Soup](https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-05-14-BeautifulSoup.md) | 2020-05-14 | University of Michigan in Coursera Chapter 12
9 | [Building a Better Contact Sheet](https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-05-31-Building-a-Better-Contact-Sheet.md) | 2020-05-31 | University of Michigan in Coursera
10| [Newspaper Face Search](https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-08-30-NewspaperFaceSearch.py) | 2020-08-30 | University of Michigan in Coursera

`https://github.com/ocholuo/language/blob/master/0.project/pycode/2020-03-20-HiMyPokemon.md`

---

:purple_heart: some link:

:star: when the runestone error: use [this link](https://runestone.academy/runestone/books/published/fopp/AdvancedAccumulation/toctree.html) to access the text book

---

Data Structure | [Arrays 101]()
