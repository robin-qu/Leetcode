// // dfs O(2^n)time
// class Solution {
//     public List<String> letterCasePermutation(String S) {
//         if (S == null || S.length() == 0) {
//             return new ArrayList<>();
//         }
        
//         char[] s = S.toCharArray();
//         List<String> res = new ArrayList<>();
        
//         dfs(0, s, new StringBuilder(), res);
        
//         return res;
//     }
    
//     private void dfs(int idx, char[] s, StringBuilder sb, List<String> res) {
//         if (idx == s.length) {
//             res.add(sb.toString());
//             return;
//         }
        
//         if (Character.isDigit(s[idx])) {
//             sb.append(s[idx]);
//             dfs(idx + 1, s, sb, res);
//             sb.deleteCharAt(sb.length() - 1);
//         } else {
//             sb.append(Character.toLowerCase(s[idx]));
//             dfs(idx + 1, s, sb, res);
//             sb.deleteCharAt(sb.length() - 1);
            
//             sb.append(Character.toUpperCase(s[idx]));
//             dfs(idx + 1, s, sb, res);
//             sb.deleteCharAt(sb.length() - 1);
//         }
//     }
// }


// dfs O(2^n)time inplace
class Solution {
    public List<String> letterCasePermutation(String S) {
        if (S == null || S.length() == 0) {
            return new ArrayList<>();
        }
        
        char[] s = S.toCharArray();
        List<String> res = new ArrayList<>();
        
        dfs(0, s, res);
        
        return res;
    }
    
    private void dfs(int idx, char[] s, List<String> res) {
        if (idx == s.length) {
            res.add(String.valueOf(s));
            return;
        }
        
        if (Character.isDigit(s[idx])) {
            dfs(idx + 1, s, res);
        } else {
            s[idx] = Character.toLowerCase(s[idx]);
            dfs(idx + 1, s, res);
            
            s[idx] = Character.toUpperCase(s[idx]);
            dfs(idx + 1, s, res);
        }
    }
}