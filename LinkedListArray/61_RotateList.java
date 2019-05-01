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
            return null;
        }
        
        int size = getSize(head);
        k %= size;
        if (k == 0) {
            return head;
        }
        
        ListNode curr = head;
        for (int i = 0; i < size - k - 1; i++) {
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
    
    private int getSize(ListNode head) {
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }
}