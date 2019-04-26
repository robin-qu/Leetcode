// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode(int x) { val = x; }
//  * }
//  */
// class Solution {
//     public ListNode reverseBetween(ListNode head, int m, int n) {
//         if (head == null) {
//             return null;
//         }
        
//         ListNode dummy = new ListNode(-1);
//         dummy.next = head;
        
//         ListNode prev = dummy;
//         ListNode curr = head;
//         int idx = 1;
        
//         while (idx < m) {
//             prev = curr;
//             curr = curr.next;
//             idx++;
//         }
        
//         prev.next = reverseFrom(curr, n - m);
//         return m == 1 ? prev.next : head;
//     }
    
//     private ListNode reverseFrom(ListNode node, int n) {
//         ListNode dummy = new ListNode(-1);
//         dummy.next = node;
//         ListNode prev = dummy;
//         ListNode curr = node;
        
//         while (n >= 0) {
//             ListNode temp = curr.next;
//             curr.next = prev;
//             prev = curr;
//             curr = temp;
//             n--;
//         }
        
//         node.next = curr;
//         return prev;
//     }
// }

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode curr = head;
        int idx = 1;
        
        while (idx < m) {
            prev = curr;
            curr = curr.next;
            idx++;
        }
        
        ListNode node = prev;
        
        while (idx <= n) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            idx++;
        }
        
        node.next.next = curr;
        node.next = prev;
        
        return dummy.next;
    }
}