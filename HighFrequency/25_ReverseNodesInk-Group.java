/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// // Version 1 O(n)time O(1)space
// class Solution {
//     public ListNode reverseKGroup(ListNode head, int k) {
//         ListNode dummy = new ListNode(0);
//         dummy.next = head;
//         ListNode curr = dummy;
        
//         while (true) {
//             ListNode next = reverseK(curr.next, k);
//             if (next == null) {
//                 break;
//             } else {
//                 curr.next = next;
//             }
            
//             for (int i = 0; i < k; i++) {
//                 curr = curr.next;
//             }
//         }
        
//         return dummy.next;
//     }
    
//     // reverse the next k nodes and returns the new head, null if less than k nodes
//     private ListNode reverseK(ListNode head, int k) {
//         ListNode tail = head;  // k + 1 th node
//         for (int i = 0; i < k; i++) {
//             if (tail == null) {
//                 return null;
//             }
//             tail = tail.next;
//         }
        
//         ListNode curr = head;
//         ListNode prev = tail;
//         for (int i = 0; i < k; i++) {
//             ListNode next = curr.next;
//             curr.next = prev;
//             prev = curr;
//             curr = next;
//         }
            
//         return prev;
//     }
// }



// Version 2 O(n)time O(1)space
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (prev != null) {
            prev = reverseK(prev, k);
        }
        
        return dummy.next;
    }
    
    // reverse the next k nodes and returns the new head, null if less than k nodes
    private ListNode reverseK(ListNode head, int k) {
        ListNode curr = head;  // k th node
        for (int i = 0; i < k; i++) {
            curr = curr.next;
            if (curr == null) {
                return null;
            }
        }
        
        curr = head.next;
        ListNode tail = head.next;
        ListNode prev = head;
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        // connecr
        head.next = prev;
        tail.next = curr;
            
        return tail;
    }
}