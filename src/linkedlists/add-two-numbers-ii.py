# https://leetcode.com/problems/add-two-numbers-ii/

from typing import Optional, Tuple


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


def insert_before(head, new_data) -> ListNode:
    return ListNode(new_data, head)


def pad_before(head, zero_count):
    for i in range(zero_count):
        head = insert_before(head, 0)
    return head


def length(head) -> int:
    temp = head
    len = 0
    while temp is not None:
        len += 1
        temp = temp.next
    return len


def add(l1: ListNode, l2: ListNode) -> Tuple[ListNode, int]:
    if l1 is None or l2 is None:
        return None, 0

    next_node, carry = add(l1.next, l2.next)
    sum = (carry + l1.val + l2.val)
    rem = sum % 10
    carry = int(sum / 10)

    return ListNode(rem, next_node), carry


class Solution:

    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:

        if l1 is None:
            return l2

        if l2 is None:
            return l1

        len1 = length(l1)
        len2 = length(l2)

        if len1 < len2:
            l1 = pad_before(l1, len2 - len1)
        else:
            l2 = pad_before(l2, len1 - len2)

        head, carry = add(l1, l2)
        if carry > 0:
            head = insert_before(head, carry)

        return head

def test(l1, l2):
    sol = Solution()
    ll1 = make_list(l1)
    ll2 = make_list(l2)
    print_list(sol.addTwoNumbers(ll1.head, ll2.head))


test('7,2,4,3', '5,6,4')
test('2,4,3', '5,6,4')
test('0', '0')
