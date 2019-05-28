/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        split(head, dummy1, dummy2);
        
        reverse(dummy2);
        
        ListNode dummy = new ListNode(-1);
        merge(dummy, dummy1.next, dummy2.next);
        
        head = dummy.next;
    }
    
    private void split(ListNode head, ListNode dummy1, ListNode dummy2) {
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        dummy1.next = head;
        dummy2.next = slow.next;
        slow.next = null;
    }
    
    private void reverse(ListNode dummy) {
        if (dummy.next == null) {
            return;
        }
        ListNode curr = dummy.next;
        ListNode prev = dummy;
        ListNode end = dummy.next;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        dummy.next = prev;
        end.next = null;        
    }
    
    private void merge(ListNode dummy, ListNode head1, ListNode head2) {
        ListNode curr = dummy;
        ListNode curr1 = head1;
        ListNode curr2 = head2;
        
        while (curr1 != null && curr2 != null) {
            curr.next = curr1;
            curr1 = curr1.next;
            curr = curr.next;
            
            curr.next = curr2;
            curr2 = curr2.next;
            curr = curr.next;
        }
        
        if (curr1 != null) {
            curr.next = curr1;
        }
    }
}