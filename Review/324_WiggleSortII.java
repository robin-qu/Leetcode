class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int n = nums.length;
        int mid = quickSelect(0, n - 1, nums, n / 2);
        partition(nums, mid);
    }

    private void partition(int[] nums, int mid) {
        int n = nums.length;
        int left = 0;
        int right = nums.length - 1;
        int curr = 0;
        while (curr <= right) {
            if (nums[idxMap(curr, n)] > mid) {
                swap(idxMap(left, n), idxMap(curr, n), nums);
                left++;
                curr++;
            } else if (nums[idxMap(curr, n)] < mid) {
                swap(idxMap(curr, n), idxMap(right, n), nums);
                right--;
            } else {
                curr++;
            }
        }
    }

    private int idxMap(int idx, int n) {
        return (1 + 2 * idx) % (n | 1);
    }

    private int quickSelect(int start, int end, int[] nums, int k) {
        if (start == end) {
            return nums[start];
        }

        int pivot = nums[start + (end - start) / 2];
        int left = start;
        int right = end;
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        if (k <= right) {
            return quickSelect(start, right, nums, k);
        } else if (k >= left) {
            return quickSelect(left, end, nums, k);
        } else {
            return nums[k];
        }
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}