class MyHashMap {
    class Node {
        public int key;
        public int val;
        public Node next;
        
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }
    
    private Node[] buckets;
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        this.buckets = new Node[10000];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int idx = getIdx(key);
        if (buckets[idx] == null) {
            buckets[idx] = new Node(key, value);
        } else {
            Node curr = buckets[idx];
            if (curr.key == key) {
                curr.val = value;
            } else {
                while (curr.next != null && curr.next.key != key) {
                    curr = curr.next;
                }

                if (curr.next == null) {
                    curr.next = new Node(key, value);
                } else {
                    curr.next.val = value;
                }
            }
                
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int idx = getIdx(key);
        if (buckets[idx] == null) {
            return -1;
        } else {
            Node curr = buckets[idx];
            while (curr != null && curr.key != key) {
                curr = curr.next;
            }
            return curr == null ? -1 : curr.val;
        }
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int idx = getIdx(key);
        if (buckets[idx] != null) {
            Node curr = buckets[idx];
            if (curr.key == key) {
                buckets[idx] = curr.next;
            } else {
                while (curr.next != null && curr.next.key != key) {
                    curr = curr.next;
                }
                
                if (curr.next != null && curr.next.key == key) {
                    curr.next = curr.next.next;
                }
            }
        }
    }
    
    private int getIdx(int key) {
        return Integer.hashCode(key) % buckets.length;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key); 
 * obj.remove(key);
 */