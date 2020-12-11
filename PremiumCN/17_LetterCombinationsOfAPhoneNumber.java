class Solution {
    Map<Character, List<Character>> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        int n = digits.length();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        List<String> res = new ArrayList<>();
        dfs(0, digits, new StringBuilder(), res);
        return res;
    }

    private void dfs(int idx, String s, StringBuilder sb, List<String> res) {
        if (idx == s.length()) {
            res.add(sb.toString());
            return;
        }

        for (char c : map.get(s.charAt(idx))) {
            sb.append(c);
            dfs(idx + 1, s, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}