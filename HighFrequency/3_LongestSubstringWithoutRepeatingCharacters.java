// // Brute force(TLE)  O(n^2)time O(n)space
// class Solution {
//     public int lengthOfLongestSubstring(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
//         int max = 1;
//         int len = 0;
        
//         for (int i = 0; i < n; i++) {
//             len = 0;
//             HashSet<Character> set = new HashSet<>();
//             for (int j = i; j < n; j++) {
//                 if (!set.contains(ss[j])) {
//                     len++;
//                     max = Math.max(max, len);
//                 } else {
//                     len = 1;
//                     set.clear();
//                 }
//                 set.add(ss[j]);
//             }
//         }
        
//         return max;
//     }
// }


// Two Pointers O(n)time O(1)space
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        int[] used = new int[256];  // store whether we have used a character
        int max = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            while (used[ss[right]] > 0) {
                used[ss[left]]--;
                left++;
            }
            
            used[ss[right]]++;
            max = Math.max(max, right - left + 1);
        }
        
        return max;
    }
}