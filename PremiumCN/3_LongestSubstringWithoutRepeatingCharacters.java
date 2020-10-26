class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] ss = s.toCharArray();
        
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (map.containsKey(ss[right])) {
                left = Math.max(map.get(ss[right]) + 1, left);
            }
            map.put(ss[right], right);
            res = Math.max(res, right - left + 1);
        }
        
        return res;
    }
}