class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        if (needle.length() == 0) {
            return 0;
        }

        int m = haystack.length();
        int n = needle.length();
        char[] hh = haystack.toCharArray();
        char[] nn = needle.toCharArray();

        for (int i = 0; i < m - n + 1; i++) {
            for (int j = 0; j < n; j++) {
                if (nn[j] != hh[i + j]) {
                    break;
                }
                if (j == n - 1) {
                    return i;
                }
            }
        }

        return -1;
    }
}