// class Solution {
//     public void wiggleSort(int[] nums) {
//         if (nums == null || nums.length <= 1) {
//             return;
//         }

//         int n = nums.length;
//         int mid = quickSelect(0, n - 1, nums, n / 2);
//         partition(nums, mid);
//         int[] temp = new int[n];
//         if (n % 2 == 0) {
//             reverse(0, n / 2 - 1, nums);
//             reverse(n / 2, n - 1, nums);
//             int j = 0;
//             for (int i = 0; i <= n / 2 - 1; i++) {
//                 temp[j] = nums[i];
//                 j += 2;
//             }
//             j = 1;
//             for (int i = n / 2; i < n; i++) {
//                 temp[j] = nums[i];
//                 j += 2;
//             }
//         } else {
//             reverse(0, n / 2, nums);
//             reverse(n / 2 + 1, n - 1, nums);
//             int j = 0;
//             for (int i = 0; i <= n / 2; i++) {
//                 temp[j] = nums[i];
//                 j += 2;
//             }
//             j = 1;
//             for (int i = n / 2 + 1; i < n; i++) {
//                 temp[j] = nums[i];
//                 j += 2;
//             }
//         }
        
//         for (int i = 0; i < n; i++) {
//             nums[i] = temp[i];
//         }
//     }

//     private void reverse(int i, int j, int[] nums) {
//         if (i < 0 || j >= nums.length) {
//             return;
//         }
//         int left = i;
//         int right = j;
//         while (left < right) {
//             int temp = nums[left];
//             nums[left] = nums[right];
//             nums[right] = temp;
//             left++;
//             right--;
//         }
//     }

//     private void partition(int[] nums, int num) {
//         int left = 0;
//         int right = nums.length - 1;
//         int curr = 0;
//         while (curr <= right) {
//             if (nums[curr] < num) {
//                 swap(left++, curr, nums);
//                 curr++;
//             } else if (nums[curr] > num) {
//                 swap(right--, curr, nums);
//             } else {
//                 curr++;
//             }
//         }
//     }

//     private int quickSelect(int start, int end, int[] nums, int k) {
//         if (start == end) {
//             return nums[start];
//         }
//         int pivot = nums[start + (end - start) / 2];
//         int left = start;
//         int right = end;
//         while (left <= right) {
//             while (left <= right && nums[left] < pivot) {
//                 left++;
//             }
//             while (left <= right && nums[right] > pivot) {
//                 right--;
//             }
//             if (left <= right) {
//                 swap(left++, right--, nums);
//             }
//         }

//         if (k <= right) {
//             return quickSelect(start, right, nums, k);
//         } else if (k >= left) {
//             return quickSelect(left, end, nums, k);
//         } else {
//             return nums[k];
//         }
//     }

//     private void swap(int i, int j, int[] nums) {
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }
// }




class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        int mid = quickSelect(0, n - 1, nums, n / 2);
        partition(nums, mid);
    }

    private void partition(int[] nums, int num) {
        int left = 0;
        int right = nums.length - 1;
        int curr = 0;
        int n = nums.length;
        while (curr <= right) {
            if (nums[indexMap(curr, n)] > num) {
                swap(indexMap(left, n), indexMap(curr, n), nums);
                left++;
                curr++;
            } else if (nums[indexMap(curr, n)] < num) {
                swap(indexMap(right, n), indexMap(curr, n), nums);
                right--;
            } else {
                curr++;
            }
        }
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
                swap(left++, right--, nums);
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

    private int indexMap(int i, int n) {
        return (1 + i * 2) % (n | 1);
    }
}