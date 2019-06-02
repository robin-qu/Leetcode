// public class Solution {
//     /**
//      * @param s: a string
//      * @return: an integer
//      */
//     public int lengthOfLongestSubstring(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         int len = s.length();
//         int left = 0;
//         int right = 1;
//         Set<Character> set = new HashSet<>();
//         set.add(s.charAt(left));
//         int res = 1;
        
//         while (left <= right && right < len) {
//             if (!set.contains(s.charAt(right))) {
//                 set.add(s.charAt(right));
//                 res = Math.max(res, right - left + 1);
//                 right++;
//             } else {
//                 set.remove(s.charAt(left));
//                 left++;
//             }
//         }
        
//         return res;
//     }
// }


// // use right pointer as main pointer
// public class Solution {
//     /**
//      * @param s: a string
//      * @return: an integer
//      */
//     public int lengthOfLongestSubstring(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }

//         int left = 0;
//         int longestLen = 0;
//         boolean[] used = new boolean[256];

//         for (int right = 0; right < s.length(); right++) {
//             while (used[s.charAt(right)]) {
//                 used[s.charAt(left)] = false;
//                 left++;
//             }
            
//             used[s.charAt(right)] = true;
//             longestLen = Math.max(longestLen, right - left + 1);
//         }

//         return longestLen;
//     }
// }


// use left pointer as main pointer
public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int right = 0;
        int longestLen = 0;
        boolean[] used = new boolean[256];

        for (int left = 0; left < n; left++) {
            while (right < n && !used[s.charAt(right)]) {
                used[s.charAt(right)] = true;
                right++;
            }
            
            longestLen = Math.max(longestLen, right - left);
            
            used[s.charAt(left)] = false;
        }

        return longestLen;
    }
}
