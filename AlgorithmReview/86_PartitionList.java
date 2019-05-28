/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        
        ListNode curr = head;
        ListNode dummy1 = new ListNode(-1);
        ListNode curr1 = dummy1;
        ListNode dummy2 = new ListNode(-1);
        ListNode curr2 = dummy2;
        
        while (curr != null) {
            if (curr.val < x) {
                curr1.next = curr;
                curr1 = curr1.next;
            } else {
                curr2.next = curr;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        
        curr1.next = dummy2.next;
        curr2.next = null;
        
        return dummy1.next;
    }
}