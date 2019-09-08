/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// O(n)time O(1)space
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        
        for (int i = 0; i < m - 1; i++) {
            curr = curr.next;
        }
        
        ListNode left = curr;
        ListNode prev = curr;
        curr = curr.next;
        for (int i = 0; i < n - m + 1; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        left.next.next = curr;
        left.next = prev;
        
        return dummy.next;
    }
}