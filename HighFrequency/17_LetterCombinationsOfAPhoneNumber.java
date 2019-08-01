// DFS O(3^n * 4^m)time O(3^n * 4^m)space
class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        
        Map<Character, char[]> map = getMap();
        
        List<String> res = new ArrayList<>();
        dfs(0, digits, new StringBuilder(), res, map);
        
        return res;
    }
    
    private void dfs(int idx, String digits, StringBuilder sb, List<String> res, Map<Character, char[]> map) {
        if (idx == digits.length()) {
            res.add(sb.toString());
            return;
        }
        
        for (char c : map.get(digits.charAt(idx))) {
            sb.append(c);
            dfs(idx + 1, digits, sb, res, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    private Map<Character, char[]> getMap() {
        Map<Character, char[]> map = new HashMap<>();
        
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
        
        return map;
    }
}