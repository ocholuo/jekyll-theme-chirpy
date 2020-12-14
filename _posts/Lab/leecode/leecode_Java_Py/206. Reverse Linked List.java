



// 206. Reverse Linked List
// Reverse a singly linked list.
// Example:
// Input: 1->2->3->4->5->NULL
// Output: 5->4->3->2->1->NULL

// Follow up:
// A linked list can be reversed either iteratively or recursively. Could you implement both?



// Approach #1 (Iterative) [Accepted]
// Assume that we have linked list 1 → 2 → 3 → Ø, we would like to change it to Ø ← 1 ← 2 ← 3.
// While you are traversing the list, change the current node's next pointer to point to its previous element. Since a node does not have reference to its previous node, you must store its previous element beforehand. You also need another pointer to store the next node before changing the reference. Do not forget to return the new head reference at the end!

public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    ListNode next = null;
    while(curr != null) {
        next = curr.next;
        curr.next = prev;  // point
        prev = curr;
        curr = next;
    }
    return prev;
}
// Complexity analysis
// Time complexity : O(n)
// Assume that n is the list's length
// Space complexity : O(1)
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
// Memory Usage: 40.7 MB, less than 6.09% of Java online submissions for Reverse Linked List.



// Approach #2 (Recursive) [Accepted]
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode rList = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return rList;
}
// Runtime: 1 ms, faster than 9.36% of Java online submissions for Reverse Linked List.
// Memory Usage: 40.6 MB, less than 7.66% of Java online submissions for Reverse Linked List.



// Approach #3
public ListNode reverseList(ListNode current) {
    if (current == null){
        return;
    }
    if (current.getNext() == null){
        this.head = current;
        return;
    }
    reverseList(current.getNext());
    current.getNext().setNext(cuurent);
    current.setNext(null);
}

    