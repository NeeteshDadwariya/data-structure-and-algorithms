#https://www.lintcode.com/problem/916/

#Given a string, determine if a permutation of the string could form a palindrome.

class Solution:
    """
    @param s: the given string
    @return: if a permutation of the string could form a palindrome
    """
    def canPermutePalindrome(self, s):
        if s is None:
            return False

        char_set = set()
        for ch in s:
            if ch in char_set:
                char_set.remove(ch)
            else:
                char_set.add(ch)

        return len(char_set) <= 1


obj = Solution()

def test(s):
    print(s + " " + str(obj.canPermutePalindrome(s)))

test("code")
test("aab")
test("carerac")
test("aabcb")
test("aabbcadad")
test("racecar")
test("rcaerca")
test("racecap")
test("rcaepca")
