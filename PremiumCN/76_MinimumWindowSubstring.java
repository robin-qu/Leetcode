class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null) {
            return "";
        }
        
        int m = s.length();
        int n = t.length();
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();        
        int[] counts = new int[256];
        int count = 0;
        for (char c : tt) {
            counts[c]++;
            if (counts[c] == 1) {
                count++;
            }
        }
        
        int left = 0;
        int min = Integer.MAX_VALUE;
        int[] indices = new int[] {-1, -1};
        for (int right = 0; right < ss.length; right++) {
            counts[ss[right]]--;
            if (counts[ss[right]] == 0) {
                count--;
            }
            while (count == 0 && left <= right) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    indices[0] = left;
                    indices[1] = right;
                }
                counts[ss[left]]++;
                if (counts[ss[left]] == 1) {
                    count++;
                }
                left++;
            }
        }
        
        return indices[0] == -1 ? "" : s.substring(indices[0], indices[1] + 1);
    }
}