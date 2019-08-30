class LFUCache {
    class Node {
        public int key;
        public int val;
        public int count;
        public Node prev;
        public Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.count = 1;
            this.prev = null;
            this.next = null;
        }
    }

    class DList {
        public Node left;
        public Node right;
        public int size;

        public DList() {
            this.left = new Node(0, 0);
            this.right = new Node(0, 0);
            this.left.next = this.right;
            this.right.prev = this.left;
            this.size = 0;
        }

        public void addLast(Node curr) {
            curr.next = right;
            curr.prev = right.prev;
            curr.next.prev = curr;
            curr.prev.next = curr;
            this.size++;
        }

        public Node removeFirst() {
            Node res = left.next;
            left.next = left.next.next;
            left.next.prev = left;
            this.size--;
            return res;
        }

        public void remove(Node curr) {
            curr.next.prev = curr.prev;
            curr.prev.next = curr.next;
            this.size--;
        }

        public int size() {
            return this.size;
        }
    }

    private int min;  // the current minimum frequency
    private int size;
    private int capacity;
    private Map<Integer, Node> map;  // key -> Node
    private Map<Integer, DList> freq; // count -> list of nodes with that count, whose leftmost element is the least recently used one, rightmost element is the most recently used one

    public LFUCache(int capacity) {
        this.min = Integer.MAX_VALUE;
        this.size = 0;
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.freq = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        // key already exists in the cache
        Node curr = map.get(key);
        update(curr);

        return curr.val;
    }

    public void put(int key, int value) {
        // corner case
        if (capacity == 0) {
            return;
        }

        // key already exists, just call get(key) and change its value
        if (get(key) != -1) {
            map.get(key).val = value;
            return;
        }

        // new node
        Node curr = new Node(key, value);

        if (size == capacity) {
            Node removed = freq.get(min).removeFirst(); // remove the least recently used element out of the least frequently used ones
            map.remove(removed.key);
            size--;
        }
        
        // with new node coming in, the minimum frequency must be 1
        min = 1;
        if (!freq.containsKey(curr.count)) {
            freq.put(curr.count, new DList());
        }
        freq.get(curr.count).addLast(curr);
        map.put(key, curr);
        size++;
    }

    private void update(Node curr) {
        DList list = freq.get(curr.count);
        list.remove(curr);
        if (curr.count == min && list.size() == 0) {  // the min value needs to be changed
            min++;
        }

        curr.count++;
        
        // put the current node into corresponding frequency list
        if (!freq.containsKey(curr.count)) {
            freq.put(curr.count, new DList());
        }
        freq.get(curr.count).addLast(curr);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */