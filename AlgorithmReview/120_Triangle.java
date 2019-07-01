// bottom up
class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 ||
            triangle.get(0) == null || triangle.get(0).size() == 0) {
            return 0;
        }
        
        int level = triangle.size();
        int[] dp = new int[triangle.get(triangle.size() - 1).size() + 1];
        for (int i = level - 1; i >= 0; i--) {
            int n = triangle.get(i).size();
            for (int j = 0; j < n; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        
        return dp[0];
    }
}