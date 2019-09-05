// Greedy + two pointers O(n)time O(n)space
class Solution {
    public List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        
        int n = s.length();
        Map<Character, Integer> lasts = new HashMap<>();  // the last index of each character in the string
        for (int i = 0; i < n; i++) {
            lasts.put(s.charAt(i), i);
        }
        
        List<Integer> res = new ArrayList<>();
        int start = 0;
        int last = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            last = Math.max(last, lasts.get(c));
            
            if (last == i) {  // reach the last appearance of all the characters in current substring
                res.add(last - start + 1);
                start = last + 1;
            }
        }
        
        return res;
    }
}