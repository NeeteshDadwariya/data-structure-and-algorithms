# https://leetcode.com/problems/string-compression/

class Solution:

    def compress(self, chars) -> int:
        if chars is None:
            return 0

        l, r = 0, 0
        while r < len(chars):
            chars[l] = chars[r]
            count = 1

            while r + 1 < len(chars) and chars[r] == chars[r + 1]:
                count, r = count + 1, r + 1

            if count > 1:
                count_str = str(count)
                chars[l + 1: l + 1 + len(count_str)] = count_str
                l += len(count_str)

            l, r = l + 1, r + 1

        return l

sol = Solution()

def test(s):
    print(str(s))
    print(str(sol.compress(s)) + " " + str(s))

test(["a","a","b","b","b", "c","c","c"])
test(["a"])
test(["a","b","b","b","b","b","b","b","b","b","b","b","b"])
test(["a","a","a","b","b","a","a"])
