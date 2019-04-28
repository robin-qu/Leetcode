/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// class Solution {
//     public void reorderList(ListNode head) {
//         ListNode dummy = new ListNode(-1);
//         dummy.next = null;
//         ListNode newCurr = dummy;
//         ListNode curr = head;
//         while (curr != null) {
//             newCurr.next = curr;
//             curr = curr.next;
//             newCurr = newCurr.next;
//             newCurr.next = null;
            
//             if (curr == null) {
//                 break;
//             }
            
//             ListNode prev = null;
//             ListNode end = curr;
//             while (end.next != null) {
//                 prev = end;
//                 end = end.next;
//             }
            
//             newCurr.next = end;
//             if (prev != null) {
//                 prev.next = null;
//             } else {
//                 curr = null;
//             }
            
//             newCurr = newCurr.next;
//         }
//         head = dummy.next;
//     }
// }