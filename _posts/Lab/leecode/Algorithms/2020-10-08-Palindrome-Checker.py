


# Palindrome-Checker


class Deque:
    def __init__(self):
        self.itmes = []

    def isEmpty(self):
        return self.items == []

    def addFront(self, item):
        self.items.append(item)  # right

    def addRear(self, item):
        self.items.insert(0, item)  # left

    def removeFront(self):
        return self.items.pop()  # right

    def removeRear(self):
        return self.items.pop(0)  # right

    def size(self):
        return len(self.items)
        


def palchecker(aString):
    chardeque = Deque()

    for ch in aString:
        chardeque.addRear(ch)

    stillEqual = True

    while chardeque.size() > 1 and stillEqual:
        first = chardeque.removeFront()
        last = chardeque.removeRear()
        if first != last:
            stillEqual = False
    return stillEqual

palchecker("lsdkjfhd")
palchecker("radar")    




# 