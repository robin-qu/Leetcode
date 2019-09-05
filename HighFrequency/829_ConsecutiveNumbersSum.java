// // brute force O(n^2)time O(1)space (TLE)
// class Solution {
//     public int consecutiveNumbersSum(int N) {
//         if (N <= 0) {
//             return 0;
//         }
        
//         int count = 0;
        
//         for (int i = 1; i <= N; i++) {
//             for (int j = i; j <= N; j++) {
//                 if (getSum(i, j) == N) {
//                     count++;
//                 }
//             }
//         }
        
//         return count;
//     }
    
//     private int getSum(int i, int j) {
//         return (i + j) * (j - i + 1) / 2;
//     }
// }


// // two pointers O(n)time O(n)space (TLE)
// class Solution {
//     public int consecutiveNumbersSum(int N) {
//         if (N <= 0) {
//             return 0;
//         }
        
//         int[] nums = new int[N];
//         for (int i = 0; i < N; i++) {
//             nums[i] = i + 1;
//         }
        
//         int count = 0;
//         int sum = 0;
//         int left = 0;
        
//         for (int right = 1; right <= N; right++) {
//             sum += nums[right - 1];
//             while (sum > N) {
//                 sum -= nums[left];
//                 left++;
//             }
            
//             if (sum == N) {
//                 count++;
//             }
//         }
        
//         return count;
//     }
// }


// mathmatical O(sqrt(N))time O(1)space
// [x + (x + (m-1))]m / 2 = mx + m(m-1)/2 = N
class Solution {
    public int consecutiveNumbersSum(int N) {
        if (N <= 0) {
            return 0;
        }
        
        int count = 0;
        for (int m = 1; N - m * (m - 1) / 2 > 0; m ++) {
            if ((N - m * (m - 1) / 2) % m == 0) {  // is an integer
                count++;
            }
        }
        
        return count;
    }
}