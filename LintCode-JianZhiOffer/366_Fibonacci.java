public class Solution {
    /**
     * @param n: an integer
     * @return: an ineger f(n)
     */
    public int fibonacci(int n) {
        if (n == 1) {
            return 0;
        }
        
        int first = 0;
        int second = 1;
        
        for (int i = 2; i < n; i++) {
            int temp = second;
            second += first;
            first = temp;
        }
        
        return second;
    }
}