



# ===================Python===================
class Stack:
    def __init__(self):
        self.items = []

    def is_empty(self):
        return self.items == []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def peek(self):
        # return self.items[len(self.items)-1]
        return self.items[-1]

    def size(self):
        return len(self.items)


# use stack
def infix_to_postfix(infixexpr):
    prec = {}
    prec["**"] = 4
    prec["*"] = 3
    prec["/"] = 3
    prec["+"] = 2
    prec["-"] = 2
    prec["("] = 1

    s = Stack()
    postfixList = []
    # print(infixexpr)
    tokenList = infixexpr.split(" ")

    for token in tokenList:
        if token.isnumeric():
            postfixList.append(token)
            # print("postfixList =", postfixList)
            # print("s = []", s.items)
        elif token == "(":
            s.push(token)
            # print("postfixList =", postfixList)
            # print("s = []", s.items)
        elif token == ")":
            topToken = s.pop()
            while topToken != "(":
                postfixList.append(topToken)
                topToken = s.pop()
            # print("postfixList =", postfixList)
            # print("s = []", s.items)
        else:
            while (not s.is_empty()) and (prec[s.peek()] >= prec[token]):
                 postfixList.append(s.pop())
            s.push(token)
            # print("postfixList =", postfixList)
            # print("s = []", s.items)

    while not s.is_empty():
        postfixList.append(s.pop())

    print(" ".join(postfixList))
    return " ".join(postfixList)


# infix_to_postfix("( A + B ) * ( C + D )")
# infix_to_postfix("( A + B ) * C")
# infix_to_postfix("A + B * C")
# infix_to_postfix("10 + 3 * 5 / ( 16 - 4 )")
infix_to_postfix("5 * 3 ** ( 4 - 2 )")



# use stack
def do_math(op, op1, op2):
    if op == "*":
        return op1 * op2
    elif op == "/":
        return op1 / op2
    elif op == "+":
        return op1 + op2
    else:
        return op1 - op2

def postfix_eval(postfix_expr):
    s = Stack()
    token_list = postfix_expr.split(" ")
    for token in token_list:
        if token.isnumeric():
            s.push(int(token))
        else:
            rightnum = s.pop()
            leftnum = s.pop()
            result = do_math(token, leftnum, rightnum)
            s.push(result)
    return s.pop()
    
print(postfix_eval("17 10 + 3 * 9 /"))