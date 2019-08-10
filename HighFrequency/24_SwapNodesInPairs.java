/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// // recursive
// class Solution {
//     public ListNode swapPairs(ListNode head) {
//         if (head == null || head.next == null) {
//             return head;
//         }
        
//         // head -> newHead -> nextHead -> ...
//         ListNode newHead = head.next;
//         ListNode nextHead = head.next.next;
//         newHead.next = head;
//         head.next = swapPairs(nextHead);
//         return newHead;
//     }
// }

// iterative
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null && node.next.next != null) {
            // node -> newNode -> nextNode -> temp -> ...
            ListNode newNode = node.next;
            ListNode nextNode = node.next.next;
            ListNode temp = node.next.next.next;
            node.next = nextNode;
            nextNode.next = newNode;
            newNode.next = temp;
            node = newNode;
        }
        
        return dummy.next;
    }
}