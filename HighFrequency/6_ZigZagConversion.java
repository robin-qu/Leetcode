// O(n)time O(n)space
class Solution {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 1) {
            return s;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        List<Character>[] container = new List[numRows];
        for (int i = 0; i < numRows; i++) {
            container[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            int idx = i % (2 * numRows - 2);
            if (idx >= numRows) {
                idx = 2 * numRows - idx - 2;
            }
            
            container[idx].add(ss[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        for (List<Character> list : container) {
            for (char c : list) {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}