// O(n)time O(1)space
class Solution {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        
        int first = 0;
        int second = 1;
        
        for (int i = 0; i < N - 1; i++) {
            int temp = second;
            second += first;
            first = temp;
        }
        
        return second;
    }
}