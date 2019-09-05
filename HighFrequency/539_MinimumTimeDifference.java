// // O(nlogn)time O(1)space
// class Solution {
//     public int findMinDifference(List<String> timePoints) {
//         if (timePoints == null || timePoints.size() < 2) {
//             return 0;
//         }
        
//         int n = timePoints.size();
        
//         Collections.sort(timePoints);
        
//         int min = Integer.MAX_VALUE;
//         for (int i = 1; i < n; i++) {
//             min = Math.min(min, getDiff(timePoints.get(i - 1), timePoints.get(i)));
//         }
//         min = Math.min(min, getDiff(timePoints.get(n - 1), "24:00") + getDiff(timePoints.get(0), "00:00"));   //  first and last
        
//         return min;
//     }
    
//     private int getDiff(String a, String b) {
//         String[] aa = a.split(":");
//         int ta = Integer.parseInt(aa[0]) * 60 + Integer.parseInt(aa[1]);
//         String[] bb = b.split(":");
//         int tb = Integer.parseInt(bb[0]) * 60 + Integer.parseInt(bb[1]);
        
//         return Math.abs(tb - ta);
//     }
// }


// bucket O(n)time O(1)space
class Solution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints == null || timePoints.size() < 2) {
            return 0;
        }
        
        int n = timePoints.size();
        
        boolean[] bucket = new boolean[24 * 60];
        
        for (String time : timePoints) {
            int idx = 60 * Integer.parseInt(time.substring(0, 2)) + Integer.parseInt(time.substring(3, 5));
            if (bucket[idx]) {  // duplicate
                return 0;  
            }
            bucket[idx] = true;
        }
        
        int min = Integer.MAX_VALUE;
        int prev = 0;
        while (!bucket[prev]) {
            prev++;
        }
        
        for (int i = 1; i < bucket.length; i++) {            
            if (bucket[i] && bucket[prev] && i != prev) {
                min = Math.min(min, i - prev);
                prev = i;
            }
        }
        
        int front = 0;
        while (!bucket[front]) {
            front++;
        }
        int tail = 24 * 60 - 1;
        while (!bucket[tail]) {
            tail--;
        }
        
        min = Math.min(min, 24 * 60 + front - tail);
        
        return min;
    }
}