class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        int n = s2.length();
        char[] ss = s2.toCharArray();
        int[] counts = new int[26];
        int count = 0;
        for (char c : s1.toCharArray()) {
            counts[c - 'a']++;
            if (counts[c - 'a'] == 1) {
                count++;
            }
        }
                
        int left = 0;
        for (int right = 0; right < n; right++) {
            counts[ss[right] - 'a']--;
            if (counts[ss[right] - 'a'] == 0) {
                count--;
            }
            while (left <= right && counts[ss[right] - 'a'] < 0) {
                counts[ss[left] - 'a']++;
                if (counts[ss[left] - 'a'] == 1) {
                    count++;
                }
                left++;
            }
            if (count == 0) {
                return true;
            }
        }

        return false;
    }
}