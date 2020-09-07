
# Project - WheelofPython

[toc]

From the Python Basic offer by University of Michigan in Coursera: [22.8. Project - Wheel of Python](https://fopp.umsi.education/books/published/fopp/Inheritance/chapterProject.html)


---

## Assignment

Rules of the game:

1. There are `num_human` human players and `num_computer` computer players.
Every player has some amount of money ($0 at the start of the game)

Every player has a set of prizes (none at the start of the game)

2. The goal is to guess a phrase within a category. For example:
Category: Artist & Song

Phrase: Whitney Houston’s I Will Always Love You

3. Players see the category and an obscured version of the phrase where every alphanumeric character in the phrase starts out as hidden (using underscores: _):
Category: Artist & Song

Phrase: _______ _______'_ _ ____ ______ ____ ___

Note that case (capitalization) does not matter

4. During their turn, every player spins the wheel to determine a prize amount and:
If the wheel lands on a cash square, players may do one of three actions:
Guess any letter that hasn’t been guessed by typing a letter (a-z)
Vowels (a, e, i, o, u) cost $250 to guess and can’t be guessed if the player doesn’t have enough money. All other letters are “free” to guess

The player can guess any letter that hasn’t been guessed and gets that cash amount for every time that letter appears in the phrase

If there is a prize, the user also gets that prize (in addition to any prizes they already had)

If the letter does appear in the phrase, the user keeps their turn. Otherwise, it’s the next player’s turn

Example: The user lands on $500 and guesses ‘W’
There are three W’s in the phrase, so the player wins $1500

Guess the complete phrase by typing a phrase (anything over one character that isn’t ‘pass’)
If they are correct, they win the game

If they are incorrect, it is the next player’s turn

Pass their turn by entering 'pass'

If the wheel lands on “lose a turn”, the player loses their turn and the game moves on to the next player

If the wheel lands on “bankrupt”, the player loses their turn and loses their money but they keep all of the prizes they have won so far.

5. The game continues until the entire phrase is revealed (or one player guesses the complete phrase)


```py

# functions and methods Using

import time

for x in range(2, 6):
    print('Sleep {} seconds..'.format(x))
    time.sleep(x) # "Sleep" for x seconds
print('Done!')

-----------------

import random

rand_number = random.randint(1, 10)
print('Random number between 1 and 10: {}'.format(rand_number))

letters = [letter for letter in 'ABCDEFGHIJKLMNOPQRSTUVWXYZ']
rand_letter = random.choice(letters)
print('Random letter: {}'.format(rand_letter))

-----------------

myString = 'Hello, World! 123'

print(myString.upper()) # HELLO, WORLD! 123
print(myString.lower()) # hello, world! 123
print(myString.count('l')) # 3

s = 'python is pythonic'
print(s.count('python')) # 2

-----------------
```

---

### players

Part A: WOFPlayer

class `WOFPlayer`: Wheel of Fortune player. Every instance of WOFPlayer has three instance variables:
- `.name`: The name of the player (should be passed into the constructor)
- `.prizeMoney`: The amount of prize money for this player (an integer, initialized to 0)
- `.prizes`: The prizes this player has won so far (a list, initialized to [])

Functions:
- def `addMoney(amt)`: Add amt to self.prizeMoney
- def `goBankrupt()`: Set self.prizeMoney to 0
- def `addPrize(prize)`: Append prize to self.prizes
- def `__str__()`: Returns the player’s name and prize money in the following format: Steve ($1800) (for a player with instance variables .name == 'Steve' and prizeMoney == 1800)

---

Part B: WOFHumanPlayer

class `WOFHumanPlayer`: inherit from class `WOFPlayer`, represent a human player.
- have all of the instance variables and methods `WOFPlayer` has

Have an additional method:
- `.getMove(category, obscuredPhrase, guessed)`: ask the user to enter a move (using input()) and return whatever string they entered. The user can then enter:

.getMove()’s prompt should be:

> {name} has ${prizeMoney}
> Category: {category}
> Phrase:  {obscured_phrase}
> Guessed: {guessed}
> Guess a letter, phrase, or type 'exit' or 'pass':

example:

> Steve has $200
> Category: Places
> Phrase: `_L___ER N____N_L P_RK`
> Guessed: B, E, K, L, N, P, R, X, Z
> Guess a letter, phrase, or type 'exit' or 'pass':

  - `exit` to exit the game
  - `pass` to skip their turn
  - a single character to guess that letter
  - a complete phrase (a multi-character phrase other than 'exit' or 'pass') to guess that phrase

Note that .getMove() does not need to enforce anything about the user’s input; that will be done via the game logic that we define in the next ActiveCode window.

---

Part C: WOFComputerPlayer

class `WOFComputerPlayer`: inherit from WOFPlayer, represent a computer player.
- have all of the instance variables and methods that WOFPlayer has.

also have:

Class variable

- `.SORTED_FREQUENCIES`: Should be set to 'ZQXJKVBPYGFWMUCLDRHSNIOATE', which is a list of English characters sorted from least frequent ('Z') to most frequent ('E'). We’ll use this when trying to make a “good” move.

Additional Instance variable

- `.difficulty`: The level of difficulty for this computer (should be passed as the second argument into the constructor after .name)

Methods

- `.smartCoinFlip()`: This method will help us decide semi-randomly whether to make a “good” or “bad” move. A higher difficulty should make us more likely to make a “good” move. Implement this by choosing a random number between 1 and 10 using random.randint(1, 10) (see above) and returning True if that random number is greater than self.difficulty. If the random number is less than or equal to self.difficulty, return False.

- `.getPossibleLetters(guessed)`: This method should return a list of letters that can be guessed.
  - These should be characters that are in LETTERS ('ABCDEFGHIJKLMNOPQRSTUVWXYZ') but not in the guessed parameter.
  - if this player doesn’t have enough `prize money` to guess a vowel (variable VOWEL_COST set to 250), then vowels (variable VOWELS set to 'AEIOU') should not be included

- `.getMove(category, obscuredPhrase, guessed)`: Should return a valid move.
  - Use the `.getPossibleLetters(guessed)` method.
  - If there aren’t any letters that can be guessed (happen if the only letters left to guess are vowels and the player doesn’t have enough for vowels), return 'pass'
  - Use the `.smartCoinFlip()` method to decide whether to make a “good” or a “bad” move
    - If making a “good” move (.smartCoinFlip() returns True), then return the most frequent (highest index in .SORTED_FREQUENCIES) possible character
    - If making a “bad” move (.smartCoinFlip() returns False), then return a random character from the set of possible characters (use random.choice())



```py

# WOFPlayer base class
class WOFPlayer():
    def __init__(self, name, prizeMoney=0, prizes=[]):
        self.name=name              # name of the player
        self.prizeMoney=prizeMoney  # The amount of prize money for this player
        self.prizes=prizes          # The prizes this player has won so far

    # add amt to priceMoney
    def addMoney(self, amt):        # addMoney(self, amt: int) -> str:
        self.prizeMoney = self.prizeMoney + amt  # not asking for return value

    # Set self.prizeMoney to 0
    def goBankrupt(self):
        self.prizeMoney=0

    # add prize to list prizes
    def addPrize(self, prize):
        self.prize = self.prize + prize

    # Returns the player’s name and prize money
    def __str__():
        return "{} (${})".format(self.name, self.prizeMoney)


# WOFHumanPlayer CLASS
class WOFHumanPlayer(WOFPlayer):
    #prompt="""
    #{} has ${}
    #Category: {}
    #Phrase:  {}
    #Guessed: {}
    #Guess a letter, phrase, or type 'exit' or 'pass':
    #""".format(self.name, self.prizeMoney, category, obscured_phrase, ', '.join(sorted(guessed)) )

    # def getMove(self, category: str, obscuredPhrase: str, guessed: list) -> str:
    def getMove(self, category, obscuredPhrase, guessed):
        print('{} has ${}, Category: {}, Phrase:   {}, Guessed:  {}'.format(self.name, self.prizeMoney, category, obscuredPhrase, ', '.join(sorted(guessed))))
        userinput=input("Guess a letter, phrase, or type 'exit' or 'pass': ")
        return userinput
        #userin=userinput.lower()
        #try:
        #    if len(userin)==1:
        #        guessed.append(userin)
        #    if len(userin)>1:
        #        if userin=='exit':
        #            print('pass')
        #        if userin=='pass':
        #            print('pass')
        #        else:
        #            print('pass')
        #except Exception, e:
        #    print(e)



# computer player.
class WOFComputerPlayer(WOFPlayer):

    # Class variable
    SORTED_FREQUENCIES='ZQXJKVBPYGFWMUCLDRHSNIOATE'   # list of English characters frequency
    LETTERS='ABCDEFGHIJKLMNOPQRSTUVWXYZ'

    VOWELS='AEIOU'
    VOWEL_COST=250

    # def __init__(self, name: str, difficulty: int, prizeMoney: int, prizes: str) -> None:
    def __init__(self, name, difficulty, prizeMoney, prizes):
        WOFPlayer.__init__(self, name, prizeMoney, prizes)
        self.difficulty=difficulty # semi-randomly choice whether 'good' or 'bad'

    def smartCoinFlip(self):
        randomnum=random.randint(1, 10)
        # higher the self.difficulty
        # less likely to be True
        if randomnum > self.difficulty:
            return True
        else:
            return False

    # def getPossibleLetters(self, guessed: list) -> list:
    def getPossibleLetters(self, guessed):
        possible_letters = []
        impossible_letters = []
        if self.prizeMoney <250:
            for vowel in VOWELS:
                impossible_letters.append(vowel)
        impossible_letters += guessed
        for letter in LETTERS:
            if letter not in impossible_letters:
                possible_letters.append(letter)         
        return possible_letters


    # def getMove(self, category: str, obscuredPhrase: str, guessed: list) -> str:
    def getMove(self, category, obscuredPhrase, guessed):
        possible_letters=self.getPossibleLetters(guessed)
        if possible_letters==[]:
            return 'pass'
        else:
            if self.smartCoinFlip()==True:
                # return the most frequent (highest index in .SORTED_FREQUENCIES) possible character
                for letter in self.SORTED_FREQUENCIES[::-1]:
                    if letter in possible_letters:
                        return letter
            else:
                # return a random character from the set of possible characters (use random.choice())
                return random.choice(possible_letters)
```


### game logic code


```py

# sys and its functions are only used for the autograder, setExecutionLimit is not in the standard sys-module!


# ensure that the Python interpreter gives our game time to run:
import sys
sys.setExecutionLimit(600000)    # take up to 10 minutes

import json
import random
import time

LETTERS = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
VOWELS  = 'AEIOU'
VOWEL_COST  = 250

# 1.
# Repeatedly asks the user for a number between min & max (inclusive)
def getNumberBetween(prompt提示, min, max):
    userinp = input(prompt) # ask the first time

    # test the value input
    while True:
      try:
        n = int(userinp) # try casting to an integer
        if n < min:
            errmessage = 'Must be at least {}'.format(min)
        elif n > max:
            errmessage = 'Must be at most {}'.format(max)
        else:
            return n
    except ValueError: # The user didn't enter a number
        errmessage = '{} is not a number.'.format(userinp)

    # If we haven't gotten a number yet, add the error message and ask again
    userinp = input('{}\n{}'.format(errmessage, prompt))


2.
# Spins the wheel of fortune wheel to give a random prize
# Examples:
# file: "wheel.json"
#    { "type": "cash", "text": "$950", "value": 950, "prize": "A trip to Ann Arbor!" },
#    { "type": "bankrupt", "text": "Bankrupt", "prize": false },
#    { "type": "loseturn", "text": "Lose a turn", "prize": false }

def spinWheel():
    with open("wheel.json", 'r') as f:
        wheel = json.loads(f.read())
        # random choose a list
        return random.choice(wheel)



3.
# Returns a category & phrase (as a tuple) to guess
# file: "phrases.json"
#     ("Artist & Song", "Whitney Houston's I Will Always Love You")

# getRandomCategoryAndPhrase()
# -> ('Artist & Song', 'GLACIER NATIONAL PARK')
def getRandomCategoryAndPhrase():
    with open("phrases.json", 'r') as f:
        phrases = json.loads(f.read())
        # phrases= category:phrase
        category = random.choice(list(phrases.keys()))
        phrase   = random.choice(phrases[category])
        return (category, phrase.upper())


4.
# Given a phrase and a list of guessed letters, returns an obscured version
# Example:
#     guessed: ['L', 'B', 'E', 'R', 'N', 'P', 'K', 'X', 'Z']
#     phrase:  "GLACIER NATIONAL PARK"
#     returns> "_L___ER N____N_L P_RK"

# obscurePhrase('GLACIER NATIONAL PARK', ['L', 'B', 'E', 'R', 'N', 'P', 'K', 'X', 'Z'])
# -> _L___ER N____N_L P_RK
def obscurePhrase(phrase, guessed):
    rv = ''
    for s in phrase:
        if (s in LETTERS) and (s not in guessed):
            rv = rv+'_'
        else:
            rv = rv+s
    return rv


5.
# Returns a string representing the current state of the game
# category:"Artist & Song"
# obscuredPhrase: "_L___ER N____N_L P_RK" from obscurePhrase(phrase, guessed)
# guessed: ['L', 'B', 'E', 'R', 'N', 'P', 'K', 'X', 'Z']

# obscured_phrase = obscurePhrase(phrase, guessed)

# showBoard(category, obscuredPhrase, guessed)
# showBoard('GLACIER NATIONAL PARK', '_L___ER N____N_L P_RK', ['L', 'B', 'E', 'R', 'N', 'P', 'K', 'X', 'Z'])
# ->
# Category: Artist & Song
# Phrase:   _L___ER N____N_L P_RK
# Guessed:  L,B,E,R,N,P,K,X,Z

def showBoard(category, obscuredPhrase, guessed):
    return """
Category: {}
Phrase:   {}
Guessed:  {}""".format(category, obscuredPhrase, ', '.join(sorted(guessed)))



# GAME LOGIC CODE

print('='*15)
print('WHEEL OF PYTHON')
print('='*15)
print('')


# Create the human player instances
num_human = getNumberBetween('How many human players?', 0, 10)

human_players = [WOFHumanPlayer(input('Enter the name for human player #{}'.format(i+1))) for i in range(num_human)]

  #for i in range(num_human):
  #  human_players=[WOFHumanPlayer ( input( 'Enter the name for player #{}'.format(i+1) ) )

  #human_players = [WOFHumanPlayer(input('Enter the name for player #{}'.format(i+1)), 0, []) for i in range(num_human)]


# Create the player instances
num_computer = getNumberBetween('How many computer players?', 0, 10)

# If there are computer players, ask how difficult they should be
if num_computer >= 1:
    difficulty = getNumberBetween('What difficulty for the computers? (1-10)', 1, 10)


# def __init__(self, name, difficulty, prizeMoney=0, prizes=[])
# MM: added 0 and [] to player!
computer_players = [WOFComputerPlayer('Computer {}'.format(i+1), difficulty, 0, []) for i in range(num_computer)]

  #computer_players = [WOFComputerPlayer('Computer {}'.format(i+1), difficulty) for i in range(num_computer)]

  #for i in range(num_computer):
  #  computer_players=[WOFComputerPlayer('Computer {}'.format(i+1), difficulty)


players = human_players + computer_players

# No players, no game :(
if len(players) == 0:
    print('We need players to play!')
    raise Exception


==========================================
below still need times to go though it
==========================================

category, phrase = getRandomCategoryAndPhrase()
guessed = []

for x in range(random.randint(10, 20)):    # x letters
    randomLetter = random.choice(LETTERS)
    if randomLetter not in guessed:
        guessed.append(randomLetter)


category, phrase = getRandomCategoryAndPhrase()
guessed = []

playerIndex = 0

winner = False
while True:
    player = players[playerIndex]
    wheelPrize = spinWheel()

    print('-'*15)
    print(showBoard(category, obscurePhrase(phrase, guessed), guessed))
    print('')
    print('{} spins...'.format(player.name))
    time.sleep(2)
    print('{}!'.format(wheelPrize['text']))

    if wheelPrize['type'] == 'bankrupt':
        player.goBankrupt()
    elif wheelPrize['type'] == 'cash':
        move = player.getMove(category, obscurePhrase(phrase, guessed), guessed)
        move = move.upper()
        if move == 'EXIT':
            break
        elif move != 'PASS':
            if len(move) == 1:
                if move not in LETTERS:
                    print('Guesses should be alphanumeric. Try again.')
                    continue
                if move in guessed:
                    print('{} has already been guessed. Try again.'.format(move))
                    continue

                if move in VOWELS:
                    if player.prizeMoney < VOWEL_COST:
                        print('Need {} to guess a vowel. Try again.'.format(VOWEL_COST))
                        continue
                    else:
                        player.prizeMoney -= VOWEL_COST

                guessed.append(move)

                print('{} says "{}"'.format(player.name, move))

                count = phrase.count(move)
                if count > 0:
                    if count == 1:
                        print("There is one {}".format(move))
                    else:
                        print("There are {} {}'s".format(count, move))

                    player.addMoney(count * wheelPrize['value'])

                    if wheelPrize['prize']:
                        player.addPrize(wheelPrize['prize'])

                    if obscurePhrase(phrase, guessed) == phrase:
                        winner = player
                        break

                    continue

                elif count == 0:
                    print("There is no {}".format(move))
            else:
                if move == phrase:
                    player.addMoney(wheelPrize['value'])
                    if wheelPrize['prize']:
                        player.addPrize(wheelPrize['prize'])
                    winner = player
                    break
                else:
                    print('{} was not the phrase'.format(move))

    # Move on to the next player (or go back to player[0] if we reached the end)
    playerIndex = (playerIndex + 1) % len(players)

if winner:
    print('{} wins! The phrase was {}'.format(winner.name, phrase))
    print('{} won ${}'.format(winner.name, winner.prizeMoney))
    if len(winner.prizes) > 0:
        print('{} also won:'.format(winner.name))
        for prize in winner.prizes:
            print('    - {}'.format(prize))
else:
    print('Nobody won.')

# mike = WOFHumanPlayer('Mike', 0, [])
# mike.addMoney(250)
# mike.getMove('Music', '__A__ _B__', 'A, B')

num_times_to_spin = random.randint(2, 5)
print('Spinning the wheel {} times (normally this would just be done once per turn)'.format(num_times_to_spin))

for x in range(num_times_to_spin):
    print("\n{}\n".format("-"*2))
    print("spinWheel()")
    # get lines from "wheel.json"
    print(spinWheel())

print("\n{}\n".format("-"*5))

print("In 2 seconds, will run getNumberBetween('Testing getNumberBetween(). Enter a number between 1 and 10', 1, 10)")

time.sleep(2)

# userinp = input('Testing getNumberBetween(). Enter a number between 1 and 10')
print(getNumberBetween('Testing getNumberBetween(). Enter a number between 1 and 10', 1, 10))



```















.
