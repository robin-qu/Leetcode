public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return new int[]{};
        }
        
        int[] res = new int[k];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll();
        }
        
        return res;
    }
}