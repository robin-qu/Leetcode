// // Initial version O(n)getRandom() 
// class RandomizedSet {
//     private Set<Integer> set;
    
//     /** Initialize your data structure here. */
//     public RandomizedSet() {
//         this.set = new HashSet<>();
//     }
    
//     /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
//     public boolean insert(int val) {
//         boolean exist = set.contains(val);
        
//         if (!exist) {
//             set.add(val);
//         }
        
//         return !exist;
//     }
    
//     /** Removes a value from the set. Returns true if the set contained the specified element. */
//     public boolean remove(int val) {
//         boolean exist = set.contains(val);
        
//         if (exist) {
//             set.remove(val);
//         }
        
//         return exist;
//     }
    
//     /** Get a random element from the set. */
//     public int getRandom() {
//         int i = 0;
        
//         Random rand = new Random();
//         int pick = rand.nextInt(set.size());
        
//         for (int num : set) {
//             if (pick == i) {
//                 return num;
//             }
//             i++;
//         }
        
//         return -1;
//     }
// }

// /**
//  * Your RandomizedSet object will be instantiated and called as such:
//  * RandomizedSet obj = new RandomizedSet();
//  * boolean param_1 = obj.insert(val);
//  * boolean param_2 = obj.remove(val);
//  * int param_3 = obj.getRandom();
//  */


// Initial version O(n)getRandom() 
class RandomizedSet {
    private Map<Integer, Integer> locs;
    private List<Integer> nums;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.locs = new HashMap<>();
        this.nums = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean exist = locs.containsKey(val);
        
        if (!exist) {
            locs.put(val, nums.size());
            nums.add(val);
        }
        
        return !exist;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean exist = locs.containsKey(val);
        
        if (exist) {
            // swap the target with the last element in the list
            nums.set(locs.get(val), nums.get(nums.size() - 1));
            locs.put(nums.get(nums.size() - 1), locs.get(val));
            // remove in locs and nums
            locs.remove(val);
            nums.remove(nums.size() - 1);
        }
        
        return exist;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random rand = new Random();
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */