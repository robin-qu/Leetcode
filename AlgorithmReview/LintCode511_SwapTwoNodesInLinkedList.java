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
        if (head == null || v1 == v2) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode curr1 = null;
        ListNode curr2 = null;
        ListNode curr = dummy;
        
        while (curr.next != null) {
            if (curr.next.val == v1) {
                curr1 = curr;
            }
            if (curr.next.val == v2) {
                curr2 = curr;
            }
            
            curr = curr.next;
        }
        
        if (curr1 == null || curr2 == null) {
            return dummy.next;
        }
        
        ListNode target1 = curr1.next;
        ListNode target2 = curr2.next;
        
        if (target1.next == target2) {
            ListNode successor = target2.next;
            
            curr1.next = target2;
            target2.next = target1;
            target1.next = successor;
        } else if (target2.next == target1) {
            ListNode successor = target1.next;
            
            curr2.next = target1;
            target1.next = target2;
            target2.next = successor;
        } else {
            ListNode successor1 = target1.next;
            ListNode successor2 = target2.next;
            
            curr1.next = target2;
            target2.next = successor1;
            curr2.next = target1;
            target1.next = successor2;
        }
            
        return dummy.next;
    }
}