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
        if (head == null || k == 1) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;
        
        while (curr != null) {
            curr = reverseK(curr, k);
        }
        
        return dummy.next;
    }
    
    // pre: node is not null
    private ListNode reverseK(ListNode node, int k) {
        ListNode res = node.next;
        ListNode curr = node;
        
        for (int i = 0; i < k; i++) {
            curr = curr.next;
            if (curr == null) {
                return null;
            }
        }
        
        curr = node.next;
        ListNode prev = node;
        
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        node.next = prev;
        res.next = curr;
        
        return res;
    }
}