/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// class Solution {
//     public ListNode swapPairs(ListNode head) {
//         ListNode dummy = new ListNode(0);
//         dummy.next = head;
//         ListNode curr = dummy;
//         while (curr.next != null && curr.next.next != null) {
//             ListNode newStart = curr.next.next.next;
//             ListNode next = curr.next.next;
//             curr.next.next = newStart;
//             next.next = curr.next;
//             curr.next = next;
//             curr = curr.next.next;
//         }
//         return dummy.next;
//     }
// }


class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newStart = head.next;
        head.next = swapPairs(newStart.next);
        newStart.next = head;
        return newStart;
    }
}