class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int n = nums.length;
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        int[] res = new int[n];
        mergeSort(indices, nums, 0, n - 1, res);

        List<Integer> list = new ArrayList<>();
        for (int r : res) {
            list.add(r);
        }
        return list;
    }

    private void mergeSort(int[] indices, int[] nums, int start, int end, int[] res) {
        if (start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(indices, nums, start, mid, res);
        mergeSort(indices, nums, mid + 1, end, res);

        int[] temp = new int[end - start + 1];
        int k = 0;
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if (nums[indices[i]] <= nums[indices[j]]) {
                res[indices[i]] += start + k - i;
                temp[k++] = indices[i++];
            } else {
                temp[k++] = indices[j++];
            }
        }

        while (i <= mid) {
            res[indices[i]] += start + k - i;
            temp[k++] = indices[i++];
        }

        while (j <= end) {
            temp[k++] = indices[j++];
        }

        for (int p = start; p <= end; p++) {
            indices[p] = temp[p - start];
        }
    }
}