class Solution {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[] counts = new int[26];
        char[] ss = s.toCharArray();
        int left = 0;
        int res = 0;
        int max = 0;
        for (int right = 0; right < n; right++) {
            counts[ss[right] - 'A']++;
            max = Math.max(max, counts[ss[right] - 'A']);
            while (max < right - left + 1 - k) {
                counts[ss[left] - 'A']--;
                left++;
            }
            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}