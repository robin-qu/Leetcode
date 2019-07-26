// Two Pointers O(m + n)time O()
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null) {
            return "";
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        
        int[] tMap = new int[256]; // stores the number of each character in t
        int count = 0;  // the number of unique character in t
        for (char c : t.toCharArray()) {
            tMap[c]++;
            if (tMap[c] == 1) {
                count++;
            }
        }
        
        int min = Integer.MAX_VALUE;
        int[] pos = new int[2];
        int left = 0;
        for (int right = 0; right < n; right++) {
            tMap[ss[right]]--;
            if (tMap[ss[right]] == 0) {
                count--;  //have collect all the occurrence of the character
            }
            
            while (left <= right && count == 0) {// have collected all the characters in t
                // update the min value
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    pos[0] = left;
                    pos[1] = right;
                }
                
                // shift the left boundary to right by 1
                tMap[ss[left]]++;
                if (tMap[ss[left]] == 1) {
                    count++;
                }
                left++;
            }
        }
        
        return min == Integer.MAX_VALUE ? "" : s.substring(pos[0], pos[1] + 1);
    }
}