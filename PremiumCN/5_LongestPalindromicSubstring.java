class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        int len = 0;
        int[] p = new int[2];
        for (int i = 0; i < n; i++) {
            int[] idxs = getOddPalindrome(i, ss);
            if (idxs[1] - idxs[0] - 1 > len) {
                len = idxs[1] - idxs[0] - 1;
                p[0] = idxs[0];
                p[1] = idxs[1];
            }
        }
        for (int i = 0; i + 1 < n; i ++) {
            int[] idxs = getEvenPalindrome(i, ss);
            if (idxs[1] - idxs[0] - 1 > len) {
                len = idxs[1] - idxs[0] - 1;
                p[0] = idxs[0];
                p[1] = idxs[1];
            }
        }
        
        return s.substring(p[0] + 1, p[1]);
    }
                                        
    private int[] getOddPalindrome(int i, char[] ss) {
        int left = i;
        int right = i;
        while (left >= 0 && right < ss.length && ss[left] == ss[right]) {
            left--;
            right++;
        }
        
        return new int[] {left, right};
    }
                                        
    private int[] getEvenPalindrome(int i, char[] ss) {
        int left = i;
        int right = i + 1;
        while (left >= 0 && right < ss.length && ss[left] == ss[right]) {
            left--;
            right++;
        }
        
        return new int[] {left, right};
    }
}