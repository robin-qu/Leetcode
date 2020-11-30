// class Solution {
//     public int maxProduct(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }

//         int n = nums.length;

//         int[] maxs = new int[n + 1];
//         int[] mins = new int[n + 1];
//         maxs[0] = 1;
//         mins[0] = 1;
//         int res = nums[0];
//         for (int i = 0; i < n; i++) {
//             if (nums[i] > 0) {
//                 maxs[i + 1] = Math.max(maxs[i] * nums[i], nums[i]);
//                 mins[i + 1] = Math.min(mins[i] * nums[i], nums[i]);
//             } else {
//                 maxs[i + 1] = Math.max(mins[i] * nums[i], nums[i]);
//                 mins[i + 1] = Math.min(maxs[i] * nums[i], nums[i]);
//             }
//             res = Math.max(res, maxs[i + 1]);
//         }

//         return res;
//     }
// }


class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int max = 1;
        int min = 1;
        int maxPrev = 1;
        int minPrev = 1;
        int res = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                max = Math.max(maxPrev * nums[i], nums[i]);
                min = Math.min(minPrev * nums[i], nums[i]);
            } else {
                max = Math.max(minPrev * nums[i], nums[i]);
                min = Math.min(maxPrev * nums[i], nums[i]);
            }
            maxPrev = max;
            minPrev = min;
            res = Math.max(res, max);
        }

        return res;
    }
}