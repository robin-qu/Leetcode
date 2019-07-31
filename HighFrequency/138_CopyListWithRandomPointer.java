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

// // O(n)time and O(n)space
// class Solution {
//     public Node copyRandomList(Node head) {
//         Node dummy = new Node();
//         Map<Node, Node> map = new HashMap<>();
        
//         // copy next pointer
//         Node curr1 = head;
//         Node curr2 = dummy;
//         while (curr1 != null) {
//             Node newNode = new Node();
//             newNode.val = curr1.val;
//             curr2.next = newNode;
//             map.put(curr1, newNode);
            
//             curr1 = curr1.next;
//             curr2 = curr2.next;
//         }
        
//         // copy random pointer
//         curr1 = head;
//         curr2 = dummy.next;
//         while (curr1 != null) {
//             curr2.random = map.get(curr1.random);
//             curr1 = curr1.next;
//             curr2 = curr2.next;
//         }
        
//         return dummy.next;
//     }
// }


// O(n)time and O(1)space
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        // copy next pointer
        Node curr = head;
        while (curr != null) {
            Node newNode = new Node(curr.val, curr.next, null);
            curr.next = newNode;
            curr = curr.next.next;
        }
        
        // copy random pointer
        curr = head;
        while (curr != null) {
            curr.next.random = curr.random == null ? null : curr.random.next;
            curr = curr.next.next;
        }
        
        return extract(head);
    }
    
    private Node extract(Node head) {
        Node curr = head;
        Node dummy = new Node();
        Node curr2 = dummy;
        
        while (curr != null) {
            curr2.next = curr.next;
            curr.next = curr.next == null ? null : curr.next.next;
            curr = curr.next;
            curr2 = curr2.next;
        }
        
        return dummy.next;
    }
}