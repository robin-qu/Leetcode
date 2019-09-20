// prefix sum  O(n)time O(K)space
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        
        int[] map = new int[K];
        int sum = 0;
        map[sum]++;
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            sum = (sum + A[i]) % K;
            if (sum < 0) {
                sum += K;
            }
            
            res += map[sum];
            map[sum]++;
        }
        
        return res;
    }
}