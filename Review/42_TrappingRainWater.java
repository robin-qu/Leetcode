// class Solution {
//     public int trap(int[] height) {
//         if (height == null || height.length <= 2) {
//             return 0;
//         }
        
//         int left = 0;
//         int right = height.length - 1;
//         int leftBound = height[left];
//         int rightBound = height[right];
//         int res = 0;
//         while (left <= right) {
//             if (height[left] <= rightBound) {
//                 leftBound = Math.max(leftBound, height[left]);
//                 res += leftBound - height[left];
//                 left++;
//             } else {
//                 rightBound = Math.max(rightBound, height[right]);
//                 res += rightBound - height[right];
//                 right--;
//             }
//         }
        
//         return res;
//     }
// }



class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;

        int left = 0;
        int right = n - 1;
        int leftBound = height[left];
        int rightBound = height[right];
        int res = 0;
        while (left <= right) {
            if (leftBound <= rightBound) {
                res += Math.max(leftBound - height[left], 0);
                leftBound = Math.max(leftBound, height[left]);
                left++;
            } else {
                res += Math.max(rightBound - height[right], 0);
                rightBound = Math.max(rightBound, height[right]);
                right--;
            }
        }

        return res;
    }
}