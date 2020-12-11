class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;    
        List<Integer>[] bucket = new ArrayList[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        map.forEach((num, count) -> {
            if (bucket[count] == null) {
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(num);
        });
        
        int[] res = new int[k];
        int idx = 0;
        for (int i = n; i >= 0; i--) {
            if (bucket[i] == null) {
                continue;
            }
            if (idx < k) {
                for (int num : bucket[i]) {
                    res[idx++] = num;
                }
            }
        }

        return res;
    }
}