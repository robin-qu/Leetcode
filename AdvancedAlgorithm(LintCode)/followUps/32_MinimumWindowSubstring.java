// // use the left pointer as main pointer
// public class Solution {
//     /**
//      * @param source : A string
//      * @param target: A string
//      * @return: A string denote the minimum window, return "" if there is no such a string
//      */
//     public String minWindow(String source , String target) {
//         if (source == null || target == null || source.length() == 0) {
//             return "";
//         }
        
//         char[] ss = source.toCharArray();
//         char[] tt = target.toCharArray();
        
//         int[] tCount = new int[256];
//         int T = 0;
//         for (char c : tt) {
//             if (tCount[c] == 0) {
//                 T++;
//             }
//             tCount[c]++;
//         }
        
//         int[] sCount = new int[256];
//         int S = 0;
        
//         int right = 0;
//         int minLen = Integer.MAX_VALUE;
//         String res = "";
//         for (int left = 0; left < source.length(); left++) {
//             while (S < T && right < source.length()) {
//                 sCount[ss[right]]++;
//                 if (sCount[ss[right]] == tCount[ss[right]]) {
//                     S++;
//                 }
//                 right++;
//             }
            
//             if (S == T) {
//                 if (right - left < minLen) {
//                     minLen = right - left;
//                     res = source.substring(left, right);
//                 }
//             }
            
//             sCount[ss[left]]--;
//             if (sCount[ss[left]] == tCount[ss[left]] - 1) {
//                 S--;
//             }
//         }
        
//         return res;
//     }
// }

// use the right pointe as main pointer
public class Solution {
    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source , String target) {
        if (source == null || target == null || source.length() == 0) {
            return "";
        }
        
        char[] ss = source.toCharArray();
        char[] tt = target.toCharArray();
        
        int[] tCount = new int[256];
        int T = 0;
        for (char c : tt) {
            if (tCount[c] == 0) {
                T++;
            }
            tCount[c]++;
        }
        
        int[] sCount = new int[256];
        int S = 0;
        
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        String res = "";
        for (int right = 0; right < source.length(); right++) {
            char c = ss[right];
            sCount[c]++;
            if (sCount[c] == tCount[c]) {
                S++;
            }
            
            while (left <= right && S == T) {
                // record the optimal value
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    res = source.substring(left, right + 1);
                }
                
                // move the left boundary to right by one
                sCount[ss[left]]--;
                if (sCount[ss[left]] == tCount[ss[left]] - 1) {
                    S--;
                }
                left++;
                
                // skips chars not in target
                while (left < right && tCount[ss[left]] == 0) {
                    sCount[ss[left]]--;
                    left++;
                }
            }
        }
        
        return res;
    }
}