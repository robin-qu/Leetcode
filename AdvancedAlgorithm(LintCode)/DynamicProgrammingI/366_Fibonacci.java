public class Solution {
    /**
     * @param n: an integer
     * @return: an ineger f(n)
     */
    public int fibonacci(int n) {
        int first = 0;
        int second = 1;
        
        if (n == 1) {
            return first;
        }
        
        if (n == 2) {
            return second;
        }
        
        for (int i = 3; i <= n; i++) {
            int temp = second;
            second = first + second;
            first = temp;
        }
        
        return second;
    }
}