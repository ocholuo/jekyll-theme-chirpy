
# Project - Turtle

[toc]


From the Python Basic offer by University of Michigan in Coursera.


---

## Assignment

Modify the turtle walk program so that you have two turtles each with a random starting location. Keep the turtles moving until one of them leaves the screen.

---

## code


```py
import turtle
import random

def randangle(turtle):
    randangle=(random.randrange(366))
    if random.randrange(0,2)==1:
        turtle.right(randangle)
    else:
        turtle.left(randangle)

def randpoint(turtle):
    randpoint=(random.randrange(leftBound,rightBound),
               random.randrange(bottomBound,topBound))
    turtle.up()
    turtle.setpos(randpoint)
    turtle.down()

def turtleinscreen(turtle):
    xpos,ypos=turtle.pos()  # (a,b)
    stillin = True
    if xpos < leftBound or rightBound < xpos:
        stillin= False
    if ypos < bottomBound or topBound < ypos:
        stillin= False
    return stillin    

wn=turtle.Screen()
at=turtle.Turtle()
bt=turtle.Turtle()
at.speed(0)
bt.speed(0)
at.shape('turtle')
at.color('red')
bt.shape('turtle')
bt.color('blue')

leftBound = (-wn.window_width() / 2)
rightBound = (wn.window_width() / 2)
topBound = (wn.window_height() / 2)
bottomBound = (-wn.window_height() / 2)

randpoint(at)
randpoint(bt)
print(at.pos())
print(bt.pos())
print(turtleinscreen(at))
print(turtleinscreen(bt))

while turtleinscreen(at) and turtleinscreen(bt):
    randangle(at)
    at.forward(40)
    randangle(bt)
    bt.forward(40)
else:
    print("gameover, someone is out of the screen")
```













.
