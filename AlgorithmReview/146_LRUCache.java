class LRUCache {
    class DoublyListNode {
        public int key;
        public int value;
        public DoublyListNode prev;
        public DoublyListNode next;
        
        public DoublyListNode(int key, int value, DoublyListNode prev,
                              DoublyListNode next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
    
    private Map<Integer, DoublyListNode> map;
    private int capacity;
    private int size;
    private DoublyListNode head;
    private DoublyListNode tail;
    
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        this.head = new DoublyListNode(-1, -1, null, null);
        this.tail = new DoublyListNode(-1, -1, head, null);
        this.head.next = this.tail;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        
        // move the node with the given to the tail
        moveToTail(key);
        
        return map.get(key).value;
    }
    
    public void put(int key, int value) {
        // delete the old key
        if (map.containsKey(key)) {
            DoublyListNode curr = map.get(key);
            curr.next.prev = curr.prev;
            curr.prev.next = curr.next;
            size--;
        }
        
        // add the new key to the tail
        DoublyListNode curr = new DoublyListNode(key, value, tail.prev, tail);
        curr.prev.next = curr;
        tail.prev = curr;
        map.put(key, curr);
        size++;
        
        if (size > capacity) { // remove the head;
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
            size--;
        }
    }
    
    private void moveToTail(int key) {
        DoublyListNode curr = map.get(key);
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
        tail.prev.next = curr;
        curr.prev = tail.prev;
        curr.next = tail;
        tail.prev = curr;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */