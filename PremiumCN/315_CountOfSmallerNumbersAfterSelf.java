class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        int n = nums.length;
        
        int[][] counts = new int[n][2];
        for (int i = 0; i < n; i++) {
            counts[i][0] = i;
            counts[i][1] = 0;
        }
        
        mergeSort(0, n - 1, counts, nums);
        
        List<Integer> res = Arrays.asList(nums);
        for (int[] count : counts) {
            res.set(count[0], count[1]);
        }
        
        return res;
    }
                                        
    private void mergeSort(int start, int end, int[][] counts, int[] nums) {
        if (start >= end) {
            return;
        }
        
        int mid = start + (end - start) / 2;
        mergeSort(start, mid, counts, nums);
        mergeSort(mid + 1, end, counts, nums);
    
        int[] temp = new int[end - start + 1];
        for (int i = start, i <= end; i++) {
            temp[i] = nums[i];
        }
        int left = start;
        int right = mid + 1;
        
    }
}