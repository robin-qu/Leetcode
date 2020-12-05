class Solution {
    public String countAndSay(int n) {
        if (n <= 0) {
            return "";
        }

        if (n == 1) {
            return "1";
        }

        String prev = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char c = prev.charAt(0);
        for (int i = 1; i < prev.length(); i++) {
            if (prev.charAt(i) == c) {
                count++; 
            } else {
                sb.append(String.valueOf(count)).append(c);
                c = prev.charAt(i);
                count = 1;
            }
        }
        sb.append(String.valueOf(count)).append(c);
        return sb.toString();
    }
}