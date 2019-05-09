public class Solution {
    /**
     * @param nums: A list of integers
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        
        int[] res = new int[nums.length];
        
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(new Comparator <Integer>() {
                public int compare(Integer n1, Integer n2) {
                    return n2 - n1;
                }
            });
            
            
        for (int i = 0; i < nums.length; i++) {
            maxPq.add(nums[i]);
            
            if (i % 2 == 0) {
                if (minPq.isEmpty()) {
                    res[i] = maxPq.peek();
                } else if (maxPq.peek() > minPq.peek()) { // special case
                    int temp1 = maxPq.poll();
                    int temp2 = minPq.poll();
                    maxPq.add(temp2);
                    minPq.add(temp1);
                }
            } else {
                minPq.add(maxPq.poll());
            }
            
            res[i] = maxPq.peek();
        }    
        
        return res;
    }
}