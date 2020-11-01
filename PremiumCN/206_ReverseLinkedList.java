/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// class Solution {
//     public ListNode reverseList(ListNode head) {
//         if (head == null) {
//             return null;
//         }
        
//         ListNode prev = null;
//         ListNode curr = head;
//         while (curr != null) {
//             ListNode next = curr.next;
//             curr.next = prev;
//             prev = curr;
//             curr = next;
//         }
        
//         return prev;
//     }
// }


class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode next = head.next;
        ListNode reversedHead = reverseList(next);
        next.next = head;
        head.next = null;
        return reversedHead;
    }
}