class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();

        int[] counts = new int[256];
        int count = 0;
        int res = 0;
        int left = 0;
        char[] ss = s.toCharArray();
        for (int right = 0; right < n; right++) {
            counts[ss[right]]++;
            if (counts[ss[right]] == 1) {
                count++;
            }
            while (left <= right && count > 2) {
                counts[ss[left]]--;
                if (counts[ss[left]] == 0) {
                    count--;
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}