class RandomizedCollection {
    private Random rand;
    private Map<Integer, Set<Integer>> count;  // val -> indices in the list
    private List<Integer> list;  // list of values

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.rand = new Random();
        this.count = new HashMap<>();
        this.list = new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        
        if (!count.containsKey(val)) {
            count.put(val, new LinkedHashSet<>());
            count.get(val).add(list.size() - 1);
            return true;
        } else {
            count.get(val).add(list.size() - 1);
            return false;
        }
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!count.containsKey(val)) {
            return false;
        }
        
        Set<Integer> indices = count.get(val);
        int idx = indices.iterator().next();  // index of the element to be removed
        indices.remove(idx);  // remove the index of element in the map
        if (indices.size() == 0) {
            count.remove(val);
        }
        
        list.set(idx, list.get(list.size() - 1));  // swap
        
        if (count.containsKey(list.get(idx))) {  // check if count still has the key (replace the same element)
            count.get(list.get(idx)).remove(list.size() - 1);
            count.get(list.get(idx)).add(idx);
        }
            
        list.remove(list.size() - 1);  // remove the last element
        
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */