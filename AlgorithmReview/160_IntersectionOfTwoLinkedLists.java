/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
// public class Solution {
//     public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//         if (headA == null || headB == null) {
//             return null;
//         }
        
//         ListNode tailB = headB;
//         while (tailB.next != null) {
//             tailB = tailB.next;
//         }
//         tailB.next = headB;
        
//         ListNode fast = headA;
//         ListNode slow = headA;
        
//         while (fast != null && fast.next != null) {
//             fast = fast.next.next;
//             slow = slow.next;
//             if (fast == slow) {
//                 ListNode pointer = headA;
//                 while (pointer != slow) {
//                     pointer = pointer.next;
//                     slow = slow.next;
//                 }
//                 tailB.next = null;
//                 return pointer;
//             }
//         }
        
//         tailB.next = null;
//         return null;
//     }
// }


public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode currA = headA;
        ListNode currB = headB;
        
        while (currA != currB) {
            currA = currA == null ? headA : currA.next;
            currB = currB == null ? headB : currB.next;
        }
        
        return currA;
    }
}