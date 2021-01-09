class Solution {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int res = 0;
        int[] counts = new int[26];
        char[] ss = s.toCharArray();
        int maxCount = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            counts[ss[right] - 'A']++;
            if (counts[ss[right] - 'A'] > maxCount) {
                maxCount = counts[ss[right] - 'A'];
            }
            while (left <= right && right - left + 1 - maxCount > k) {
                counts[ss[left] - 'A']--;
                left++;
            }
            res = Math.max(right - left + 1, res);
        }

        return res;
    }
} 