class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        int[] counts = new int[26];
        for (char c : ss) {
            counts[c - 'a']++;
        }
        
        int res = 0;
        for (int i = 1; i <= 26; i++) { 
            int[] currCount = new int[26];
            int seen = 0;
            int valid = 0;
            int left = 0;
            for (int right = 0; right < n; right++) {
                char c = ss[right];
                currCount[c - 'a']++;
                if (currCount[c - 'a'] == 1) {
                    seen++;
                }
                if (currCount[c - 'a'] == k) {
                    valid++;
                }
                while (seen > i) {
                    char cc = ss[left++];
                    currCount[cc - 'a']--;
                    if (currCount[cc - 'a'] == 0) {
                        seen--;
                    }
                    if (currCount[cc - 'a'] == k - 1) {
                        valid--;
                    }
                }
                if (valid == seen) {
                    res = Math.max(res, right - left + 1);
                }
            }
        }
        
        return res;
    }
}


// class Solution {
//     public int longestSubstring(String s, int k) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         return helper(0, s.length() - 1, s.toCharArray(), k);
//     }
            
//     private int helper(int start, int end, char[] ss, int k) {
//         if (start > end) {
//             return 0;
//         }
        
//         int[] counts = new int[26];
//         int seen = 0;
//         int valid = 0;
//         for (int i = start; i <= end; i++) {
//             counts[ss[i] - 'a']++;
//             if (counts[ss[i] - 'a'] == 1) {
//                 seen++;
//             }
//             if (counts[ss[i] - 'a'] == k) {
//                 valid++;
//             }
//         }
        
//         if (seen == valid) {
//             return end - start + 1;
//         }
        
//         int left = 0;
//         int res = 0;
//         for (int right = start; right <= end; right++) {
//             if (counts[ss[right] - 'a'] < k) {
//                 res = Math.max(res, helper(left, right - 1, ss, k));
//                 left = right + 1;
//             }
//         }
        
//         res = Math.max(res, helper(left, end, ss, k));
        
//         return res;
//     }
// }