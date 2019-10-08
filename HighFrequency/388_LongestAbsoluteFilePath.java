// O(n)time O(n)space
class Solution {
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0 || input.indexOf('.') == -1) {
            return 0;
        }
        
        int n = input.length();
        String[] dirs = input.split("\n");
        Stack<String> stack = new Stack<>();
        
        int res = 0;
        int len = 0;
        for (String s : dirs) {
            int currLevel = s.lastIndexOf("\t") + 1;
            s = s.replace("\t", "");
            
            while (currLevel < stack.size()) {
                len -= stack.pop().length();
            }
            
            stack.push(s);
            len += s.length();
            
            if (s.indexOf('.') != -1) {  // is a file
                res = Math.max(res, len + stack.size() - 1);
            }
        }
        
        return res;
    }
}