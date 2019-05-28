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
// class Solution {
//     public Node copyRandomList(Node head) {
//         if (head == null) {
//             return head;
//         }
        
//         Map<Node, Node> map = new HashMap<>();
        
//         Node curr = head;
        
//         while (curr != null) {
//             map.put(curr, new Node(curr.val, null, null));
//             curr = curr.next;
//         }
        
//         for (Node node : map.keySet()) {
//             map.get(node).next = map.get(node.next);
//             map.get(node).random = map.get(node.random);
//         }
        
//         return map.get(head);
//     }
// }


class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        
        Node curr = head;
        
        // copy val and next
        while (curr != null) {
            Node next = curr.next;
            curr.next = new Node(curr.val, curr.next, null);
            curr = curr.next.next;
        }
        
        // copy random
        curr = head;
        while (curr != null && curr.next != null) {
            curr.next.random = curr.random == null ? null : curr.random.next;
            curr = curr.next.next;
        }
        
        // split
        Node dummy1 = new Node(-1);
        Node curr1 = dummy1;
        Node dummy2 = new Node(-1);
        Node curr2 = dummy2;
        curr = head;
        
        while (curr != null) {
            curr1.next = curr;
            curr1 = curr1.next;
            curr2.next = curr.next;
            curr2 = curr2.next;
            curr = curr.next.next;
        }
        curr1.next = null;
        curr2.next = null;
        head = dummy1.next;

        return dummy2.next;
    }
}