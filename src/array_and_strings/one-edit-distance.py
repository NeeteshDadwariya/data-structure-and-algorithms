# https://www.lintcode.com/problem/640/

class Solution:
    """
    @param s: a string
    @param t: a string
    @return: true if they are both one edit distance apart or false
    """

    def isOneEditDistance(self, s, t):
        l1 = len(s)
        l2 = len(t)
        len_diff = abs(l1 - l2)

        if len_diff > 1:
            return False

        dist = 0
        i = 0
        j = 0

        while i < l1 and j < l2:
            if dist > 1:
                return False
            if s[i] != t[j]:
                dist += 1
                if l1 < l2:
                    j += 1
                elif l2 < l1:
                    i += 1
                else:
                    i += 1
                    j += 1
            else:
                i += 1
                j += 1

        if i < l1 or j < l2:
            dist += max(l1, l2) - min(i,j)

        return dist == 1


obj = Solution()


def test(s, t):
    print(s + " " + t + " " + str(obj.isOneEditDistance(s, t)))


test('pale', 'ple')
test('pales', 'pale')
test('pale', 'bale')
test('pale', 'bake')
test('aDb', 'adb')
test('ab', 'ab')
