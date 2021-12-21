# https://www.lintcode.com/problem/217/

class ListNode(object):
    def __init__(self, val, next=None):
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
    """
    @param head: The first node of linked list.
    @return: Head node.
    """

    @staticmethod
    def remove_duplicates(head):
        # write your code here
        if head is None:
            return

        data = set()
        temp = head
        prev = None

        while temp is not None:

            if temp.val in data:
                prev.next = temp.next
                temp.next = None
                temp = prev.next
            else:
                data.add(temp.val)
                prev = temp
                temp = temp.next


def test_remove_duplicates(s: str):
    sol = Solution()
    nums = s.split('->')
    ll = LinkedList()
    [ll.insert(int(n)) for n in nums if n != 'null']
    ll.print_list()
    sol.remove_duplicates(ll.head)
    ll.print_list()


test_remove_duplicates('1->2->1->3->3->5->6->3->null')
