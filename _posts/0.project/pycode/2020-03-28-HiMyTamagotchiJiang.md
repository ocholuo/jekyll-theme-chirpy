
# Project - A Tamagotchi Game (HiMyTamagotchiJiang)

[toc]

From the Python Basic offer by University of Michigan in Coursera: [20.13. Project - Sentiment Classifier](https://fopp.umsi.education/books/published/fopp/Classes/Tamagotchi.html)


---

## Assignment

class Pet.

Each instance of the class will be one electronic pet for the user to take care of. Each instance will have a current state, consisting of three instance variables:
- hunger, an integer
- boredom, an integer
- sounds, a list of strings, each a word that the pet has been taught to say

---

## Functions

the `__init__` method: hunger and boredom are initialized to random values between 0 and the threshold for being hungry or bored.

The `sounds` instance variable is initialized to be a copy of the class variable with the same name.

a `clock_tick` method: increments the boredom and hunger instance variables, simulating the idea that as time passes, the pet gets more bored and hungry.

The `__str__` method: produces a string representation of the pet’s current state, notably whether it is bored or hungry or whether it is happy. It’s bored if the boredom instance variable is larger than the threshold, which is set as a class variable.

To relieve boredom:
- the `teach()` method: teach the pet a new word
- the `hi()` method: interact with the pet

In response to teach(), the pet adds the new word to its list of words.
In response to the hi() method, it prints out one of the words it knows, randomly picking one from its list of known words.

Both hi() and teach() cause an invocation of the `reduce_boredom()` method. It decrements the boredom state by an amount that it reads from the class variable `boredom_decrement`. The boredom state can never go below 0.

the `feed()` method: to relieve hunger

---

## code

```py

import sys
sys.setExecutionLimit(60000)

from random import randrange

# Super class
class Pet(Object):

    boredom_decrement = 5
    hunger_decrement = 5

    boredom_threshold = 5
    hunger_threshold = 10

    sounds = ['Mrrp']


    def __init__(self, name = "Kitty"):
        self.name = name
        self.hunger = randrange(self.hunger_threshold)    # below 10
        self.boredom = randrange(self.boredom_threshold)  # below 5
        self.sounds = self.sounds[:]  # copy the class attribute, so that when we make changes to it, we won't affect the other Pets in the class

    def clock_tick(self):
        self.boredom += 1
        self.hunger += 1

    # 判断心情，call by str, print out the mood.
    def mood(self):
        if self.hunger <= self.hunger_threshold and self.boredom <= self.boredom_threshold:
            return "happy"
        elif self.hunger > self.hunger_threshold:
            return "hungry"
        else:
            return "bored"

    def __str__(self):
        state = "     I'm " + self.name + ". "
        state += " I feel " + self.mood() + ". "
        state += "Hunger {} Boredom {} Words {}".format(self.hunger, self.boredom, self.sounds)
        return state

    def hi(self):
        print(self.sounds[randrange(len(self.sounds))])
        self.reduce_boredom()

    def teach(self, word):
        self.sounds.append(word)
        self.reduce_boredom()

    def feed(self):
        self.reduce_hunger()

    def reduce_hunger(self):
        self.hunger = max(0, self.hunger - self.hunger_decrement)

    def reduce_boredom(self):
        self.boredom = max(0, self.boredom - self.boredom_decrement)

class Cat(Pet):
    sound=['Meow']

    def __init(self, name='Fluffy', meow_count=3):
        Pet.__init__(self)
        self.meow_count=meow_count
        # meow_count=how many times to hi

    def hi(self):
        for i in range(self.meow_count):
            print(self.sounds[randrange(len(self.sounds))])
        self.reduce_boredom()

    # in Cat class, cats express their hunger and boredom a little differently
    # also have an extra instance, variable meow_count.    
    def mood(self):
        #if self.hunger > self.hunger_threshold:
        #    return "hungry"
        #if self.boredom <2:
        #    return "grumpy; leave me alone"
        #elif self.boredom > self.boredom_threshold:
        #    return "bored"
        #elif randrange(2) == 0:
        #    return "randomly annoyed"
        #else:
        #    return "happy"

        if self.hunger <= self.hunger_threshold and self.boredom <= self.boredom_threshold:
            return "happy, I suppose"
        elif self.hunger > self.hunger_threshold:
            return "mmmm...hungry"
        else:
            return "a bit bored"

class Dog(Pet):
    sounds=['Woof','Ruff']

    # in Dog class, Dog pets should express their hunger and boredom differently than generic Pet.    
    def mood(self):
        if self.hunger <= self.hunger_threshold and self.boredom <= self.boredom_threshold:
            return "happy, arf! Happy"
        elif self.hunger > self.hunger_threshold:
            return "hungry already, arrrf"
        else:
            return "bored, so you should play with me"

    def feed(self):
        Pet.feed(self)
        print('Arf! Thanks!')


class Bird(Pet):
     sound=['chirp']
     def __init__(self, name="Kitty", chirp_number=2):
         # call the parent class's constructor
         Pet.__init__(self,name)
         # basically, call the SUPER/parent version -- of the constructor, with all the parameters that it needs.
         # assign the new instance variable
         self.chirp_number=chirp_number


     def hi(self):
         for i in range(self.chirp_number):
           print(self.sounds[randrange(len(self.sounds))])
         self.update_boredom()

class Lab(Dog):

    def fetch(self):
      return "I found the tennis ball!!"

    def hi(self):
      print(self.fetch())
      print(self.sounds[randrange(len(self.sounds))])


class Poodle(Dog):

    def dance(self):
        return "Dancin' in circles like poodles do."

    def hi(self):
        print(self.dance())
        Dog.hi(self)


class Tiger(Cat):

    def __init__(self,name,meow_count=5):
        Cat.__init__(self)
        self.meow_count=meow_count

    def roar(self):
        print('ROOOOOAR!')
    def hi(self):

        Pet.hi(self)
        self.roar()


class Retriever(Lab):

    def fetch(self):
        Dog.fetch(self)
        print('I found the tennis ball! I can fetch anything!')


# class method
def whichone(petlist,name):
    for pet in petlist:
        if pet.name == name:
             return pet
    return None # No pet matched

pet_types = {'tiger':Tiger, 'dog': Dog, 'lab': Lab, 'poodle': Poodle, 'cat': Cat, 'bird': Bird}
def whichtype(adopt_type="general pet"):
    return pet_types.get(adopt_type.lower(), Pet)


# interact
def play():

    animal=[]
    option=''

    base_prompt="""
        Quit
        Adopt <petname_with_no_spaces> <pet_type_from_dog, cat, lab, poodle, bird, or another unknown pet type>
        Greet <petname>         pet.hi()
        Teach <petname> <word>  pet.teach(words[2])
        Feed <petname>          pet.feed()

        Choice:
        """
    feedback=''

    while True:
        # show the guid and get the input
        action=input(feedback+'\n'+base_prompt)
        feedback=''
        # split the input
        words=action.split()

        if len(words)>0:
          command=words[0]
        else:
          command=None

        if command=='Quit':
           print('Exiting...')
           return

        elif command=='Adopt' and len(words) > 1:
            # check if words[1] pet name already in inside.
            if whichone(animals,words[1]):
               feedback += "You already have a pet with that name\n"
            else:
               # add the new animals
               # what class:
               if len(words)>2:
                   Cl=whichtype(words[2])
               else:
                   Cl=Pet
               # Make an instance of that class. Pet(name)
               # append it to the animals list
               animals.append(cl(words[1]))

        elif command=="Greet" and len(words) > 1:
            pet = whichone(animals, words[1])
            if not pet:
               feedback += "I didn't recognize that pet name. Please try again.\n"
               print(feedback)
            else:
               pet.hi()

        elif command == "Teach" and len(words) > 2:
            pet = whichone(animals, words[1])
            if not pet:
                feedback += "I didn't recognize that pet name. Please try again."
                print(feedback)
            else:
                pet.teach(words[2])

        elif command == "Feed" and len(words) > 1:
            pet = whichone(animals, words[1])
            if not pet:
                feedback += "I didn't recognize that pet name. Please try again."
                print(feedback)
            else:
                pet.feed()

        else:
           feedback+= "I didn't understand that. Please try again."
           print(feedback)

        for pet in animals:
            pet.clock_tick()
            feedback += "\n" + pet.__str__()
        # show guid
        # user input
        # adopt add the animals lists
        # and each action ends with for loop
        # print out the animals list items

# input: Adopt K cats
# add line in the head:
# I'm K. I feel randomly annoyed. Hunger 5 Boredom 5 Words ['Meow']

play()




p1 = Pet("Fido")
#print('hi, My name is {}, and My conditions:'.format(p1.name))
print(p1,'\n')

for i in range(10):  # pass 10 hours
    p1.clock_tick()
    #print(i+1, 'hours passed:')
print(p1,'\n')

p1.feed()  # reduce_hunger -5
#print('After feed:')
print(p1,'\n')

p1.hi()    # reduce_boredom -5
#print('After one hi:')
print(p1,'\n')

p1.teach("Boo")
for i in range(10):
    p1.hi()
#print('After ten times hi:')
print(p1,'\n')

#print('After feed and hi, and My conditions:')
#print(p1)











.
