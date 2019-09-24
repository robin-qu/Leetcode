/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/

// O(n*len)time O(n)space
class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }
        
        Node curr = head;
        while (curr != null) {
            if (curr.child != null) {
                Node newHead = flatten(curr.child);
                curr.child = null;
                Node tail = newHead;
                while (tail.next != null) {
                    tail = tail.next;
                }
                
                if (curr.next != null) {
                    tail.next = curr.next;
                    curr.next.prev = tail;
                }
                curr.next = newHead;
                newHead.prev = curr;
                curr = tail.next;
            } else {
                curr = curr.next;
            }
        }
        
        return head;
    }
}