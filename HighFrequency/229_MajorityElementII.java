// O(n)time O(1)space
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        int n = nums.length;
        int first = 0;
        int count1 = 0;
        int second = 0;
        int count2 = 0;
        
        for (int num : nums) {
            if (first == num) {
                count1++;
            } else if (second == num) {
                count2++;
            } else if (count1 == 0) {
                count1++;
                first = num;
            } else if (count2 == 0) {
                count2++;
                second = num;
            } else {
                count1--;
                count2--;
            }
        }
        
        // post check
        int countFirst = 0;
        for (int num : nums) {
            if (num == first) {
                countFirst++;
            }
        }
        if (count1 > 0 && countFirst > n / 3) {
            res.add(first);
        }
        
        int countSecond = 0;
        for (int num : nums) {
            if (num == second) {
                countSecond++;
            }
        }
        if (count2 > 0 && countSecond > n / 3) {
            res.add(second);
        }        
        
        return res;
    }
}