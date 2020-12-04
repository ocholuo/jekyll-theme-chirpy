
# 1. Two Sum

# Given an array of integers, return indices of the two numbers such that they add up to a specific target.
# You may assume that each input would have exactly one solution, and you may not use the same element twice.

# Example:
# nums = [2, 7, 11, 15], target = 9,
# Because nums[0] + nums[1] = 2 + 7 = 9,
# return [0, 1].


class Solution(object):
    def twoSum(self, nums, target):
        dictionary = dict()
        pos = 0
        while pos < len(nums):
            if (target - nums[pos]) not in dictionary:
                dictionary[nums[pos]] = pos
                pos += 1
            else:
                return [dictionary[target - nums[pos]], pos]
# Runtime: 28 ms, faster than 98.66% of Python online submissions for Two Sum.
# Memory Usage: 14.1 MB, less than 5.13% of Python online submissions for Two Sum.


class Solution(object):  
    def twoSum(self, nums, target):
        h = {}
        for index, value in enumerate(nums):  # index, nums_value
            num = target - value
            if num not in h:
                h[value] = index 
            else: 
                return [h[num], index]
# Runtime: 96 ms, faster than 33.28% of Python3 online submissions for Two Sum.
# Memory Usage: 15.3 MB, less than 5.11% of Python3 online submissions for Two Sum.


class Solution(object):
    def twoSum(self, nums, target):
            a = {}
            for i, v in enumerate(nums):
                j = target - v
                if j in a:
                    return [a[j], i]
                a[v] = i
            # return []      Runtime: 64 ms
# Runtime: 96 ms, faster than 33.28% of Python3 online submissions for Two Sum.
# Memory Usage: 15 MB, less than 9.06% of Python3 online submissions for Two Sum.       


class Solution(object):
    def twoSum(self, nums, target):
        a ={}
        for i, num in enumerate(nums):
            if target-num in a:
                return [a[target - num], i]
            else:
                a[num] = i
# Runtime: 28 ms, faster than 98.66% of Python online submissions for Two Sum.
# Memory Usage: 14.2 MB, less than 5.13% of Python online submissions for Two Sum.


if __name__ == '__main__':
    # begin
    s = Solution()
    print s.twoSum([3, 2, 4], 6)


