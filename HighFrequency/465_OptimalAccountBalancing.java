// dfs O(2^n)time O(n)space
class Solution {
    public int minTransfers(int[][] transactions) {
        if (transactions == null || transactions.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
        }
        
        return dfs(0, new ArrayList<>(map.values()));
    }

    // get the min number of transactions start from debt[idx], assuming debts before idx are settled
    private int dfs(int start, List<Integer> debt) {
        while (start < debt.size() && debt.get(start) == 0) {
            start++;  // skip zeros
        }
            
        if (start == debt.size()) {
            return 0;
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = start + 1; i < debt.size(); i++) {
            if (debt.get(start) * debt.get(i) >= 0) {  // skips debts that are not in opposite sign (including 0 debt)
                continue;
            }
            
            debt.set(i, debt.get(i) + debt.get(start));
            res = Math.min(res, 1 + dfs(start + 1, debt));
            debt.set(i, debt.get(i) - debt.get(start));
        }
        
        return res;
    }
}