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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode right = slow.next;
        slow.next = null;
        
        right = reverse(right);
        merge(head, right);
    }
    
    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        dummy.next.next = null;
        return prev;
    }
    
    private void merge(ListNode n1, ListNode n2) {
        ListNode curr = n1;
        while (n2 != null) {
            ListNode next = curr.next;
            curr.next = n2;
            n2 = n2.next;
            curr = curr.next;
            curr.next = next;
            curr = curr.next;
        }
    }
}