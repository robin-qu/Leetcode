// // initial version O(n)time O(n)space
// class Solution {
//     private double[] prob;

//     public Solution(int[] w) {
//         int n = w.length;
//         prob = new double[n];
//         double sum = 0; 
//         for (int num : w) {
//             sum += num;
//         }
        
//         for (int i = 0; i < n; i++) {
//             prob[i] = (double) w[i] / sum;
//             if (i > 0) {
//                 prob[i] += prob[i - 1];
//             }
//         }
//     }
    
//     public int pickIndex() {
//         double x = Math.random();
//         for (int i = 0; i < prob.length; i++) {
//             if (x < prob[i]) {
//                 return i;
//             }
//         }
        
//         return -1;
//     }
// }

// /**
//  * Your Solution object will be instantiated and called as such:
//  * Solution obj = new Solution(w);
//  * int param_1 = obj.pickIndex();
//  */


// binarysearch version O(n)timeO(logn)pickindex O(n)space
class Solution {
    private int n;
    private int[] sums;
    private Random rand;

    public Solution(int[] w) {
        this.n = w.length;
        this.sums = new int[n];
        this.rand = new Random();
        sums[0] = w[0]; 
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + w[i];
        }
    }
    
    public int pickIndex() {
        int target = rand.nextInt(sums[n - 1]) + 1;
        int left = 0;
        int right = n - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (sums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (target > sums[left]) {
            return right;
        }
        
        return left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */