// // D&C O(n!)time O(n)space
// class Solution {
//     public List<Integer> diffWaysToCompute(String input) {
//         if (input == null || input.length() == 0) {
//             return new ArrayList<>();
//         }
        
//         List<Integer> res = new ArrayList<>();
//         boolean hasOpr = false;
//         for (int i = 0; i < input.length(); i++) {
//             char c = input.charAt(i);
//             if (!Character.isDigit(c)) {
//                 hasOpr = true;
//                 List<Integer> left = diffWaysToCompute(input.substring(0, i));
//                 List<Integer> right = diffWaysToCompute(input.substring(i + 1, input.length()));
//                 for (int l : left) {
//                     for (int r : right) {
//                         res.add(evaluate(l, r, c));
//                     }
//                 }
//             }
//         }
        
//         if (!hasOpr) {
//             res.add(Integer.parseInt(input));
//         }
        
//         return res;
//     }

//     private int evaluate(int a, int b, char c) {
//         if (c == '+') {
//             return a + b;
//         }
        
//         if (c == '-') {
//             return a - b;
//         }
        
//         return a * b;
//     }
// }


// D&C with memo O(n^3)time O(n)space
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        if (input == null || input.length() == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<Integer>> memo = new HashMap<>();
        
        return helper(input, memo);
    }
     
    private List<Integer> helper(String input, Map<String, List<Integer>> memo) {
        if (memo.containsKey(input)) {
            return memo.get(input);
        }
        
        List<Integer> res = new ArrayList<>();
        boolean hasOpr = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isDigit(c)) {
                hasOpr = true;
                List<Integer> left = helper(input.substring(0, i), memo);
                List<Integer> right = helper(input.substring(i + 1, input.length()), memo);
                for (int l : left) {
                    for (int r : right) {
                        res.add(evaluate(l, r, c));
                    }
                }
            }
        }
        
        if (!hasOpr) {
            res.add(Integer.parseInt(input));
        }
        
        memo.put(input, res);
        return res;
    }

    private int evaluate(int a, int b, char c) {
        if (c == '+') {
            return a + b;
        }
        
        if (c == '-') {
            return a - b;
        }
        
        return a * b;
    }
}