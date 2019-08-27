// // dfs O(r*c)time O(1)space (TLE)
// class Solution {
//     class ResultType {
//         public int r;
//         public int c;
//         public boolean canFit;
        
//         public ResultType(int r, int c, boolean canFit) {
//             this.r = r;
//             this.c = c;
//             this.canFit = canFit;
//         }
//     }

//     public int wordsTyping(String[] sentence, int rows, int cols) {
//         if (sentence == null || sentence.length == 0) {
//             return 0;
//         }
        
//         int res = 0;
//         ResultType rt = canFit(sentence, 0, rows, 0, cols);
//         while (rt.canFit) {
//             res++;
//             rt = canFit(sentence, rt.r, rows, rt.c, cols);
//         }
        
//         return res;
//     }
    
//     private ResultType canFit(String[] sentence, int r0, int rows, int c0, int cols) {
//         for (String s : sentence) {
//             int len = s.length();
            
//             if (len > cols) {  // one single word cannot fit in one line
//                 return new ResultType(-1, -1, false);
//             }
            
//             if (c0 + len > cols) {  // cannot continue adding word into the line
//                 c0 = 0;
//                 r0++;
//                 if (r0 >= rows) {
//                     return new ResultType(-1, -1, false);
//                 }
//             }
            
//             c0 += len + 1;
//         }
        
//         return new ResultType(r0, c0, true);
//     }
// }


// // dp O(r*c/len*col)time O(1)space
// class Solution {
//     public int wordsTyping(String[] sentence, int rows, int cols) {
//         if (sentence == null || sentence.length == 0) {
//             return 0;
//         }
        
//         String s = String.join(" ", sentence) + " ";
//         int len = s.length();
//         int start = 0;
        
//         for (int i = 0; i < rows; i++) {
//             start += cols;
            
//             if (s.charAt(start % len) == ' ') {  // the beginning of a row is an empty space, just skip it
//                 start++;
//             } else {  // it points to a character, cannot fit in the current row
//                 while (start > 0 && s.charAt((start - 1) % len) != ' ') {
//                     start--;  // step backward to the start of a word
//                 }
//             }
//         }
        
//         return start / len;
//     }
// }


// dp O(r*c/len*col)time O(1)space
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        if (sentence == null || sentence.length == 0) {
            return 0;
        }
        
        int n = sentence.length;
        int[] dp = new int[n];  // dp[i] is if the row starts at sentence[i], the number of words can be placed in this row (can be greater than n)
        
        for (int i = 0; i < n; i++) {
            int len = 0;
            int wordsCount = 0;
            int idx = i;
            
            while (len + sentence[idx % n].length() <= cols) {
                len += sentence[idx % n].length() + 1;
                idx++;
                wordsCount++;
            }
            
            dp[i] = wordsCount;
        }
        
        int count = 0;
        int idx = 0;
        for (int i = 0; i < rows; i++) {
            count += dp[idx];   // number of words in the line
            idx = (idx + dp[idx]) % n;
        }
        
        return count / n;
    }
}