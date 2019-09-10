// Sliding window O(n)time O(1)space
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null || s.length() < p.length()) {
            return new ArrayList<>();
        }
        
        int m = s.length();
        int n = p.length();
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        List<Integer> res = new ArrayList<>();
        
        int[] counts = new int[26];
        int count = 0;
        for (char c : pp) {
            counts[c - 'a']++;
            if (counts[c - 'a'] == 1) {
                count++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            counts[ss[i] - 'a']--;
            if (counts[ss[i] - 'a'] == 0) {
                count--;
            }
        }
        
        if (count == 0) {
            res.add(0);
        }
        
        for (int i = 1; i < m - n + 1; i++) {
            counts[ss[i - 1] - 'a']++;
            if (counts[ss[i - 1] - 'a'] == 1) {
                count++;
            }
            counts[ss[i + n - 1] - 'a']--;
            if (counts[ss[i + n - 1] - 'a'] == 0) {
                count--;
            }
            
            if (count == 0) {
                res.add(i);
            }
        }
        
        return res;
    }
}