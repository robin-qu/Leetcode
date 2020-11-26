class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        int res = 0;
        int left = 0;
        int[] counts = new int[256];
        int count = 0;
        for (int right = 0; right < n; right++) {
            char c = ss[right];
            counts[c]++;
            if (counts[c] == 1) {
                count++;
            }
            while (count > k) {
                counts[ss[left]]--;
                if (counts[ss[left]] == 0) {
                    count--;
                }
                left++;
            }
            res = Math.max(right - left + 1, res);
        }

        return res;
    }
}