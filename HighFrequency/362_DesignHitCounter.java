// // O(logn)time O(n)space
// class HitCounter {
//     private List<Integer> count;

//     /** Initialize your data structure here. */
//     public HitCounter() {
//         this.count = new ArrayList<>();
//     }
    
//     /** Record a hit.
//         @param timestamp - The current timestamp (in seconds granularity). */
//     public void hit(int timestamp) {
//         count.add(timestamp);
//     }
    
//     /** Return the number of hits in the past 5 minutes.
//         @param timestamp - The current timestamp (in seconds granularity). */
//     public int getHits(int timestamp) {
//         if (count.size() != 0 && timestamp >= 300) {
//             int idx = binarySearch(timestamp - 300);
//             if (idx == -1) {
//                 count = new ArrayList<>();
//             } else {
//                 count = count.subList(idx, count.size());
//             }
//         }
//         return count.size();
//     }
    
//     // return the first element that is bigger than t
//     private int binarySearch(int t) {
//         int left = 0;
//         int right = count.size() - 1;
        
//         while (left + 1 < right) {
//             int mid = left + (right - left) / 2;
            
//             if (count.get(mid) <= t) {
//                 left = mid;
//             } else {
//                 right = mid;
//             }
//         }
        
//         if (count.get(left) > t) {
//             return left;
//         }
        
//         if (count.get(right) > t) {
//             return right;
//         }
        
//         return -1;
//     }
// }

// /**
//  * Your HitCounter object will be instantiated and called as such:
//  * HitCounter obj = new HitCounter();
//  * obj.hit(timestamp);
//  * int param_2 = obj.getHits(timestamp);
//  */


// O(logn)time O(n)space
class HitCounter {
    private int[] time;
    private int[] count;

    /** Initialize your data structure here. */
    public HitCounter() {
        this.time = new int[300];
        this.count = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int idx = timestamp % 300;
        if (time[idx] != timestamp) {
            time[idx] = timestamp;
            count[idx] = 1;
        } else {
            count[idx]++;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int hits = 0;
        for (int i = 0; i < 300; i++) {
            if (time[i] > timestamp - 300) {
                hits += count[i];
            }
        }
        
        return hits;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */