class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        
        if (n <= 2) {
            return n;
        }
        
        int first = 1;
        int second = 2;
        
        for (int i = 0; i < n - 2; i++) {
            int temp = first;
            first = second;
            second += temp;
        }
        
        return second;
    }
}