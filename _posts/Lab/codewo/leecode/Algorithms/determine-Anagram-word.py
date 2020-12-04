
# "heart" and "earth" are anagrams. 
# "python" and "typhon" are anagrams 

print(anagram_solution_1("apple", "pleap"))  # expected: True
print(anagram_solution_1("abcd", "dcba"))  # expected: True
print(anagram_solution_1("abcd", "dcda"))  # expected: False

# Solution 1: Checking Off
# this solution is 𝑂(𝑛2).
# each of the n characters in s1 will iterate through up to n characters in the list from s2
# Each of the n positions in the list will be visited once to match a character from s1. 
def anagram_solution_1(s1, s2):
    still_ok = True

    if len(s1) != len(s2):
        still_ok = False

    a_list = list(s2)
    pos_1 = 0

    while pos_1 < len(s1) and still_ok:
        pos_2 = 0
        found = False
        while pos_2 < len(a_list) and not found:
            if s1[pos_1] == a_list[pos_2]:
                found = True
            else:
                pos_2 = pos_2 + 1

        if found:
            a_list[pos_2] = None
        else:
            still_ok = False

        pos_1 = pos_1 + 1

    return still_ok


# Solution 2: Sort and Compare
# this solution have the same order of magnitude as that of the sorting process.
def anagram_solution_2(s1, s2):
    a_list_1 = list(s1)
    a_list_2 = list(s2)

    # sorting is typically either 𝑂(𝑛^2) or 𝑂(𝑛log𝑛)
    a_list_1.sort()
    a_list_2.sort()

    pos = 0
    matches = True

    while pos < len(s1) and matches:
        if a_list_1[pos] == a_list_2[pos]:
            pos = pos + 1
        else:
            matches = False
    return matches


# Solution 3: Brute Force

# generate a list of all possible strings using the characters from s1 and then see if s2 occurs. 
# When generating all possible strings from s1
    # n possible first characters, 
    # 𝑛−1 possible characters for the second position, 
    # 𝑛−2 for the third, 
    # and so on. 
    # The total number of candidate strings is 𝑛∗(𝑛−1)∗(𝑛−2)∗...∗3∗2∗1, which is 𝑛!. 
# Although some of the strings may be duplicates, the program cannot know this ahead of time and so it will still generate 𝑛! different strings.
# 𝑛! grows even faster than 2^𝑛 as n gets large. 
    # if s1 were 20 characters long, there would be 20!=2,432,902,008,176,640,000 possible strings. 
    # processed one possibility/second, take 77,146,816,596 years to go through the entire list. 


# Solution 4: Count and Compare
# any two anagrams will have the same number of a’s, the same number of b’s, the same number of c’s, and so on.
# Again, the solution has a number of iterations. However, unlike the first solution, none of them are nested. 
# The first two iterations used to count the characters are both based on n. 
# The third iteration, comparing the two lists of counts, always takes 26 steps since there are 26 possible characters in the strings. 
# Adding it all up gives us 𝑇(𝑛)=2𝑛+26 steps. That is 𝑂(𝑛)
def anagram_solution_4(s1, s2):
    c1 = [0] * 26
    c2 = [0] * 26

    for i in range(len(s1)):
        pos = ord(s1[i]) - ord("a")
        c1[pos] = c1[pos] + 1

    for i in range(len(s2)):
        pos = ord(s2[i]) - ord("a")
        c2[pos] = c2[pos] + 1

    j = 0
    still_ok = True

    while j < 26 and still_ok:
        if c1[j] == c2[j]:
            j = j + 1
        else:
            still_ok = False
    return still_ok













# .
