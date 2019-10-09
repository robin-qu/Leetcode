// dfs O(4^n)time O(n)space
class Solution {
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) {
            return new ArrayList<>();
        }
        
        List<String> res = new ArrayList<>();
        
        dfs(0, num, new StringBuilder(), target, 0, 0, res);
        
        return res;
    }
    
    private void dfs(int idx, String num, StringBuilder sb, int target, long curr, long mult, List<String> res) {
        // mult: the number to be multiplied in the next recursion
        if (idx == num.length()) {
            if (target == curr) {
                res.add(sb.toString());
            }
            return;
        }
        
        for (int i = idx; i < num.length(); i++) {
            if (i != idx && num.charAt(idx) == '0') {
                break;   // skip leading zero
            }
            
            long val = Long.parseLong(num.substring(idx, i + 1));
            int len = sb.length();
            
            if (idx == 0) {  // the first number
                sb.append(val);
                dfs(i + 1, num, sb, target, curr + val, val, res);
                sb.setLength(len);
            } else {
                sb.append('+');
                sb.append(val);
                dfs(i + 1, num, sb, target, curr + val, val, res);
                sb.setLength(len);

                sb.append('-');
                sb.append(val);
                dfs(i + 1, num, sb, target, curr - val, -val, res);
                sb.setLength(len);

                sb.append('*');
                sb.append(val);
                dfs(i + 1, num, sb, target, curr - mult + val * mult, val * mult, res);
                sb.setLength(len);
            }
        }
    }
}