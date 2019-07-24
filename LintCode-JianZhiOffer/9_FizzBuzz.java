public class Solution {
    /**
     * @param n: An integer
     * @return: A list of strings.
     */
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                res.add("fizz buzz");
            } else if (i % 3 == 0) {
                res.add("fizz");
            } else if (i % 5 == 0) {
                res.add("buzz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        
        return res;
    }
}