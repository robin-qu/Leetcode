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
//     public ListNode reverseKGroup(ListNode head, int k) {
//         ListNode dummy = new ListNode(0);
//         dummy.next = head;
//         ListNode curr = reverseK(dummy, k);
//         while (curr != null) {
//             curr = reverseK(curr, k);
//         }

//         return dummy.next;
//     }

//     private ListNode reverseK(ListNode head, int k) {
//         ListNode last = head;
//         for (int i = 0; i < k; i++) {
//             last = last.next;
//             if (last == null) {
//                 return null;
//             }
//         }
//         ListNode newLast = head.next;
//         ListNode prev = head;
//         ListNode curr = head.next;
//         for (int i = 0; i < k; i++) {
//             ListNode next = curr.next;
//             curr.next = prev;
//             prev = curr;
//             curr = next;
//         }
//         head.next = prev;
//         newLast.next = curr;
//         return newLast;
//     }
// }


class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode last = head;
        for (int i = 0; i < k; i++) {
            if (last == null) {
                return head;
            }
            last = last.next;
        }
        ListNode newLast = head;
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        newLast.next = reverseKGroup(curr, k);

        return prev;
    }
}