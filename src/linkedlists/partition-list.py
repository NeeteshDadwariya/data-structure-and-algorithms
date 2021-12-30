# https://leetcode.com/problems/partition-list/

from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def partition(self, head: Optional[ListNode], x: int) -> Optional[ListNode]:
        l1 = h1 = ListNode(None)
        l2 = h2 = ListNode(None)

        temp = head
        while temp is not None:
            if temp.val < x:
                l1.next = temp
                l1 = l1.next
            else:
                l2.next = temp
                l2 = l2.next
            temp = temp.next

        l1.next = h2.next
        l2.next = None

        return h1.next
