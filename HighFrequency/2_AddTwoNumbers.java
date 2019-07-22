/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// // O(n)time O(1)space
// class Solution {
//     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//         if (l1 == null || l2 == null) {
//             return null;
//         }
        
//         ListNode dummy = new ListNode(-1);
//         ListNode curr = dummy;
//         ListNode c1 = l1;
//         ListNode c2 = l2;
//         int carry = 0;
        
//         while (c1 != null && c2 != null) {
//             curr.next = new ListNode((c1.val + c2.val + carry) % 10);
//             carry = c1.val + c2.val + carry > 9 ? 1 : 0;
//             curr = curr.next;
//             c1 = c1.next;
//             c2 = c2.next;
//         }
        
//         if (c1 != null) {
//             curr.next = c1;
//         } else if (c2 != null) {
//             curr.next = c2;
//         }
//         ListNode prev = curr;
//         curr = curr.next;
        
//         while (curr != null) {
//             if (curr.val + carry > 9) {
//                 curr.val = (curr.val + carry) % 10;
//                 carry = 1;
//             } else {
//                 curr.val = curr.val + carry;
//                 carry = 0;
//             }
//             curr = curr.next;
//             prev = prev.next;
//         }
        
//         if (carry == 1) {
//             prev.next = new ListNode(1);
//         }
        
//         return dummy.next;
//     }
// }


// O(n)time O(1)space
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        ListNode c1 = l1;
        ListNode c2 = l2;
        int carry = 0;
        
        while (c1 != null || c2 != null) {
            int x = (c1 == null ? 0 : c1.val);
            int y = (c2 == null ? 0 : c2.val);
            curr.next = new ListNode((x + y + carry) % 10);
            carry = x + y + carry > 9 ? 1 : 0;
            curr = curr.next;
            
            if (c1 != null) {
                c1 = c1.next;
            }
            if (c2 != null) {
                c2 = c2.next;
            }
        }
        
        if (carry == 1) {
            curr.next = new ListNode(1);
        }
        
        return dummy.next;
    }
}