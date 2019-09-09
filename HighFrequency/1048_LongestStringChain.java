// // dp O(n^2)time O(n)space
// class Solution {
//     public int longestStrChain(String[] words) {
//         if (words == null || words.length == 0) {
//             return 0;
//         }
        
//         int n = words.length;
//         Arrays.sort(words, new Comparator<String>() {
//             @Override
//             public int compare(String a, String b) {
//                 return a.length() - b.length();
//             }
//         });
        
//         int[] dp = new int[n];
//         Arrays.fill(dp, 1);
        
//         int res = 1;
//         for (int i = 1; i < n; i++) {
//             for (int j = i - 1; j >= 0; j--) {
//                 if (match(words[j], words[i])) {
//                     dp[i] = Math.max(dp[i], dp[j] + 1);
//                 }
//             }
            
//             res = Math.max(dp[i], res);
//         }
        
//         return res;
//     }
    
//     private boolean match(String a, String b) {
//         if (a.length() + 1 != b.length()) {
//             return false;
//         }
        
//         for (int i = 0; i < b.length(); i++) {
//             if (a.equals(b.substring(0, i) + b.substring(i + 1, b.length()))) {
//                 return true;
//             }
//         }
        
//         return false;
//     }
// }


// dp O(nlogn)time O(n)space
class Solution {
    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        
        int n = words.length;
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });
        
        Map<String, Integer> dp = new HashMap<>();
        for (String word : words) {
            dp.put(word, 1);
        }
        
        int res = 1;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                String pred = word.substring(0, i) + word.substring(i + 1, word.length());
                if (dp.containsKey(pred)) {
                    dp.put(word, Math.max(dp.get(word), dp.get(pred) + 1));
                }
            }
            
            res = Math.max(dp.get(word), res);
        }
        
        return res;
    }
}