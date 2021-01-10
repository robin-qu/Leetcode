class LRUCache {
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int cap;

    public LRUCache(int capacity) {
        if (capacity < 0) {
            return;
        }
        this.map = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();
        this.head.next = tail;
        this.tail.prev = head;
        this.cap = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        moveToEnd(map.get(key));
        return map.get(key).val;
    }
    
    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            append(key, value);
        } else {
            map.get(key).val = value;
            moveToEnd(map.get(key));
        }
        if (map.size() > cap) {
            removeFirst();
        }
    }

    private void moveToEnd(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = tail;
        node.prev = tail.prev;
        node.prev.next = node;
        tail.prev = node;
    }

    private void append(int key, int val) {
        Node node = new Node(key, val);
        map.put(key, node);
        node.next = tail;
        node.prev = tail.prev;
        node.prev.next = node;
        tail.prev = node;
    }

    private void removeFirst() {
        Node node = head.next;
        map.remove(node.key);
        head.next = node.next;
        node.next.prev = head;
        node.next = null;
        node.prev = null;
    }

    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node() {
            this.key = 0;
            this.val = 0;
            this.prev = null;
            this.next = null;
        }

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