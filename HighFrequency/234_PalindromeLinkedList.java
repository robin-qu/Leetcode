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
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        slow = reverse(slow.next);
        
        ListNode curr = head;
        while (slow != null) {
            if (slow.val != curr.val) {
                return false;
            }
            
            curr = curr.next;
            slow = slow.next;
        }
        
        return true;
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode curr = head;
        ListNode prev = dummy;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        head.next = null;
        return prev;
    }
}