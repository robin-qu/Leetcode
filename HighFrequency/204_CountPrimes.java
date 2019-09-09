// O(n)time O(n)space
class Solution {
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        
        boolean[] used = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (used[i]) {
                continue;
            }
            
            count++;
            
            int j = 2;
            while (i * j < n) {
                used[i * j] = true;
                j++;
            }
        }
        
        return count;
    }
}