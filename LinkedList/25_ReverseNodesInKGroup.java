/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        
        while (prev != null) {
            prev = reverseKOnce(prev, k);
        }
        
        return dummy.next;
    }
    
    private ListNode reverseKOnce(ListNode head, int k) {
        ListNode curr = head;
        ListNode last = head.next;
        for (int i = 0; i < k; i++) {
            curr = curr.next;
            if (curr == null) {
                return null;
            }
        }
        
        curr = head.next;
        ListNode prev = head;
        for (int i = 0; i < k; i++) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        head.next = prev;
        last.next = curr;
        
        return last;
    }
}