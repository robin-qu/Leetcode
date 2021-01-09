class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return helper(s.toCharArray(), 0, s.length() - 1, k);
    }

    private int helper(char[] ss, int start, int end, int k) {
        if (start > end) {
            return 0;
        }

        int[] counts = new int[26];
        int seen = 0;
        int valid = 0;
        for (int i = start; i <= end; i++) {
            counts[ss[i] - 'a']++;
            if (counts[ss[i] - 'a'] == 1) {
                seen++;
            }
            if (counts[ss[i] - 'a'] == k) {
                valid++;
            }
        }

        if (valid == seen) {
            return end - start + 1;
        }

        int prev = start;
        int res = 0;
        for (int i = start; i <= end; i++) {
            if (counts[ss[i] - 'a'] < k) {
                res = Math.max(res, helper(ss, prev, i - 1, k));
                prev = i + 1;
            }
        }
        res = Math.max(res, helper(ss, prev, end, k));

        return res;
    }
}