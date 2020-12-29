class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new double[0];
        }

        int n = nums.length;

        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            left.offer(nums[i]);
        }
        for (int i = 0; i < k - k / 2; i++) {
            right.offer(left.poll());
        }

        double[] res = new double[n - k + 1];
        res[0] = k % 2 == 0 ? ((double) left.peek() + (double) right.peek()) / 2 : (double) right.peek();

        for (int i = k; i < n; i++) {
            if (left.contains(nums[i - k])) {
                left.remove(nums[i - k]);
                left.offer(nums[i]);
            } else {
                right.remove(nums[i - k]);
                right.offer(nums[i]);
            }
            left.offer(right.poll());
            right.offer(left.poll());
            res[i - k + 1] = k % 2 == 0 ? ((double) left.peek() + (double) right.peek()) / 2 : (double) right.peek();
        }

        return res;
    }
}