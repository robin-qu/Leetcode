class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int n = nums.length;

        int[] triplet = new int[3];
        Arrays.fill(triplet, Integer.MAX_VALUE);
        for (int num : nums) {
            if (num <= triplet[0]) {
                triplet[0] = num;
            } else if (num <= triplet[1]) {
                triplet[1] = num;
            } else if (num <= triplet[2]) {
                triplet[2] = num;
            }
        }

        return triplet[2] != Integer.MAX_VALUE;
    }
}