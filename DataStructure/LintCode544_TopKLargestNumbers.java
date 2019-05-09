public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0 || k > nums.length) {
            return new int[]{};
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        
        for (int num : nums) {
            if (pq.size() == k) {
                pq.poll();
            }
            pq.add(num);
        }
        
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll();
        }
        
        return res;
    }
}