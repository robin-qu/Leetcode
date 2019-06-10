// class Solution {
//     public String convert(String s, int numRows) {
//         if (s == null || s.length() == 0 || numRows <= 1) {
//             return s;
//         }
        
//         char[] c = s.toCharArray();
//         int len = s.length();
//         StringBuffer[] sb = new StringBuffer[numRows];
//         for (int i = 0; i < numRows; i++) {
//             sb[i] = new StringBuffer();
//         }
        
//         int idx = 0;
//         while (idx + numRows + numRows - 3 < len) {
//             for (int i = 0; i < numRows; i++) {
//                 sb[i].append(c[idx]);
//                 idx++;
//             }
//             for (int i = numRows - 2; i > 0; i--) {
//                 sb[i].append(c[idx]);
//                 idx++;
//             }
//         }
        
//         int i = 0;
//         while (idx < len && i < numRows) {
//             sb[i].append(c[idx]);
//             i++;
//             idx++;
//         }
        
//         i = numRows - 2;
//         while (idx < len) {
//             sb[i].append(c[idx]);
//             i--;
//             idx++;
//         }
        
//         String res = "";
//         for (int j = 0; j < numRows; j++) {
//             res += sb[j].toString();
//         }
        
//         return res;
//     }
// }

class Solution {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 1) {
            return s;
        }
        
        char[] c = s.toCharArray();
        int len = s.length();
        StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) {
            sb[i] = new StringBuffer();
        }
        
        int idx = 0;
        while (idx < len) {
            for (int i = 0; i < numRows && idx < len; i++) {
                sb[i].append(c[idx]);
                idx++;
            }
            for (int i = numRows - 2; i > 0 && idx < len; i--) {
                sb[i].append(c[idx]);
                idx++;
            }
        }
        
        for (int j = 1; j < numRows; j++) {
            sb[0].append(sb[j]);
        }
        
        return sb[0].toString();
    }
}