// // use right pointer as main pointer
// public class Solution {
//     /**
//      * @param s: A string
//      * @param k: An integer
//      * @return: An integer
//      */
//     public int lengthOfLongestSubstringKDistinct(String s, int k) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         char[] ss = s.toCharArray();
//         int[] count = new int[256];
//         int currK = 0;
        
//         int res = 0;
//         int left = 0;
//         for (int right = 0; right < s.length(); right++) {
//             char c = ss[right];
//             count[c]++;
//             if (count[c] == 1) {
//                 currK++;
//             }
            
//             if (currK <= k) {
//                 res = Math.max(res, right - left + 1);
//                 continue;
//             }
            
//             while (currK > k) {
//                 count[ss[left]]--;
//                 if (count[ss[left]] == 0) {
//                     currK--;
//                 }
//                 left++;
//             }
//         }
//         return res;
//     }
// }

// use left pointer as main pointer
public class Solution {
    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] ss = s.toCharArray();
        int[] count = new int[256];
        int currK = 0;

        int res = 0;
        int right = 0;
        for (int left = 0; left < s.length(); left++) {
            while (currK <= k && right < s.length()) {
                count[ss[right]]++;
                if (count[ss[right]] == 1) {
                    currK++;
                }
                right++;
            }
            
            if (currK <= k && right == s.length()) {
                res = Math.max(res, right - left);
            } else {
                res = Math.max(res, right - left - 1);
            }
            
            
            count[ss[left]]--;
            if (count[ss[left]] == 0) {
                currK--;
            }
        }
        return res;
    }
}