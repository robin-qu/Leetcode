class Solution {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        
        List<String> res = new ArrayList<>();
        dfs(0, 0, n, new StringBuilder(), res);
        return res;
    }
                                        
    private void dfs(int left, int right, int n, StringBuilder sb, List<String> res) {
        if (left == n && right == n) {
            res.add(sb.toString());
            return;
        }
        
        if (left < n) {
            sb.append('(');
            dfs(left + 1, right, n, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if (right < left) {
            sb.append(')');
            dfs(left, right + 1, n, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}