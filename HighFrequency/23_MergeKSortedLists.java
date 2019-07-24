/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// PriorityQueue  O(nlogn)time O(n)space
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        int n = lists.length;
        
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });
        
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        
        while (!pq.isEmpty()) {
            curr.next = pq.poll();
            curr = curr.next;
            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }
        
        return dummy.next;
    }
}