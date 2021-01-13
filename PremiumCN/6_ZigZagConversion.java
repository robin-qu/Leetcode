class Solution {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows == 1) {
            return s;
        }

        int n = s.length();

        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int idx = i % (numRows + numRows - 2);
            if (idx < numRows) {
                sbs[idx].append(c);
            } else {
                sbs[numRows + numRows - 2 - idx].append(c);
            }
        }

        StringBuilder resSb = new StringBuilder();
        for (StringBuilder sb : sbs) {
            resSb.append(sb.toString());
        }
        return resSb.toString();
    }
}