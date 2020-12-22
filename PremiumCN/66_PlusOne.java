class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int[] res = new int[n];
        int carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = (digits[i] + carry) % 10;
            carry = (digits[i] + carry) / 10;
        }

        if (carry != 0) {
            int[] newRes = new int[n + 1];
            newRes[0] = carry;
            for (int i = 0; i < n; i++) {
                newRes[i + 1] = res[i];
            }
            return newRes;
        }

        return res;
    }
}