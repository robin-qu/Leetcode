/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param hashTable: A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */    
    public ListNode[] rehashing(ListNode[] hashTable) {
        int newSize = 2 * hashTable.length;
        ListNode[] res = new ListNode[newSize];
        
        for (ListNode node : hashTable) {
            ListNode curr = node;
            while (curr != null) {
                rehash(new ListNode(curr.val), hashcode(curr.val, newSize), res);
                curr = curr.next;
            }
        }
        
        return res;
    }
    
    private void rehash(ListNode node, int code, ListNode[] table) {
        if (table[code] == null) {
            table[code] = node;
        } else {
            ListNode curr = table[code];
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;
        }
    }
    
    int hashcode(int key, int capacity) {
        return (key % capacity + capacity) % capacity;
    }
};
