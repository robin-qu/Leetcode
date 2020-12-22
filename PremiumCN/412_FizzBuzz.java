class Solution {
    public List<String> fizzBuzz(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            if (i % 3 == 0 && i % 5 == 0) {
                s = "FizzBuzz";
            } else if (i % 3 == 0) {
                s = "Fizz";
            } else if (i % 5 == 0) {
                s = "Buzz";
            }
            res.add(s);
        }

        return res;
    }
} 