class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        if (nums == null || lower > upper) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        int left = lower;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != left) {
                int right = nums[i] - 1;
                if (left == right) {
                    res.add(String.valueOf(left));
                } else {
                    res.add(left + "->" + right);
                }
            }
            left = nums[i] + 1;
        }
        if (left == upper) {
            res.add(String.valueOf(left));
        } else if (left < upper) {
            res.add(left + "->" + upper);
        }

        return res;
    }
}