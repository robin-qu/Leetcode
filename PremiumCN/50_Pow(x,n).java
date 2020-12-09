class Solution {
    public double myPow(double x, int n) {
        if (n == Integer.MIN_VALUE) {
            return 1 / helper(x, Integer.MAX_VALUE, new HashMap<>()) / x;
        }
        if (n < 0) {
            return 1 / helper(x, -n, new HashMap<>());
        }
        return helper(x, n, new HashMap<>());
    }

    private double helper(double x, int n, Map<Integer, Double> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n == 0) {
            return 1.0;
        }

        if (n == 1) {
            return x;
        }

        double res = helper(x, n / 2, memo) * helper(x, n - n / 2, memo);
        memo.put(n, res);
        return res;
    }
}