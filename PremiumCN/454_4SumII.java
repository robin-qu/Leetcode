class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || B == null || C == null || D == null) {
            return 0;
        }

        int n = A.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map.put(0 - A[i] - B[j], map.getOrDefault(0 - A[i] - B[j], 0) + 1);
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map.containsKey(C[i] + D[j])) {
                    res += map.get(C[i] + D[j]);
                }
            }
        }

        return res;
    }
} 