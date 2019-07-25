// // Initial version
// class Solution {
//     public List<String> removeInvalidParentheses(String s) {
//         List<String> res = new ArrayList<>();
//         if (s == null) {
//             return res;
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
        
//         // count the number of misplaced left parentheses and right parentheses
//         int left = 0;
//         int right = 0;
//         for (int i = 0; i < n; i++) {
//             char c = ss[i];
            
//             if (c == '(') {
//                 left++;
//             } else if (c == ')') {
//                 if (left > 0) {
//                     left--;
//                 } else {
//                     right++;
//                 }
//             }
//         }
        
//         Set<String> set = new HashSet<>();
        
//         dfs(ss, 0, 0, left, right, "", res, set);
        
//         return res;
//     }
    
//     private void dfs(char[] ss, int idx, int leftCount, int left, int right, String str, List<String> res, Set<String> set) {
//         if (idx >= ss.length) {
//             if (left == 0 && right == 0 && leftCount == 0 && !set.contains(str)) {
//                 res.add(str);
//                 set.add(str);
//             }
//             return;
//         }
        
//         if (ss[idx] != '(' && ss[idx] != ')') {
//             dfs(ss, idx + 1, leftCount, left, right, str + ss[idx], res, set);
//             return;
//         }
        
//         if (ss[idx] == '(') {
//             // include '('
//             dfs(ss, idx + 1, leftCount + 1, left, right, str + ss[idx], res, set);
//             // exclude '('
//             if (left > 0) {
//                 dfs(ss, idx + 1, leftCount, left - 1, right, str, res, set);
//             }
//             return;
//         }
        
//         if (ss[idx] == ')') {
//             // include ')'
//             if (leftCount > 0) {
//                 dfs(ss, idx + 1, leftCount - 1, left, right, str + ss[idx], res, set);
//             }
//             // exclude ')'
//             if (right > 0) {
//                 dfs(ss, idx + 1, leftCount, left, right - 1, str, res, set);
//             }
//             return;
//         }
//     }
// }


// // Optimized DFS O(2^n)time O(n)space
// class Solution {
//     public List<String> removeInvalidParentheses(String s) {
//         List<String> res = new ArrayList<>();
//         if (s == null) {
//             return res;
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
        
//         // count the number of misplaced left parentheses and right parentheses
//         int left = 0;
//         int right = 0;
//         for (int i = 0; i < n; i++) {
//             char c = ss[i];
            
//             if (c == '(') {
//                 left++;
//             } else if (c == ')') {
//                 if (left > 0) {
//                     left--;
//                 } else {
//                     right++;
//                 }
//             }
//         }
        
//         dfs(ss, 0, 0, left, right, new StringBuilder(), res);
        
//         return res;
//     }
    
//     private void dfs(char[] ss, int idx, int leftCount, int left, int right, StringBuilder sb, List<String> res) {
//         if (idx >= ss.length) {
//             if (left == 0 && right == 0 && leftCount == 0) {  // all of the misplaced parentheses have been removed as well as the remaining parentheses are valid
//                 res.add(sb.toString());
//             }
//             return;
//         }
        
//         if (ss[idx] != '(' && ss[idx] != ')') {  // add letter into the result and keep recursion
//             sb.append(ss[idx]);
//             dfs(ss, idx + 1, leftCount, left, right, sb, res);
//             sb.deleteCharAt(sb.length() - 1);
//             return;
//         }
        
//         if (ss[idx] == '(') {
//             // include '('
//             int i = 1;
//             while (idx + i < ss.length && ss[idx + i] == ss[idx]) {
//                 i++;  // remove duplicates
//             }
//             sb.append(ss, idx, i);
//             dfs(ss, idx + i, leftCount + i, left, right, sb, res);
//             sb.delete(sb.length() - i, sb.length());
            
//             // exclude '('
//             if (left > 0) {  // there are still misplaced left parentheses we can remove
//                 dfs(ss, idx + 1, leftCount, left - 1, right, sb, res);
//             }
//             return;
//         }
        
//         if (ss[idx] == ')') {
//             // include ')'
//             int i = 1;
//             while (idx + i < ss.length && ss[idx + i] == ss[idx]) {
//                 i++;  // remove duplicates
//             }
//             sb.append(ss, idx, i);
//             if (leftCount >= i) {
//                 dfs(ss, idx + i, leftCount - i, left, right, sb, res);
//             }
//             sb.delete(sb.length() - i, sb.length());
            
//             // exclude ')'
//             if (right > 0) {  // there are still misplaced right parentheses that we can remove
//                 dfs(ss, idx + 1, leftCount, left, right - 1, sb, res);
//             }
//             return;
//         }
//     }
// }


// BFS
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        if (isValid(s)) {
            res.add(s);
            return res;
        }
        
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        
        while (!queue.isEmpty()) {
            String str = queue.poll();
            
            if (isValid(str)) {
                res.add(str);
                visited.add(str);
                found = true;
            }
            
            if (found) {
                continue;
            }
            
            int n = str.length();
            for (int j = 0; j < n; j++) {
                if (str.charAt(j) != '(' && str.charAt(j) != ')') {
                    continue;
                }

                String substr = str.substring(0, j) + str.substring(j + 1, n);
                if (!visited.contains(substr)) {
                    queue.offer(substr);
                    visited.add(substr);
                }
            }
        }
        
        return res;
    }
    
    private boolean isValid(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        
        return left == 0 && right == 0;
    }
}