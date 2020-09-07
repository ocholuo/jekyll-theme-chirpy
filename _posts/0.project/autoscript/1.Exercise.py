

# 1. Exercise: Creating and Using Functions

def repeat_the_msg(message, number):
    if number == '':
        output = message
    else:
        output = message*int(number)
    return print(output)

message = input("The message to echo:")
number = input("The number of times to repeat:")

repeat_the_msg(message, number)