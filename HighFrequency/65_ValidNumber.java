// class Solution {
//     public boolean isNumber(String s) {
//         s = s.trim();
//         if (s.split(" ").length > 1) {
//             return false;  //  "1 a"
//         }
        
//         char[] ss = s.toCharArray();
//         int n = s.length();
        
//         int eIdx = -1;
//         for (int i = 0; i < n; i++) {
//             if (Character.isLetter(ss[i])) {
//                 if (ss[i] == 'e') {
//                     if (eIdx != -1) {  // there is already an 'e'
//                         return false;
//                     }
//                     eIdx = i;
//                 } else {
//                     return false;   // "abc"
//                 }
//             }
//         }
//         if (eIdx == 0 || eIdx == n - 1) {
//             return false;   // "e3"  " 1e"
//         }
        
//         int pointIdx = -1;
//         for (int i = 0; i < n; i++) {
//             if (ss[i] == '.') {
//                 if (pointIdx != -1) {
//                     return false;
//                 }
//                 pointIdx = i;
//             }
//         }
        
//         if (pointIdx != -1 && pointIdx != n - 1 && !Character.isDigit(ss[pointIdx + 1])) {
//             return false;
//         }
        
//         if (pointIdx == 0 && pointIdx == n - 1) {
//             return false;
//         }
        
//         if (eIdx != -1 && pointIdx > eIdx) {
//             return false;   // " 99e2.5 "
//         }
        
//         for (int i = 1; i < eIdx; i++) {
//             if (ss[i] == '+' || ss[i] == '-') {
//                 return false;
//             }
//         }
        
//         if (eIdx >= 0) {
//             for (int i = eIdx + 2; i < n; i++) {
//                 if (ss[i] == '+' || ss[i] == '-') {
//                     return false;
//                 }
//             }
//         }
        
//         return true;
//     }
// }

class Solution {
    public boolean isNumber(String s) {
    s = s.trim();
    
    boolean pointSeen = false;
    boolean eSeen = false;
    boolean numberSeen = false;
    boolean numberAfterE = true;
    for(int i=0; i<s.length(); i++) {
        if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
            numberSeen = true;
            numberAfterE = true;
        } else if(s.charAt(i) == '.') {
            if(eSeen || pointSeen) {
                return false;
            }
            pointSeen = true;
        } else if(s.charAt(i) == 'e') {
            if(eSeen || !numberSeen) {
                return false;
            }
            numberAfterE = false;
            eSeen = true;
        } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
            if(i != 0 && s.charAt(i-1) != 'e') {
                return false;
            }
        } else {
            return false;
        }
    }
    
    return numberSeen && numberAfterE;
}
}