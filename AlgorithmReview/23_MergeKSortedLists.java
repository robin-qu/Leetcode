// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode(int x) { val = x; }
//  * }
//  */
// class Solution {
//     class ListNodeComparator implements Comparator<ListNode> {
//         @Override
//         public int compare(ListNode n1, ListNode n2) {
//             return n1.val - n2.val;
//         }
//     }
    
//     public ListNode mergeKLists(ListNode[] lists) {
//         if (lists == null || lists.length == 0) {
//             return null;
//         }
        
//         int size = lists.length;
//         ListNode dummy = new ListNode(-1);
//         ListNode curr = dummy;
        
//         PriorityQueue<ListNode> pq = new PriorityQueue<>(new ListNodeComparator());
        
//         for (int i = 0; i < size; i++) {
//             if (lists[i] != null) {
//                 pq.offer(lists[i]);
//             }
//         }
        
//         while (!pq.isEmpty()) {
//             ListNode next = pq.poll();
//             if (next.next != null) {
//                 pq.offer(next.next);
//             }
//             curr.next = next;
//             curr = curr.next;
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
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        return merge(lists, 0, lists.length - 1);
    }
    
    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        
        int mid = start + (end - start) / 2;
        
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid + 1, end);
        
        return mergeTwoLists(left, right);
    }
    
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        ListNode curr1 = list1;
        ListNode curr2 = list2;
        
        while (curr1 != null && curr2 != null) {
            if (curr1.val <= curr2.val) {
                curr.next = curr1;
                curr1 = curr1.next;
            } else {
                curr.next = curr2;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        
        curr.next = (curr1 == null ? curr2 : curr1);
        
        return dummy.next;
    }
}