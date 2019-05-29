/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// class Solution {
//     public void deleteNode(ListNode node) {
//         ListNode prev = null;
//         ListNode curr = node;
//         ListNode next = node.next;
        
//         while (next != null) {
//             prev = curr;
//             curr.val = next.val;
//             curr = next;
//             next = next.next;
//         }
        
//         prev.next = null;
//     }
// }

class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}