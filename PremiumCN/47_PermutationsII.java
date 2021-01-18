class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int idx, int[] nums, List<Integer> list, List<List<Integer>> res) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        Set<Integer> visited = new HashSet<>();
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && visited.contains(nums[i])) {
                continue;
            }
            visited.add(nums[i]);
            swap(i, idx, nums);
            list.add(nums[idx]);
            dfs(idx + 1, nums, list, res);
            list.remove(list.size() - 1);
            swap(i, idx, nums);
        }
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}