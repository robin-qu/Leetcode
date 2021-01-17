class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        }

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();
        char[] ss1 = num1.toCharArray();
        char[] ss2 = num2.toCharArray();
        int[] res = new int[m + n];

        for (int i = n - 1; i >= 0; i--) {
            int a = ss2[i] - '0';
            int p = m + i;
            int carry = 0;
            for (int j = m - 1; j >= 0; j--) {
                int b = ss1[j] - '0';
                int val = res[p] + a * b + carry;
                res[p] = val % 10;
                carry = val / 10;
                p--;
            }
            res[p] = carry;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            if (sb.length() == 0 && num == 0) {
                continue;
            }
            sb.append(num);
        }

        return sb.toString();
    }
}