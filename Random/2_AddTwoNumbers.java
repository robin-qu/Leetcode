// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode(int x) { val = x; }
//  * }
//  */
// class Solution {
//     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//         ListNode dummy = new ListNode(-1);
//         ListNode curr = dummy;
//         ListNode curr1 = l1;
//         ListNode curr2 = l2;
//         int carry = 0;
        
//         while (curr1 != null && curr2 != null) {
//             int val = curr1.val + curr2.val + carry;
//             if (val > 9) {
//                 val -= 10;
//                 carry = 1;
//             } else {
//                 carry = 0;
//             }
//             curr.next = new ListNode(val);
//             curr = curr.next;
//             curr1 = curr1.next;
//             curr2 = curr2.next;
//         }
        
//         while (curr1 != null) {
//             int val = curr1.val + carry;
//             if (val > 9) {
//                 val -= 10;
//                 carry = 1;
//             } else {
//                 carry = 0;
//             }
//             curr.next = new ListNode(val);
//             curr = curr.next;
//             curr1 = curr1.next;
//         }
        
//         while (curr2 != null) {
//             int val = curr2.val + carry;
//             if (val > 9) {
//                 val -= 10;
//                 carry = 1;
//             } else {
//                 carry = 0;
//             }
//             curr.next = new ListNode(val);
//             curr = curr.next;
//             curr2 = curr2.next;
//         }
        
//         if (carry == 1) {
//             curr.next = new ListNode(1);
//         }
        
//         return dummy.next;
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

// class Solution {
//     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//         ListNode dummy = new ListNode(-1);
//         ListNode curr = dummy;
//         ListNode curr1 = l1;
//         ListNode curr2 = l2;
//         int carry = 0;
        
//         while (curr1 != null || curr2 != null) {
//             int val1 = (curr1 == null ? 0 : curr1.val);
//             int val2 = (curr2 == null ? 0 : curr2.val);
//             int val = val1 + val2 + carry;
//             if (val > 9) {
//                 val -= 10;
//                 carry = 1;
//             } else {
//                 carry = 0;
//             }
//             curr.next = new ListNode(val);
//             curr = curr.next;
//             curr1 = (curr1 == null ? null : curr1.next);
//             curr2 = (curr2 == null ? null : curr2.next);
//         }
        
//         if (carry == 1) {
//             curr.next = new ListNode(1);
//         }
        
//         return dummy.next;
//     }
// }

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        int carry = 0;
        
        while (curr1 != null || curr2 != null || carry == 1) {
            int val1 = (curr1 == null ? 0 : curr1.val);
            int val2 = (curr2 == null ? 0 : curr2.val);
            int val = val1 + val2 + carry;
            if (val > 9) {
                val -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            curr.next = new ListNode(val);
            curr = curr.next;
            curr1 = (curr1 == null ? null : curr1.next);
            curr2 = (curr2 == null ? null : curr2.next);
        }
        
        return dummy.next;
    }
}