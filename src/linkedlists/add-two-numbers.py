# https://leetcode.com/problems/add-two-numbers/

from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class LinkedList:
    head = None

    def insert(self, new_data):

        new_node = ListNode(new_data)
        if self.head is None:
            self.head = new_node
            return
        last = self.head
        while last.next is not None:
            last = last.next
        last.next = new_node

    def print_list(self):
        temp = self.head
        l = list()
        while temp is not None:
            l.append(temp.val)
            temp = temp.next
        print(l)


class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:

        if l1 is None or l2 is None:
            return None

        sum_head = None
        sum_ptr = None
        p1 = l1
        p2 = l2
        carry = 0

        while p1 is not None or p2 is not None or carry > 0:
            sum_val = carry
            if p1 is not None:
                sum_val += p1.val
                p1 = p1.next
            if p2 is not None:
                sum_val += p2.val
                p2 = p2.next
            carry = int(sum_val / 10)
            sum_val = sum_val % 10
            new_node = ListNode(sum_val, None)
            if sum_head is None:
                sum_head = sum_ptr = new_node
            else:
                sum_ptr.next = new_node
                sum_ptr = sum_ptr.next

        return sum_head


def print_list(head):
    temp = head
    l = list()
    while temp is not None:
        l.append(temp.val)
        temp = temp.next
    print(l)


def make_list(str):
    nums = str.split(',')
    ll = LinkedList()
    [ll.insert(int(n)) for n in nums if n != 'null']
    return ll


def test(l1, l2):
    sol = Solution()
    ll1 = make_list(l1)
    ll2 = make_list(l2)
    print_list(sol.addTwoNumbers(ll1.head, ll2.head))


test('2,4,3', '5,6,4')
test('0', '0')
test('9,9,9,9,9,9,9', '9,9,9,9')
