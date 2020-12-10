class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int n = nums.length;
        int res1 = 0;
        int count1 = 0;
        int res2 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (res1 == num && count1 > 0) {
                count1++;
            } else if (res2 == num && count2 > 0) {
                count2++;
            } else if (count1 == 0) {
                res1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                res2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        List<Integer> res = new ArrayList<>();
        if (count1 > 0 && getCount(res1, nums) > n / 3) {
            res.add(res1);
        }
        if (count2 > 0 && getCount(res2, nums) > n / 3) {
            res.add(res2);
        }

        return res;
    }

    private int getCount(int num, int[] nums) {
        int res = 0;
        for (int n : nums) {
            if (n == num) {
                res++;
            }
        }
        return res;
    }
}