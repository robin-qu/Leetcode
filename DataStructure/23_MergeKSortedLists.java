/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// // heap
// class Solution {
//     public ListNode mergeKLists(ListNode[] lists) {
//         ListNode dummy = new ListNode(0);
//         if (lists == null || lists.length == 0) {
//             return dummy.next;
//         }
        
//         PriorityQueue<ListNode> pq = new PriorityQueue<>(new ListNodeComparator());
//         for (ListNode node : lists) {
//             if (node != null) {
//                 pq.add(node);
//             }
//         }
        
//         ListNode curr = dummy;
//         while (!pq.isEmpty()) {
//             ListNode next = pq.poll();
//             if (next.next != null) {
//                 pq.add(next.next);
//             }
//             curr.next = next;
//             curr = curr.next;
//         }
        
//         return dummy.next;
//     }
    
//     class ListNodeComparator implements Comparator<ListNode> {
//         public int compare(ListNode n1, ListNode n2) {
//             return n1.val - n2.val;
//         }
//     }
// }

// // merge one by one
// class Solution {
//     public ListNode mergeKLists(ListNode[] lists) {
//         if (lists == null || lists.length == 0) {
//             return null;
//         }
        
//         ListNode dummy = new ListNode(0);
//         dummy.next = lists[0];
        
//         for (int i = 1; i < lists.length; i++) {
//             mergeTwoList(dummy, lists[i]);
//         }
//         return dummy.next;
//     }
    
//     // merge list2 to list1
//     private void mergeTwoList(ListNode dummy, ListNode node2) {
//         ListNode curr = dummy;
//         ListNode curr1 = dummy.next;
//         ListNode curr2 = node2;
//         while (curr2 != null) {
//             while (curr1 != null && curr1.val <= curr2.val) {
//                 curr1 = curr1.next;
//                 curr = curr.next;
//             }
//             ListNode temp = curr2.next;
//             curr2.next = curr.next;
//             curr.next = curr2;
//             curr2 = temp;
//             curr = curr.next;
//         }
//     }
// }

// Divide and conquer
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        return helper(lists, 0, lists.length - 1);
    }
    
    private ListNode helper(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        
        int mid = left + (right - left) / 2;
        
        ListNode leftList = helper(lists, left, mid);
        ListNode rightList = helper(lists, mid + 1, right);
        
        return mergeTwoList(leftList, rightList);
    }
    
    // merge list2 to list1
    private ListNode mergeTwoList(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode curr1 = node1;
        ListNode curr2 = node2;
        
        while (curr1 != null && curr2 != null) {
            if (curr1.val < curr2.val) {
                curr.next = curr1;
                curr1 = curr1.next;
            } else {
                curr.next = curr2;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        
        if (curr1 != null) {
            curr.next = curr1;
        } else {
            curr.next = curr2;
        }
        
        return dummy.next;
    }
}