class Solution {
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        
        List<String> res = new ArrayList<>();
        
        dfs(0, s, "", 0, res);
        
        return res;
    }
    
    private void dfs(int idx, String s, String sol, int count, List<String> res) {
        if (count > 4) {  // optimization
            return;
        }
        
        if (idx == s.length()) {
            if (count == 4) {
                res.add(sol.substring(0, sol.length() - 1));
            }
            
            return;
        }
        
        for (int i = 1; i <= 3; i++) {
            if (idx + i > s.length()) {
                break;
            }
            
            String sub = s.substring(idx, idx + i);
            
            if ((sub.startsWith("0") && sub.length() > 1) || (i == 3 && Integer.parseInt(sub) > 255)) {
                continue;
            }
            
            dfs(idx + i, s, sol + sub + '.', count + 1, res);
        }
    } 
}