/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param head: a ListNode
     * @param v1: An integer
     * @param v2: An integer
     * @return: a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode curr = dummy.next;
        ListNode position1 = null;
        ListNode position2 = null;
        ListNode prev1 = null;
        ListNode prev2 = null;
        ListNode next1 = null;
        ListNode next2 = null;
        while (curr != null) {
            if (curr.val == v1) {
                position1 = curr;
                prev1 = prev;
                next1 = curr.next;
            }
            if (curr.val == v2) {
                position2 = curr;
                prev2 = prev;
                next2 = curr.next;
            }
            
            prev = curr;
            curr = curr.next;
        }
        
        if (position1 != null && position2 != null) {
            if (position1.next == position2) {
                prev1.next = position2;
                position2.next = position1;
                position1.next = next2;
            } else if (position2.next == position1) {
                prev2.next = position1;
                position1.next = position2;
                position2.next = next1;
            } else {
                prev1.next = position2;
                position2.next = next1;
                prev2.next =  position1;
                position1.next = next2;
            }
        }
        
        return dummy.next;
    }
}