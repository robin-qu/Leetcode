/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy.next;
        ListNode prev = dummy;
        int idx = 1;
        
        while (idx < m) {
            prev = curr;
            curr = curr.next;
            idx++;
        }
        
        ListNode start = prev;
        ListNode end = curr;
        
        for (int i = 0; i < n - m + 1; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        start.next = prev;
        end.next = curr;
        
        return dummy.next;
    }
}