# Definition for singly-linked list.
#https://leetcode.com/problems/palindrome-linked-list

from typing import Optional

class ListNode:
     def __init__(self, val=0, next=None):
         self.val = val
         self.next = next

class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        p1 = p2 = head
        stack = []

        if p1 is None or p1.next is None:
            return True

        while p2 is not None and p2.next is not None:
            stack.append(p1.val)
            p1 = p1.next
            p2 = p2.next.next

        #Odd elements case
        if p2 is not None:
            p1 = p1.next

        while p1 is not None:
            if p1.val != stack.pop():
                return False
            p1 = p1.next


        return True
