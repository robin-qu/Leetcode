class LRUCache {
    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            remove(map.get(key));
            addToTail(map.get(key));
            return map.get(key).val;
        }
        
        return -1;
    }
    
    public void put(int key, int val) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        
        Node curr = new Node(key, val);
        map.put(key, curr);
        addToTail(curr);
        
        if (map.size() > capacity) {
            map.remove(head.next.key);
            remove(head.next);
        }
    }
                                        
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;        
    }
                                        
    private void addToTail(Node node) {
        node.next = tail;
        node.prev = tail.prev;
        node.prev.next = node;
        tail.prev = node;
    }
                                        
    class Node {
        public int key;
        public int val;
        public Node prev;
        public Node next;
        
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */