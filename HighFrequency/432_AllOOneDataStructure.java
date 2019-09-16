class AllOne {
    private class Bucket {
        public int val;
        public Set<String> keys;
        public Bucket prev;
        public Bucket next;
        
        public Bucket(int val) {
            this.val = val;
            this.keys = new HashSet<>();
            this.prev = null;
            this.next = null;
        }
    }
    
    private Map<String, Integer> strToVal;
    private Map<Integer, Bucket> valToBuc;
    private Bucket head;
    private Bucket tail;
    
    /** Initialize your data structure here. */
    public AllOne() {
        this.strToVal = new HashMap<>();
        this.valToBuc = new HashMap<>();
        this.head = new Bucket(0);
        this.tail = new Bucket(Integer.MAX_VALUE);
        valToBuc.put(0, head);
        valToBuc.put(Integer.MAX_VALUE, tail);
        head.next = tail;
        tail.prev = head;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        strToVal.put(key, strToVal.getOrDefault(key, 0) + 1);
        
        int val = strToVal.get(key);
        
        if (!valToBuc.containsKey(val)) {   // add a new Bucket
            Bucket newBuck = new Bucket(val);
            valToBuc.put(val, newBuck);
            Bucket curr = valToBuc.get(val - 1);
            
            newBuck.next = curr.next;
            newBuck.prev = curr;
            curr.next.prev = newBuck;
            curr.next = newBuck;
        }
        
        valToBuc.get(val).keys.add(key);
        
        if (val > 1) {
            Bucket prev = valToBuc.get(val - 1);
            prev.keys.remove(key);
            if (prev.keys.size() == 0) {
                prev.next.prev = prev.prev;
                prev.prev.next = prev.next;
                valToBuc.remove(val - 1);
            }
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!strToVal.containsKey(key)) {
            return;
        }
        
        int val = strToVal.get(key);
        Bucket curr = valToBuc.get(val);
        
        if (val == 1) {
            strToVal.remove(key);
        } else {
            strToVal.put(key, val - 1);
            if (!valToBuc.containsKey(val - 1)) {
                Bucket newBuck = new Bucket(val - 1);
                valToBuc.put(val - 1, newBuck);
                newBuck.next = curr;
                newBuck.prev = curr.prev;
                curr.prev.next = newBuck;
                curr.prev = newBuck;
            }

            valToBuc.get(val - 1).keys.add(key);
        }
        
        curr.keys.remove(key);
        if (curr.keys.size() == 0) {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            valToBuc.remove(val);
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }
        
        return tail.prev.keys.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }
        
        return head.next.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */