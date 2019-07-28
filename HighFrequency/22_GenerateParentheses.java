// dfs O(2^n)time O(n)space
class Solution {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        
        List<String> res = new ArrayList<>();
        dfs(0, 2 * n, n, n, new StringBuilder(), res);
        return res;
    }
    
    private void dfs(int idx, int n, int left, int right, StringBuilder sb, List<String> res) {
        if (idx == n) {
            res.add(sb.toString());
            return;
        }
        
        if (left > 0) {
            sb.append('(');
            dfs(idx + 1, n, left - 1, right, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if (right > left) {
            sb.append(')');
            dfs(idx + 1, n, left, right - 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}