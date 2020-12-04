class Solution {
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }

        int res = 0;
        boolean[] visited = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!visited[i]) {
                res++;
                for (int j = 1; j * i < n; j++) {
                    visited[j * i] = true;
                }
            }
        }

        return res;
    }
}