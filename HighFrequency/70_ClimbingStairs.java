// DP O(n)time O(1)space
class Solution {
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        
        int first = 1;
        int second = 2;
        
        if (n == 1) {
            return 1;
        }
        
        if (n == 2) {
            return 2;
        }
        
        for (int i = 3; i <= n; i++) {
            int temp = second;
            second += first;
            first = temp;
        }
        
        return second;
    }
}