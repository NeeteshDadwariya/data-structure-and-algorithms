# https://leetcode.com/problems/remove-nth-node-from-end-of-list/

from typing import Optional


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
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        if head is None:
            return head

        l = 0
        temp = head
        while temp is not None:
            temp = temp.next
            l += 1

        temp = head
        prev = None
        counter = 0
        while temp is not None and counter < l - n:
            prev = temp
            temp = temp.next
            counter += 1

        if prev is None:
            head = temp.next
        else:
            prev.next = temp.next
        temp.next = None

        return head

    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        if head is None:
            return head

        i = 0
        prev = None
        temp = head

        while temp is not None:
            if i >= n:
                prev = head if prev is None else prev.next

            i += 1
            temp = temp.next

        #Head to be removed
        if prev is None:
            new_head = head.next
            head.next = None
            head = new_head
        else:
            rem = prev.next
            prev.next = rem.next
            rem.next = None

        return head


def test(s: str):
    sol = Solution()
    nums = s.split('->')
    ll = LinkedList()
    [ll.insert(int(n)) for n in nums if n != 'null']
    ll.print_list()
    ll.head = sol.removeNthFromEnd(ll.head, 3)
    ll.print_list()
    ll.head = sol.removeNthFromEnd(ll.head, 1)
    ll.print_list()
    ll.head = sol.removeNthFromEnd(ll.head, 6)
    ll.print_list()
    ll.head = sol.removeNthFromEnd(ll.head, 1)
    ll.print_list()
    ll.head = sol.removeNthFromEnd(ll.head, 1)
    ll.print_list()
    ll.head = sol.removeNthFromEnd(ll.head, 1)
    ll.print_list()
    ll.head = sol.removeNthFromEnd(ll.head, 1)
    ll.print_list()
    ll.head = sol.removeNthFromEnd(ll.head, 1)
    ll.print_list()

test('1->2->1->3->3->5->6->3->null')
