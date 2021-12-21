# https://leetcode.com/problems/rotate-image/
import math
from typing import List


class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        if matrix is None or matrix[0] is None:
            return matrix

        r, c = len(matrix), len(matrix[0])

        #[print(row) for row in matrix]
        print()
        for i in range(0, math.floor(r / 2)):
            for j in range(i, c - i - 1):
                # Do the triple point swap

                temp = matrix[i][j]
                matrix[i][j] = matrix[r-1-j][i]
                matrix[r-1-j][i] = matrix[r-1-i][c-1-j]
                matrix[r-1-i][c-1-j] = matrix[j][c-1-i]
                matrix[j][c-1-i] = temp

        [print(row) for row in matrix]



sol = Solution()
sol.rotate([
    [*range(1,7)],
    [*range(7,13)],
    [*range(13,19)],
    [*range(19,25)],
    [*range(25,31)],
    [*range(31,37)],
])


