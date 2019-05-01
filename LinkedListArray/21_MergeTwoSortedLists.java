/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// class Solution {
//     public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//         if (l1 == null) return l2;
//         if (l2 == null) return l1;
//         if (c1.val <= c2.val) {
//             c1.next = mergeTwoLists(c1.next, c2);
//             return c1;
//         } else {
//             c2.next = mergeTwoLists(c1, c2.next);
//             return c2;
//         }
//     }
// }


class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        dummy.next = l1;
        ListNode curr = dummy;
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        
        while (curr1 != null && curr2 != null) {
            if (curr1.val < curr2.val) {
                curr.next = curr1;
                curr1 = curr1.next;
            } else {
                curr.next = curr2;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        
        curr.next = curr1 == null ? curr2 : curr1;
        return dummy.next;
    }
}