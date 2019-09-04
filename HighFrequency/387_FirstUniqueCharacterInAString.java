// O(n)time O(1)space
class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        
        int n = s.length();
        int[] counts = new int[26];
        
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        for (int i = 0; i < n; i++) {
            if (counts[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        
        return -1;
    }
}