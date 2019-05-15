class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }

        int len = stones.length;
        Map<Integer, Set<Integer>> steps = new HashMap<>();
        for (int i = 0; i < len; i++) {
            steps.put(stones[i], new HashSet<Integer>());
        }
        steps.get(0).add(0);

        for (int i = 0; i < len - 1; i++) {
            for (int j : steps.get(stones[i])) {
                for (int step = j - 1; step <= j + 1; step++) {
                    if (step > 0 && steps.containsKey(stones[i] + step)) {
                        steps.get(stones[i] + step).add(step);
                    }
                }
            }
        }

        return !steps.get(stones[len - 1]).isEmpty();
    }
}
