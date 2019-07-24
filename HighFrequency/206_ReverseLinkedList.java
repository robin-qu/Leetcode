/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// // Iterative
// class Solution {
//     public ListNode reverseList(ListNode head) {
//         if (head == null) {
//             return null;
//         }

//         ListNode curr = head;
//         ListNode prev = null;
        
//         while (curr != null) {
//             ListNode next = curr.next;
//             curr.next = prev;
//             prev = curr;
//             curr = next;
//         }
            
//         return prev;
//     }
// }

// Recursive
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        
        return node;
    }
}