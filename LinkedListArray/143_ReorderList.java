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


class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        
        // Finds middle point
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        
        curr = head;
        for (int i = 0; i < size / 2 ; i++) {
            curr = curr.next;
        }
        
        ListNode right = curr.next;
        curr.next = null;
        
        right = reverse(right);
        head = merge(head, right);
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
    
    // l1 has equal number of node or one more node than l2
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            curr.next = l1;
            curr = curr.next;
            l1 = l1.next;
            curr.next = l2;
            curr = curr.next;
            l2 = l2.next;
        }
        if (l1 != null) {
            curr.next = l1;
        }
        return dummy.next;
    }
} 