// Two pointers + hashmap O(n)time O(n)space
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(ss[i], 0);
        }
        
        int max = 0;
        int used = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            map.put(ss[right], map.get(ss[right]) + 1);
            if (map.get(ss[right]) == 1) {  // new character
                used++;
                while (used > k) {
                    map.put(ss[left], map.get(ss[left]) - 1);
                    if (map.get(ss[left]) == 0) {
                        used--;
                    }
                    left++;
                }
            }
            max = Math.max(max, right - left + 1);
        }
        
        return max;
    }
}