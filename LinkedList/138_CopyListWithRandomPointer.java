/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/

// // Use a hashmap to store relations
// class Solution {
//     public Node copyRandomList(Node head) {
//         if (head == null) {
//             return null;
//         }
        
//         Map<Node, Node> mapping = buildMap(head);
        
//         Node curr = head;
//         while (curr != null) {
//             mapping.get(curr).random = mapping.get(curr.random);
//             curr = curr.next;
//         }
        
//         return mapping.get(head);
//     }
    
//     private Map<Node, Node> buildMap(Node head) {
//         Map<Node, Node> mapping = new HashMap<>();
//         Node newHead = new Node(head.val, null, null);
//         Node curr = head;
//         Node newCurr = newHead;
//         mapping.put(head, newHead);
        
//         while (curr.next != null) {
//             curr = curr.next;
//             newCurr.next = new Node(curr.val, null, null);
//             newCurr = newCurr.next;
//             mapping.put(curr, newCurr);
//         }
        
//         return mapping;
//     }
// }


// Stores relations in the next node
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        // Copy next
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = new Node(curr.val, next, null);
            curr = curr.next.next;
        }
        
        // Copy random
        curr = head;
        while (curr != null) {
            if (curr.random == null) {
                curr.next.random = null;
            } else {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        
        return extractList(head);
    }
    
    private Node extractList(Node head) {
        Node curr = head;
        Node newHead = head.next;
        Node newCurr = newHead;
        
        while (curr.next.next != null) {
            curr.next = curr.next.next;
            curr = curr.next;
            newCurr.next = newCurr.next.next;
            newCurr = newCurr.next;
        }
        newCurr.next = null;
        curr.next = null;
        
        return newHead;
    }
}