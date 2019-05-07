class LRUCache {
    class Node {
        public int key;
        public int val;
        public Node prev;
        public Node next;
        
        public Node(int key, int val, Node prev, Node next) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }
    
    private int size;
    private int capacity;
    private Node dummyHead;
    private Node dummyTail;
    private Map<Integer, Node> hash;
    
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.hash = new HashMap<>();
        this.dummyHead = new Node(-1, -1, null, null);
        this.dummyTail = new Node(-1, -1, dummyHead, null);
        this.dummyHead.next = this.dummyTail;
    }
    
    public int get(int key) {
        if (!hash.containsKey(key)) {
            return -1;
        }
        moveToTail(hash.get(key));
        return hash.get(key).val;
    }
    
    public void put(int key, int value) {
        if (hash.containsKey(key)) {
            Node curr = hash.get(key);
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            addNode(key, new Node(key, value, dummyTail.prev, dummyTail));
        } else if (size == 0) {
            addNode(key, new Node(key, value, dummyHead, dummyTail));
            size++;
        } else if (size < capacity) {
            addNode(key, new Node(key, value, dummyTail.prev, dummyTail));
            size++;
        } else {  // size >= capacity
            hash.remove(dummyHead.next.key);
            dummyHead.next.next.prev = dummyHead;
            dummyHead.next = dummyHead.next.next;
            addNode(key, new Node(key, value, dummyTail.prev, dummyTail));
        }
    }
    
    private void moveToTail(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = dummyTail.prev;
        node.next = dummyTail;
        node.prev.next = node;
        node.next.prev = node;
    }
    
    private void addNode(int key, Node newNode) {
        newNode.prev.next = newNode;
        newNode.next.prev = newNode;
        hash.put(key, newNode);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */