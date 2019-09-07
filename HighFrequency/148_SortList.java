/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// // Quick sort O(nlogn)time O(n)space
// class Solution {
//     public ListNode sortList(ListNode head) {
//         if (head == null || head.next == null) {
//             return head;
//         }
        
//         ListNode left = new ListNode(0);
//         ListNode middle = new ListNode(0);
//         ListNode right = new ListNode(0);
//         ListNode curr = head;
//         ListNode currLeft = left;
//         ListNode currMid = middle;
//         ListNode currRight = right;
//         int pivot = head.val;
        
//         while (curr != null) {
//             if (curr.val < pivot) {
//                 currLeft.next = curr;
//                 currLeft = currLeft.next;
//             } else if (curr.val == pivot) {
//                 currMid.next = curr;
//                 currMid = currMid.next;
//             } else {
//                 currRight.next = curr;
//                 currRight = currRight.next;
//             }
            
//             curr = curr.next;
//         }
        
//         currLeft.next = null;   // terminate left list
//         currMid.next = null;    // terminate middle list
//         currRight.next = null;  // terminate right list
        
//         ListNode leftSorted = sortList(left.next);
//         ListNode rightSorted = sortList(right.next);
        
//         // merge list
//         ListNode sorted = new ListNode(0);
//         curr = sorted;
//         currLeft = leftSorted;
//         while (currLeft != null) {
//             curr.next = currLeft;
//             currLeft = currLeft.next;
//             curr = curr.next;
//         }
//         currMid = middle.next;
//         while (currMid != null) {
//             curr.next = currMid;
//             currMid = currMid.next;
//             curr = curr.next;
//         }
//         currRight = rightSorted;
//         while (currRight != null) {
//             curr.next = currRight;
//             currRight = currRight.next;
//             curr = curr.next;
//         }
        
//         return sorted.next;
//     }
// }


// Merge sort O(nlogn)time O(n)space
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
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode l = sortedLeft;
        ListNode r = sortedRight;
        while (l != null && r != null) {
            if (l.val < r.val) {
                curr.next = l;
                l = l.next;
            } else {
                curr.next = r;
                r = r.next;
            }
            
            curr = curr.next;
        }
        
        curr.next = (l == null ? r : l);
        
        return dummy.next;
    }
    
    private ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
}