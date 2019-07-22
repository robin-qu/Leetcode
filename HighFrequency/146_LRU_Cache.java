class LRUCache {
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
    
    private Map<Integer, Node> map;
    private Node leftDummy;
    private Node rightDummy;
    private int size;
    private int capacity;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.leftDummy = new Node(-1, -1);
        this.rightDummy = new Node(-1, -1);
        this.leftDummy.next = rightDummy;
        this.rightDummy.prev = leftDummy;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        
        moveToEnd(map.get(key));
        return map.get(key).val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node curr = map.get(key);
            // remove existing key
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            // add new key in the end
            addNode(key, value);
        } else if (size < capacity) {
            addNode(key, value);
            size++;
        } else {  // reaches capacity
            // remove the least recently used item
            map.remove(leftDummy.next.key);
            leftDummy.next.next.prev = leftDummy;
            leftDummy.next = leftDummy.next.next;
            
            addNode(key, value);
        }
    }
    
    private void moveToEnd(Node curr) {
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
        curr.next = rightDummy;
        curr.prev = rightDummy.prev;
        curr.next.prev = curr;
        curr.prev.next = curr;
    }
    
    private void addNode(int key, int val) {
        Node curr = new Node(key, val);
        map.put(key, curr);
        
        curr.next = rightDummy;
        curr.prev = rightDummy.prev;
        curr.prev.next = curr;
        curr.next.prev = curr;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */