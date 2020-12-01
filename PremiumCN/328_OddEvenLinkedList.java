/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode a = head;
        ListNode head1 = head.next;
        ListNode b = head.next;
        ListNode curr = head.next.next;

        boolean isFirstHalf = true;
        while (curr != null) {
            if (isFirstHalf) {
                a.next = curr;
                a = a.next;
            } else {
                b.next = curr;
                b = b.next;
            }
            curr = curr.next;
            isFirstHalf = !isFirstHalf;
        }
        a.next = head1;
        b.next = null;
        return head;
    }
} 