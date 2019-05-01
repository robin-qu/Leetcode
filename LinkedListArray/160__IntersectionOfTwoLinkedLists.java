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
//         ListNode currA = headA;
//         ListNode currB = headB;
//         int sizeA = 0;
//         int sizeB = 0;
//         while (currA != null) {
//             sizeA++;
//             currA = currA.next;
//         }
//         while (currB != null) {
//             sizeB++;
//             currB = currB.next;
//         }
//         currA = headA;
//         currB = headB;
//         if (sizeA > sizeB) {
//             for (int i = 0; i < sizeA - sizeB; i++) {
//                 currA = currA.next;
//             }
//         }
//         if (sizeA < sizeB) {
//             for (int i = 0; i < sizeB - sizeA; i++) {
//                 currB = currB.next;
//             }
//         }
//         while (currA != null && currB != null) {
//             if (currA == currB) {
//                 return currA;
//             } 
//             currA = currA.next;
//             currB = currB.next;
//         }
//         return null;
//     }
// }

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            if (a == null) {
                a = headB;
            } else {
                a = a.next;
            }
            
            if (b == null) {
                b = headA;
            } else {
                b = b.next;
            }
        }
        return a;
    }
}

// // Finds cycle
// public class Solution {
//     public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//         if (headA == null || headB == null) {
//             return null;
//         }
        
//         ListNode currB = headB;
        
//         while (currB.next != null) {
//             currB = currB.next;
//         }
//         currB.next = headB;
        
//         ListNode slow = headA;
//         ListNode fast = headA;
        
//         while (fast != null && fast.next != null) {
//             fast = fast.next.next;
//             slow = slow.next;
//             if (fast == slow) {
//                 ListNode curr = headA;
//                 while (curr != slow) {
//                     curr = curr.next;
//                     slow = slow.next;
//                 }
//                 currB.next = null;
//                 return curr;
//             }
//         }
//         currB.next = null;
//         return null;
//     }
// }