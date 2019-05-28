/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;
        
        int size = 0;
        while (curr.next != null) {
            curr = curr.next;
            size++;
        }
        
        curr = dummy;
        if (k % size == 0) {
            return head;
        }
        
        for (int i = 0; i < size - k % size; i++) {
            curr = curr.next;
        }
        
        ListNode newHead = curr.next;
        curr.next = null;
        
        curr = newHead;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = head;
        
        return newHead;
    }
}