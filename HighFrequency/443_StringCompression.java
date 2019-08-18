// Two Pointers O(n)time O(n)space
class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        
        int n = chars.length;
        
        int idx = 0;
        int leftIdx = 0;
        
        while (idx < n) {
            char c = chars[idx];
            int count = 0;
            
            while (idx < n && chars[idx] == c) {
                count++;
                idx++;
            }
            
            chars[leftIdx++] = c;
            
            if (count == 1) {
                continue;
            }
            
            char[] num = Integer.toString(count).toCharArray();
            for (int i = 0; i < num.length; i++) {
                chars[leftIdx++] = num[i];
            }
        }
        
        return leftIdx;
    }
}