class Solution {
    public int trailingZeroes(int n) {
        if (n <= 0) {
            return 0;
        }

        int res = 0;
        long five = 5;
        while (five <= n) {
            res += n / five;
            five *= 5;
        }

        return res;
    }
}