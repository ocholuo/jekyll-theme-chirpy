
# Project - HiMyPokemon

[toc]

From the Python Basic offer by University of Michigan in Coursera: [22.7. Chapter Assessment](https://fopp.umsi.education/books/published/fopp/Inheritance/ChapterAssessment.html)


---

## Assignment

The class `Pokemon`: a Pokemon and its leveling and evolving characteristics. An instance of the class is one pokemon that you create.

The class `Grass_Pokemon`: a subclass that inherits from Pokemon but changes some aspects, for instance, the boost values are different.

Functions `opponent`: return which type of pokemon the current type is weak and strong against, as a tuple.
- Grass is weak against Fire and strong against Water
- Ghost is weak against Dark and strong against Psychic
- Fire is weak against Water and strong against Grass
- Flying is weak against Electric and strong against Fighting


---

## code

```py
class Pokemon(object):
    attack = 12
    defense = 10
    health = 15
    p_type = "Normal"

    attackadding=0

    def __init__(self, name, level = 5):
        self.name = name
        self.level = level

    def train(self):
        self.update()
        print("boost: {} attack, {} defense, {} health, {} evolve".format(self.attack_boost, self.defense_boost, self.health_boost, self.evolve))
        self.attack_up()
        self.defense_up()
        self.health_up()
        self.level = self.level + 1
        if self.level % self.evolve == 0:
            print("Evolved!")
            return self.level, "Evolved!"
        else:
            return self.level

    # call by self.train()
    def update(self):
        self.attack_boost = 3
        self.defense_boost = 2
        self.health_boost = 5
        self.evolve = 10      
    # call by self.train()
    def attack_up(self):
        self.attack = self.attack + self.attack_boost
        return self.attack  
    # call by self.train()
    def defense_up(self):
        self.defense = self.defense + self.defense_boost
        return self.defense
    # call by self.train()
    def health_up(self):
        self.health = self.health + self.health_boost
        return self.health


    def opponent(self):
        if self.p_type=='Grass':
            opponentType=('Fire', 'Water')
        elif self.p_type=="Ghost":
            opponentType=('Dark', 'Psychic')
        elif self.p_type=="Fire":
            opponentType=('Water', 'Grass')
        elif self.p_type=="Flying":
            opponentType=('Electric', 'Fighting')
        else:
            opponentType=('None', 'None')
        return opponentType

    
    def __str__(self):
        return "Pokemon name: {}, Type: {}, Level: {}".format(self.name, self.p_type, self.level)+'\n'+"{} attack, {} defense, {} health".format(self.attack, self.defense, self.health)+'\n'

class Grass_Pokemon(Pokemon):
    attack = 15
    defense = 14
    health = 12
    p_type = "Grass"

    attackadding=0

    def train(self):
        self.update()
        print("boost: {} attack, {} defense, {} health, {} evolve".format(self.attack_boost, self.defense_boost, self.health_boost, self.evolve))
        self.defense_up()
        self.health_up()
        self.level = self.level + 1
        self.attack_up()
        if self.level % self.evolve == 0:
            print("Evolved!")
            return self.level, "Evolved!"
        else:
            return self.level

    def update(self):
        self.attack_boost = 2
        self.defense_boost = 3
        self.health_boost = 6
        self.evolve = 12

    def moves(self):
        self.p_moves = ["razor leaf", "synthesis", "petal dance"]

    def attack_up(self):
        self.attackadding = self.attackadding + self.attack_boost
        #print("attackadding:", self.attackadding)
        if self.level>=10:
            self.attack = self.attack + self.attackadding
            self.attackadding=0
        return self.attack

    def __str__(self):
        Pokemon.__str__(self)
        return "I'm Different! Pokemon name: {}, Type: {}, Level: {}".format(self.name, self.p_type, self.level)+'\n'+"{} attack, {} defense, {} health".format(self.attack, self.defense, self.health)+'\n'


class Ghost_Pokemon(Pokemon):
    p_type = "Ghost"

    def update(self):
        self.health_boost = 3
        self.attack_boost = 4
        self.defense_boost = 3

class Fire_Pokemon(Pokemon):
    p_type = "Fire"

class Flying_Pokemon(Pokemon):
    p_type = "Flying"



```














.
