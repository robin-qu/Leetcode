/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// // merge sort
// class Solution {    
//     public ListNode sortList(ListNode head) {
//         if (head == null || head.next == null) {
//             return head;
//         }
        
//         ListNode slow = head;
//         ListNode fast = head.next;
        
//         while (fast != null && fast.next != null) {
//             fast = fast.next.next;
//             slow = slow.next;
//         }
        
//         ListNode left = head;
//         ListNode right = slow.next;
//         slow.next = null;
        
//         ListNode sortedLeft = sortList(left);
//         ListNode sortedRight = sortList(right);
        
//         return merge(sortedLeft, sortedRight);
//     }
    
//     private ListNode merge(ListNode head1, ListNode head2) {
//         if (head1 == null) {
//             return head2;
//         }
        
//         if (head2 == null) {
//             return head1;
//         }
        
//         ListNode dummy = new ListNode(-1);
//         ListNode curr = dummy;
//         ListNode curr1 = head1;
//         ListNode curr2 = head2;
        
//         while (curr1 != null && curr2 != null) {
//             if (curr1.val < curr2.val) {
//                 curr.next = curr1;
//                 curr1 = curr1.next;
//             } else {
//                 curr.next = curr2;
//                 curr2 = curr2.next;
//             }
//             curr = curr.next;
//         }
        
//         curr.next = curr1 == null ? curr2 : curr1;
        
//         return dummy.next;
//     }
// }


// quick sort
class Solution {    
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode pivot = head;
        ListNode curr = head.next;
        head.next = null;
        
        ListNode leftDummy = new ListNode(-1);
        ListNode leftCurr = leftDummy;
        ListNode rightDummy = new ListNode(-1);
        ListNode rightCurr = rightDummy;
        
        while (curr != null) {
            if (curr.val < pivot.val) {
                leftCurr.next = curr;
                leftCurr = leftCurr.next;
            } else {
                rightCurr.next = curr;
                rightCurr = rightCurr.next;
            }
            curr = curr.next;
        }
        leftCurr.next = null;
        rightCurr.next = null;
        
        ListNode sortedLeft = sortList(leftDummy.next);
        ListNode sortedRight = sortList(rightDummy.next);
        
        return concatenate(sortedLeft, pivot, sortedRight);
    }
    
    private ListNode concatenate(ListNode left, ListNode middle, 
                                 ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        
        curr.next = left;
        while (curr.next != null) {
            curr = curr.next;
        }
        
        curr.next = middle;
        curr = curr.next;
        
        curr.next = right;
        
        return dummy.next;
    }
}