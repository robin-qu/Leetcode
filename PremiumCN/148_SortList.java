/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// class Solution {
//     public ListNode sortList(ListNode head) {
//         if (head == null || head.next == null) {
//             return head;
//         }
        
//         int pivot = head.val;
//         ListNode leftDummy = new ListNode(0);
//         ListNode rightDummy = new ListNode(0);
//         ListNode middleDummy = new ListNode(0);
//         ListNode leftCurr = leftDummy;
//         ListNode rightCurr = rightDummy;
//         ListNode middleCurr = middleDummy;
//         ListNode curr = head;
//         while (curr != null) {
//             if (curr.val < pivot) {
//                 leftCurr.next = curr;
//                 leftCurr = leftCurr.next;
//             } else if (curr.val > pivot) {
//                 rightCurr.next = curr;
//                 rightCurr = rightCurr.next;
//             } else {
//                 middleCurr.next = curr;
//                 middleCurr = middleCurr.next;
//             }
//             curr = curr.next;
//         }
//         leftCurr.next = null;
//         middleCurr.next = null;
//         rightCurr.next = null;
        
//         ListNode sortedLeft = sortList(leftDummy.next);
//         ListNode sortedRight = sortList(rightDummy.next);
        
//         if (sortedLeft == null) {
//             middleCurr.next = sortedRight;
//             return middleDummy.next;
//         }
        
//         while (leftCurr.next != null) {
//             leftCurr = leftCurr.next;
//         }
//         leftCurr.next = middleDummy.next;
//         middleCurr.next = sortedRight;
//         return sortedLeft;
//     }
// }


class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode mid = getMid(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;
        
        left = sortList(left);
        right = sortList(right);
        
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
        
        if (leftCurr == null) {
            curr.next = rightCurr;
        } else {
            curr.next = leftCurr;
        }
        
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