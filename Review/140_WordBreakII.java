class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) {
            return new ArrayList<>();
        }

        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, 0, new HashSet<>(wordDict), new ArrayList<>(), memo);
    }

    private List<String> dfs(String s, int idx, Set<String> words, List<String> list, Map<Integer, List<String>> memo) {
        if (memo.containsKey(idx)) {
            return memo.get(idx);
        }

        List<String> res = new ArrayList<>();

        if (idx == s.length()) {
            res.add("");
            return res;
        }

        for (int i = idx + 1; i <= s.length(); i++) {
            String curr = s.substring(idx, i);
            if (words.contains(curr)) {
                List<String> successors = dfs(s, i, words, list, memo);
                for (String suc : successors) {
                    if (suc.equals("")) {
                        res.add(curr);
                    } else {
                        res.add(curr + " " + suc);
                    }
                }
            }
        }

        memo.put(idx, res);
        return res;
    }
}