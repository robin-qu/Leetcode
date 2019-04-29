/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// // Quick sort
// class Solution {
//     public ListNode sortList(ListNode head) {
//         if (head == null || head.next == null) {
//             return head;
//         }
        
//         ListNode pivot = head;
        
//         ListNode leftDummy = new ListNode(0);
//         ListNode left = leftDummy;
//         ListNode midDummy = new ListNode(0);
//         ListNode middle = midDummy;
//         ListNode rightDummy = new ListNode(0);
//         ListNode right = rightDummy;
        
//         ListNode curr = head;
//         while (curr != null) {
//             if (curr.val < pivot.val) {
//                 left.next = curr;
//                 left = left.next;
//             } else if (curr.val > pivot.val) {
//                 right.next = curr;
//                 right = right.next;
//             } else {
//                 middle.next = curr;
//                 middle = middle.next;
//             }
//             curr = curr.next;
//         }
//         left.next = null;
//         right.next = null;
//         middle.next = null;
        
//         ListNode sortedLeft = sortList(leftDummy.next);
//         ListNode sortedRight = sortList(rightDummy.next);
        
//         return merge(sortedLeft, midDummy.next, sortedRight);
//     }
    
//     private ListNode merge(ListNode left, ListNode mid, ListNode right) {
//         ListNode dummy = new ListNode(0);
//         ListNode curr = dummy;
//         while (left != null) {
//             curr.next = left;
//             curr = curr.next;
//             left = left.next;
//         }
//         while (mid != null) {
//             curr.next = mid;
//             curr = curr.next;
//             mid = mid.next;
//         }
//         while (right != null) {
//             curr.next = right;
//             curr = curr.next;
//             right = right.next;
//         }
        
//         return dummy.next;
//     }
// }

// Merge sort
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode mid = getMid(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;
        
        ListNode sortedLeft = sortList(left);
        ListNode sortedRight = sortList(right);
        
        return mergeTwoSortedList(sortedLeft, sortedRight);
    }
    
    private ListNode mergeTwoSortedList(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode leftCurr = left;
        ListNode rightCurr = right;
        
        while (leftCurr != null && rightCurr != null) {
            if (leftCurr.val < rightCurr.val) {
                curr.next = leftCurr;
                leftCurr = leftCurr.next;
            } else {
                curr.next = rightCurr;
                rightCurr = rightCurr.next;
            }
            curr = curr.next;
        }
        
        if (leftCurr != null) {
            curr.next = leftCurr;
        }
        
        if (rightCurr != null) {
            curr.next = rightCurr;
        }
        
        return dummy.next;
    }
    
    private ListNode getMid(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
}