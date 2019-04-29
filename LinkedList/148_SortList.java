/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Quick sort
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode mid = getMid(head);
        
        ListNode leftDummy = new ListNode(0);
        ListNode left = leftDummy;
        ListNode midDummy = new ListNode(0);
        ListNode middle = midDummy;
        ListNode rightDummy = new ListNode(0);
        ListNode right = rightDummy;
        
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < mid.val) {
                left.next = curr;
                left = left.next;
            } else if (curr.val > mid.val) {
                right.next = curr;
                right = right.next;
            } else {
                middle.next = curr;
                middle = middle.next;
            }
            curr = curr.next;
        }
        left.next = null;
        right.next = null;
        middle.next = null;
        
        ListNode sortedLeft = sortList(leftDummy.next);
        ListNode sortedRight = sortList(rightDummy.next);
        
        return merge(sortedLeft, midDummy.next, sortedRight);
    }
    
    private ListNode getMid(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
    
    private ListNode merge(ListNode left, ListNode mid, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (left != null) {
            curr.next = left;
            curr = curr.next;
            left = left.next;
        }
        while (mid != null) {
            curr.next = mid;
            curr = curr.next;
            mid = mid.next;
        }
        while (right != null) {
            curr.next = right;
            curr = curr.next;
            right = right.next;
        }
        
        return dummy.next;
    }
}