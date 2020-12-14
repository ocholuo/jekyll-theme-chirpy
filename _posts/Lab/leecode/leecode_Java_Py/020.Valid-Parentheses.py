

# 20. Valid Parentheses
# https://leetcode.com/problems/valid-parentheses/
# Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

# An input string is valid if:

# Open brackets must be closed by the same type of brackets.
# Open brackets must be closed in the correct order.
# Note that an empty string is also considered valid.


# ===================================JAVA=============================================
# import java.util.HashMap;
# import java.util.Stack;
# class Solution {
#     // Hash table that takes care of the mappings.
#     private HashMap<Character, Character> mappings;
  
#     // Initialize hash map with mappings. This simply makes the code easier to read.
#     public Solution() {
#       this.mappings = new HashMap<Character, Character>();
#       this.mappings.put(')', '(');
#       this.mappings.put('}', '{');
#       this.mappings.put(']', '[');
#     }
  
#     public boolean isValid(String s) {
  
#       // Initialize a stack to be used in the algorithm.
#       Stack<Character> stack = new Stack<Character>();
  
#       for (int i = 0; i < s.length(); i++) {
#         char c = s.charAt(i);
  
#         // If the current character is a closing bracket.
#         // Input: ( )[]{}, ([)]
#         if (this.mappings.containsKey(c)) {
  
#           // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
#           char topElement = stack.empty() ? '#' : stack.pop();
  
#           // If the mapping for this bracket doesn't match the stack's top element, return false.
#           if (topElement != this.mappings.get(c)) {
#             return false;
#           }
#         } else {
#           // If it was an opening bracket, push to the stack.
#           stack.push(c);
#         }
#       }
#       // If the stack still contains elements, then it is an invalid expression.
#       return stack.isEmpty();
#     }

# 	public Object findDisappearedNumbers(int[] arr) {
# 		return null;
# 	}
# }


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


# class Solution1:
#     def isValid(self, s: str) -> bool:
#         newstr=''
#         for i in s:
#             if i in ['(',')','{','}','[',']']:
#                 newstr+=i
#         while '()' in newstr or '{}' in newstr or '[]' in newstr:
#             newstr=newstr.replace('()','').replace('{}','').replace('[]','')
#         return newstr ==''
# # Runtime: 40 ms, faster than 17.44% of Python3 online submissions for Valid Parentheses.
# # Memory Usage: 13.7 MB, less than 6.09% of Python3 online submissions for Valid Parentheses.


# class Solution2:
#     def isValid(self, s: str) -> bool:
#         while '()' in s or '{}' in s or '[]' in s:
#             s=s.replace('()','').replace('{}','').replace('[]','')
#         return s ==''
# # Runtime: 80 ms, faster than 6.27% of Python3 online submissions for Valid Parentheses.
# # Memory Usage: 14 MB, less than 5.22% of Python3 online submissions for Valid Parentheses.


# class Solution3:  use list and dictionary
#     def isValid(self, s):
#         bracket_map = {"(": ")", "[": "]",  "{": "}"}
#         open_par = set(["(", "[", "{"])
#         stack = []
#         for i in s:
#             if i in open_par:                             # 如果是开口
#                 stack.append(i)
#             elif stack and i == bracket_map[stack[-1]]:   # 如果不是开口，stack有东西，最后一个正好match
#                 stack.pop()
#             else:
#                 return False
#         return stack == []
# # Runtime: 32 ms, faster than 46.59% of Python3 online submissions for Valid Parentheses.
# # Memory Usage: 13.8 MB, less than 5.22% of Python3 online submissions for Valid Parentheses.



# class Solution4: use stack
#     def par_checker(symbol_string):
#         s = Stack()
#         bracket_map = {"(": ")", "[": "]",  "{": "}"}
#         for symbol in "([{":
#             if symbol in open_par:
#                 s.push(symbol)
#             elif (not s.is_empty()) and symbol == bracket_map.get(s.peek()):
#                 s.pop()
#             else:
#                 return False
#         return s.is_empty()


# class Solution5:  # stack
# def matches(opener, closer):
#     openers = "({["
#     closers = ")}]"
#     return openers.index(opener) == closers.index(closer)
# def par_checker(symbol_string):
#     s = Stack()
#     for i in symbol_string:
#         if i in "([{":
#             s.push(i)
#         elif (not s.is_empty()) and matches(s.pop(),i):
#             continue
#         else:
#             return False
#     return s.is_empty()


# print(par_checker("((())(()"))  #expected False
# print(par_checker("((()))"))  # expected True
# print(par_checker("((()()))"))  # expected True
# print(par_checker("(()"))  # expected False
# print(par_checker(")("))  # expected False





# //