class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null) {
            return res;
        }
        
        if (candidates.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        
        Arrays.sort(candidates);
        
        List<Integer> combination = new ArrayList<>();
        helper(candidates, 0, target, combination, res);
        
        return res;
    }
    
    private void helper(int[] candidates,
                        int idx,
                        int target,
                        List<Integer> combination,
                        List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        
        if (target == 0) {
            res.add(new ArrayList<Integer>(combination));
            return;
        }
        
        for (int i = idx; i < candidates.length; i++) {
            // if (i > idx && candidates[i - 1] == candidates[i]) {
            //     continue;
            // }
            combination.add(candidates[i]);
            helper(candidates, i + 1, target - candidates[i], combination, res);
            int temp = combination.remove(combination.size() - 1);
            while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
                i++;
            }
        }
    }
}

// class Solution {
//     public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//         List<List<Integer>> res = new ArrayList<>();
//         if (candidates == null) {
//             return res;
//         }
        
//         if (candidates.length == 0) {
//             res.add(new ArrayList<>());
//             return res;
//         }
        
//         Arrays.sort(candidates);
        
//         List<Integer> combination = new ArrayList<>();
//         helper(candidates, 0, target, combination, res);
        
//         return res;
//     }
    
//     private void helper(int[] candidates,
//                         int idx,
//                         int target,
//                         List<Integer> combination,
//                         List<List<Integer>> res) {
//         if (target < 0) {
//             return;
//         }
        
//         if (target == 0) {
//             res.add(new ArrayList<Integer>(combination));
//             return;
//         }
        
//         for (int i = idx; i < candidates.length; i++) {
//             if (i > idx && candidates[i - 1] == candidates[i]) {
//                 continue;
//             }
//             combination.add(candidates[i]);
//             helper(candidates, i + 1, target - candidates[i], combination, res);
//             combination.remove(combination.size() - 1);
//         }
//     }
// }