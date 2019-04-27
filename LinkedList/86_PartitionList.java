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
        ListNode leftDummy = new ListNode(-1);
        leftDummy.next = null;
        ListNode rightDummy = new ListNode(-1);
        rightDummy.next = null;
        
        ListNode left = leftDummy;
        ListNode right = rightDummy;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                left.next = curr;
                left = left.next;
            } else {
                right.next = curr;
                right = right.next;
            }
            curr = curr.next;
        }
        right.next = null;
        left.next = rightDummy.next;
        return leftDummy.next;
    }
}