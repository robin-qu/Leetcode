/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// // brute force O(n^2)time O(1)space
// class Solution {
//     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//         // get length
//         ListNode curr1 = l1;
//         int len1 = 0;
//         while (curr1 != null) {
//             curr1 = curr1.next;
//             len1++;
//         }
//         ListNode curr2 = l2;
//         int len2 = 0;
//         while (curr2 != null) {
//             curr2 = curr2.next;
//             len2++;
//         }
        
//         ListNode dummy = new ListNode(0);
//         int carry = 0;
        
//         for (int i = 0; i < Math.min(len1, len2); i++) {
//             curr1 = l1;
//             curr2 = l2;
//             for (int j = 0; j < len1 - i - 1; j++) {
//                 curr1 = curr1.next;
//             }
//             for (int j = 0; j < len2 - i - 1; j++) {
//                 curr2 = curr2.next;
//             }
            
//             int val = curr1.val + curr2.val + carry;
//             if (val >= 10) {
//                 val = val % 10;
//                 carry = 1;
//             } else {
//                 carry = 0;
//             }
            
//             ListNode newNode = new ListNode(val);
//             newNode.next = dummy.next;
//             dummy.next = newNode;
//         }
        
//         if (len1 > len2) {
//             for (int i = 0; i < len1 - len2; i++) {
//                 curr1 = l1;
//                 for (int j = 0; j < len1 - len2 - i - 1; j++) {
//                     curr1 = curr1.next;
//                 }

//                 int val = curr1.val + carry;
//                 if (val >= 10) {
//                     val = val % 10;
//                     carry = 1;
//                 } else {
//                     carry = 0;
//                 }

//                 ListNode newNode = new ListNode(val);
//                 newNode.next = dummy.next;
//                 dummy.next = newNode;
//             }
                
//         }
        
//         if (len2 > len1) {
//             for (int i = 0; i < len2 - len1; i++) {
//                 curr2 = l2;
//                 for (int j = 0; j < len2 - len1 - i - 1; j++) {
//                     curr2 = curr2.next;
//                 }

//                 int val = curr2.val + carry;
//                 if (val >= 10) {
//                     val = val % 10;
//                     carry = 1;
//                 } else {
//                     carry = 0;
//                 }

//                 ListNode newNode = new ListNode(val);
//                 newNode.next = dummy.next;
//                 dummy.next = newNode;
//             }
                
//         }
        
//         if (carry == 1) {
//             ListNode newNode = new ListNode(1);
//             newNode.next = dummy.next;
//             dummy.next = newNode;
//         }
        
//         return dummy.next;
//     }
// }


// stack O(n)time O(n)space
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode curr1 = l1;
        while (curr1 != null) {
            stack1.push(curr1);
            curr1 = curr1.next;
        }
        ListNode curr2 = l2;
        while (curr2 != null) {
            stack2.push(curr2);
            curr2 = curr2.next;
        }
        
        int carry = 0;
        ListNode dummy = new ListNode(0);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int digit1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            int digit2 = stack2.isEmpty() ? 0 : stack2.pop().val;
            int val = digit1 + digit2 + carry;
            if (val >= 10) {
                val = val % 10;
                carry = 1;
            } else {
                carry = 0;
            }
            
            ListNode newNode = new ListNode(val);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        
        if (carry == 1) {
            ListNode newNode = new ListNode(1);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        
        return dummy.next;
    }
}