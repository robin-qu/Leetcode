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
        if (hashTable == null || hashTable.length == 0) {
            return hashTable;
        }
        
        int size = hashTable.length;
        int newSize = 2 * size;
        ListNode[] newHashTable = new ListNode[newSize];
        
        // manual initialization:
        for (int i = 0; i < newSize; i++) {
            newHashTable[i] = null;
        }
        
        for (int i = 0; i < size; i++) {
            ListNode head = hashTable[i];
            while (head != null) {
                int hashCode = (head.val % newSize + newSize) % newSize;
                
                if (newHashTable[hashCode] == null) {
                    newHashTable[hashCode] = new ListNode(head.val);
                } else {
                    ListNode curr = newHashTable[hashCode];
                    while (curr.next != null) {
                        curr = curr.next;
                    }
                    curr.next = new ListNode(head.val);
                }
                
                head = head.next;
            }
        }
        
        return newHashTable;
    }
};
