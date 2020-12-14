

# The decimal number 23310 and its corresponding binary equivalent 111010012 are interpreted respectively as
# 2×102+3×101+3×100
# and
# 1×27+1×26+1×25+0×24+1×23+0×22+0×21+1×20


# ===================================Python=============================================
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

# convert-integer-into-base(16)
# Class solution1: stack, put num%16 in stack
def divide_by_base(num, base):
    digits = "0123456789ABCDEF"
    s = Stack()
    while num > 0:
        rem = num % base
        s.push(rem)
        num = num // base
    ans_string = ""
    while not s.is_empty():
        ans_string += str(digits[s.pop()])
    return ans_string

print(divide_by_base(25,2))
print(divide_by_base(25,16))
print(divide_by_base(25,8))
print(divide_by_base(256,16))
print(divide_by_base(26,26))

# convert-integer-into-binary
# Class solution1: stack, put num%2 in stack
# def divide_by_2(decimal_num):
#     s = Stack()
#     while decimal_num > 0:
#         rem = decimal_num % 2
#         s.push(rem)
#         decimal_num = decimal_num // 2
#     bin_string = ""
#     while not s.is_empty():
#         bin_string += str(s.pop())
#     return bin_string

# print(divide_by_2(42))
# print(divide_by_2(31))



# The decimal number 233 and its corresponding octal and hexadecimal equivalents 3518 and 𝐸916 are interpreted as
# 3×82+5×81+1×80
# and
# 14×161+9×160

# convert-integer-into-base(16)
# Class solution1: stack, put num%16 in stack
# def divide_by_16(decimal_num):
#     digits = "0123456789ABCDEF"
#     s = Stack()
#     while decimal_num > 0:
#         rem = decimal_num % 16
#         s.push(rem)
#         decimal_num = decimal_num // 16

#     bin_string = ""
#     while not s.is_empty():
#         bin_string += str(digits[s.pop()])
#     return bin_string

# print(divide_by_16(42))
# print(divide_by_16(31))