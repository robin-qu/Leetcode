// // PriorityQueue O(logn)set O(nlogn)get
// class TimeMap {
//     class Pair implements Comparable<Pair> {
//         public String value;
//         public int timestamp;
        
//         public Pair(String value, int timestamp) {
//             this.value = value;
//             this.timestamp = timestamp;
//         }
        
//         @Override
//         public int compareTo(Pair other) {
//             return this.timestamp - other.timestamp;
//         }
//     }
    
//     private Map<String, PriorityQueue<Pair>> map;
    
//     /** Initialize your data structure here. */
//     public TimeMap() {
//         this.map = new HashMap<>();
//     }
    
//     public void set(String key, String value, int timestamp) {
//         if (!this.map.containsKey(key)) {
//             this.map.put(key, new PriorityQueue<Pair>());
//         }
//         this.map.get(key).offer(new Pair(value, timestamp));
//     }
    
//     public String get(String key, int timestamp) {
//         if (!map.containsKey(key)) {
//             return "";
//         }
        
//         PriorityQueue<Pair> pq = map.get(key);
//         Pair temp = null;
//         while (!pq.isEmpty() && pq.peek().timestamp <= timestamp) {
//             temp = pq.poll();
//         }
        
//         if (temp == null) {
//             return "";
//         }
        
//         pq.offer(temp);
//         return temp.value;
//     }
// }

// /**
//  * Your TimeMap object will be instantiated and called as such:
//  * TimeMap obj = new TimeMap();
//  * obj.set(key,value,timestamp);
//  * String param_2 = obj.get(key,timestamp);
//  */


// Binary Search O(1)set O(logn)get
class TimeMap {
    class Pair {
        public String value;
        public int timestamp;
        
        public Pair(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
    
    private Map<String, List<Pair>> map;
    
    /** Initialize your data structure here. */
    public TimeMap() {
        this.map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!this.map.containsKey(key)) {
            this.map.put(key, new ArrayList<Pair>());
        }
        this.map.get(key).add(new Pair(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        
        List<Pair> list = map.get(key);
        if (list.get(0).timestamp > timestamp) {
            return "";
        }
        return binarySearch(timestamp, list);
    }
    
    private String binarySearch(int timestamp, List<Pair> list) {
        int left = 0;
        int right = list.size() - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (list.get(mid).timestamp > timestamp) {
                right = mid;
            } else if (list.get(mid).timestamp < timestamp) {
                left = mid;
            } else {
                return list.get(mid).value;
            }
        }
        
        if (list.get(right).timestamp <= timestamp) {
            return list.get(right).value;
        }
        
        return list.get(left).value;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */