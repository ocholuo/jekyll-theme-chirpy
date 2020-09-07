
# Project - Sentiment Classifier (AreYourWordsHappy)

[toc]

From the Python Basic offer by University of Michigan in Coursera: [16.10. Project - Sentiment Classifier](https://fopp.umsi.education/books/published/fopp/Sorting/chapterProject.html)




---

## Assignment

We have provided some synthetic (fake, semi-randomly generated) twitter data in a csv file named project_twitter_data.csv which has the text of a tweet, the number of retweets of that tweet, and the number of replies to that tweet. We have also words that express positive sentiment and negative sentiment, in the files positive_words.txt and negative_words.txt.

Your task is to build a sentiment classifier, which will detect how positive or negative each tweet is. You will create a csv file, which contains columns for the Number of Retweets, Number of Replies, Positive Score (which is how many happy words are in the tweet), Negative Score (which is how many angry words are in the tweet), and the Net Score for each tweet. At the end, you upload the csv file to Excel or Google Sheets, and produce a graph of the Net Score vs Number of Retweets.

---

## Functions

1. def `strip_punctuation(strWord)`:
which takes one parameter, a string which represents a word, and removes characters considered punctuation from everywhere in the word.

2. def `get_pos(strSentences)`:
which takes one parameter, a string which represents a one or more sentences, and calculates how many words in the string are considered positive words. Use the list, positive_words to determine what words will count as positive. The function should return a positive integer - how many occurances there are of positive words in the text.

3. def `get_neg(strSentences)`:
which takes one parameter, a string which represents a one or more sentences, and calculates how many words in the string are considered negative words. Use the list, negative_words to determine what words will count as negative. The function should return a positive integer - how many occurances there are of negative words in the text.

4. def `writeInDataFile(resultingDataFile)`:
which takes one parameter, a string which represents all data in project_twitter_data.csv, and write a csv file called resulting_data.csv, which contains the Number of Retweets, Number of Replies, Positive Score (which is how many happy words are in the tweet), Negative Score (which is how many angry words are in the tweet), and the Net Score (how positive or negative the text is overall) for each tweet. The file have its headers in that order.

```
Data file: project_twitter_data.csv
tweet_text,retweet_count,reply_count
@twitteruser: On now - @Fusion scores first points #FirstFinals @overwatchleague @umich @umsi Michigan Athletics made out of emojis. #GoBlue,3,0
BUNCH of things about crisis respons… available July 8th… scholarship focuses on improving me… in North America! A s… and frigid temperatures,1,0
FREE ice cream with these local area deals: chance to pitch yourself to hundreds of employers? Msi student Name Here is spending her summer,1,2
AWAY from the Internet of Things attacks… right and the left? See… use DIY language to discuss other projects not traditionally viewed as masculine,3,1
IN City Name!… from @twitteruser has some amazing datasets and tools for collective action. - UMSI a… grateful for… equipping elderly African-American,6,0
ENTREPRENEURSHIP in #City.… a great social media job opportunity for our UMSI alumni looking to work at City Name Public Schools! #goblue,9,5
BRINGING #datascience to community researchers with a team of researchers have been helping people in 'lean' economies learn entrepreneurial,19,0
WHY not pay you?… endure crushing pressures and frigid temperatures but the most difficult part of… faculty and staff across the city,0,0
A bunch of things about crisis respons… – but the implications are much bigger t… for some but a financial burden for others. @umichdpss and,0,0
@THEEMMYS nomination for Best Lead Actor in a library role focused on Digital Services and Projects? As a bonus you get to work at t… by @cab938,82,2
THIS headline has been inescapable this summer. Now the infectious chart-topper from 'Scorpion' gets a vi… Seventh Victim 'Scorpion' gets a,0,0
OF wine with a shoe? Yes but it ain't pretty. Its First Amendment rights. That it claims will for… and non-binary musicians shaping the sound,0,0
HAVE detained six people in history to ride to orbit in private space taxis next year if all to faithful them is that they simply can't afford,47,0
PET Name. She is 1 year old Shiba Inu. When her caregiver is not at home Name likes to have… I tell them about my website and ask if my,2,1
YOU’RE welcome! He was a mix of many breeds. He is a 2 year old Yellow Lab. When he was a mix of breeds but has the bark of a road!!,0,2
Name. He is wild and playful. He likes to chase and play with his stuffed elephant! the Dir. Of Human Resources @twitteruser. He is a,0,0
BORDER Terrier puppy. Name is loving and very protective of the people she loves. Name2 is a 3 year old Maltipoo. Name3 is an 8 year old Corgi.,4,6
REASON they did not rain but they will reign beautifully couldn't asked for a crime 80 years in the Spring Name's Last Love absolutely love,19,0
HOME surrounded by snow in my Garden. But City Name people musn't: such a good book: RT @twitteruser The Literature of Conflicted Lands after a,0,0
Data file: positive_words.txt
```

---

## code

```py
inputfile = open("project_twitter_data.csv","r")
outputfile = open("resulting_data.csv","w")

punctuation_chars = ["'", '"', ",", ".", "!", ":", ";", '#', '@']

def strip_punctuation(string):
    for i in string:
        if i in punctuation_chars: string=string.replace(i,'')
    return string

def get_pos(string):
    n=0
    for i in strip_punctuation(string).lower().split():
        if i in positive_words: n+=1
    return n

def get_neg(string):
    n=0
    for i in strip_punctuation(string).lower().split():
        if i in negative_words: n+=1
    return n    

def writedata(outputfile):
    outputfile.write("Number of Retweets, Number of Replies, Positive Score, Negative Score, Net Score")
    outputfile.write("\n")

    linestostrings = inputfile.readlines()
    #print(linestostrings)
    # ['tweet_text,retweet_count,reply_count\n'
    #  '@twitteruser: On now - @Fusion scores first points #FirstFinals @overwatchleague @umich @umsi Michigan Athletics made out of emojis. #GoBlue,3,0\n'...]
    #                                                                                                                                           Retweets, Replies  
    headeroffile = linestostrings.pop(0)

    for lines in linestostrings:
      #   @twitteruser: On now - @Fusion scores first points #FirstFinals @overwatchleague @umich @umsi Michigan Athletics made out of emojis. #GoBlue,3,0
      list = lines.strip().split(',')
      # ['@twitteruser: On now - @Fusion scores first points #FirstFinals @overwatchleague @umich @umsi Michigan Athletics made out of emojis. #GoBlue', '3', '0']
      outputfile.write("{}, {}, {}, {}, {}".format(list[1], list[2], get_pos(list[0]), get_neg(list[0]), (get_pos(list[0])-get_neg(list[0]))))
      #                                            Retweets    Replies    Positive Score      Negative Score           Net Score
      outputfile.write("\n")


# lists of words to use
positive_words = []
with open("positive_words.txt") as pos_f:
    for lin in pos_f:
        if lin[0] != ';' and lin[0] != '\n':
            positive_words.append(lin.strip())

negative_words = []
with open("negative_words.txt") as pos_f:
    for lin in pos_f:
        if lin[0] != ';' and lin[0] != '\n':
            negative_words.append(lin.strip())

with open("project_twitter_data.csv") as filein:
    writedata(outputfile)
    outputfile.close()

# writedata(outputfile)
# inputfile.close()
# outputfile.close()

#
3, 0, 0, 0, 0
1, 0, 2, 2, 0
1, 2, 1, 0, 1
3, 1, 1, 0, 1
6, 0, 2, 0, 2
9, 5, 2, 0, 2
19, 0, 2, 0, 2
0, 0, 0, 3, -3
0, 0, 0, 2, -2
82, 2, 4, 0, 4
0, 0, 0, 1, -1
0, 0, 1, 0, 1
47, 0, 2, 0, 2
2, 1, 1, 0, 1
0, 2, 1, 0, 1
0, 0, 2, 1, 1
4, 6, 3, 0, 3
19, 0, 3, 1, 2
0, 0, 1, 1, 0










.
