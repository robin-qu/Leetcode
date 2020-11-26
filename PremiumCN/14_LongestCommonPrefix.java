class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int n = strs.length;
        int res = strs[0].length();
        char[] cc = strs[0].toCharArray();
        for (int i = 1; i < n; i++) {
            res = Math.min(res, strs[i].length());
            for (int j = 0; j < Math.min(res, strs[i].length()); j++) {
                if (cc[j] != strs[i].charAt(j)) {
                    res = Math.min(res, j);
                    break;
                }
            }
        }

        return strs[0].substring(0, res);
    }
}