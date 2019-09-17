// O(logtarget)time O(logtarget)space
class Solution {
    public int leastOpsExpressTarget(int x, int target) {
        if (x > target) {
            return Math.min(target * 2 - 1, (x - target) * 2);
        }
        
        if (x == target) {
            return 0;
        }
        
        long sum = x;
        int times = 0;
        while (sum < target) {
            times++;
            sum *= x;
        }
        
        if (sum == target) {
            return times;
        }
        
        int plus = Integer.MAX_VALUE;
        int minus = Integer.MAX_VALUE;
        
        if (sum - target < target) {
            minus = leastOpsExpressTarget(x, (int) (sum - target)) + times;
        }
        
        plus = leastOpsExpressTarget(x, (int) (target - (sum / x))) + times - 1;
        
        return Math.min(plus, minus) + 1;
    }
}