// Two pointers O(n)
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        int n = height.length;
        
        int left = 0;
        int leftMax = height[left];  // maintain the heightest left boundary
        int right = n - 1;
        int rightMax = height[right];  // maintain the heightest right boundary
        int res = 0;
        
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            
            if (height[left] <= height[right]) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        
        return res;
    }
}