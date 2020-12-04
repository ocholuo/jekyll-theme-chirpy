
# java:
# public class Reversestring {
#     public String reverseString(String s){
#         String result = "";
#         for(int i = 0; i < s.length(); i++){
#             result = s.charAt(i) + result;
#         }
#         return result;
#     }
# }


class Stack:
    def __init__(self):
        self._items = []
    def is_empty(self):
        return not bool(self._items)
    def push(self, item):
        self._items.append(item)
    def pop(self):
        return self._items.pop()
    def peek(self):
        return self._items[-1]
    def size(self):
        return len(self._items)


# from test import testEqual
# from pythonds.basic.stack import Stack

def rev_string(my_str):
    s = Stack()
    revstr=''
    for char in my_str:
        s.push(char)
    while not s.is_empty():
        revstr += s.pop()
    return revstr

print(rev_string('apple'))
# testEqual(rev_string('x'), 'x')
# testEqual(rev_string('abc'), 'cba')
