// stack O(n)time O(n)space
class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return null;
        }
        
        Stack<String> stack = new Stack<>();
        String[] dirs = path.split("/");
        
        for (String dir : dirs) {
            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (dir.equals("") || dir.equals(".")) {
                continue;
            } else {
                stack.push(dir);
            }
        }        
        
        Stack<String> temp = new Stack<>();
        
        while(!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        while (!temp.isEmpty()) {
            sb.append('/');
            sb.append(temp.pop());
        }
        
        return sb.length() == 0 ? "/" : sb.toString();
    }
}