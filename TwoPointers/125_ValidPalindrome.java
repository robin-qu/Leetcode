// class Solution {
//     public boolean isPalindrome(String s) {
//         if (s.length() == 0) return true;
//         s = s.toLowerCase();
//         int front = 0;
//         int back = s.length() - 1;
//         while (front < back) {
//             if (!isAlphanumeric(s.charAt(front))) front++;
//             else if (!isAlphanumeric(s.charAt(back))) back--;
//             else if (s.charAt(front) != s.charAt(back)) return false;
//             else {
//                 front++;
//                 back--;
//             }
//         }
//         return true;
//     }
//     private boolean isAlphanumeric(char c) {
//         return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
//     }
// }


class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        int len = s.length();
        
        while (left < right) {
            while (left < len && !isAlphanumeric(s.charAt(left))) {
                left++;
            }
            
            while (right >= 0 && !isAlphanumeric(s.charAt(right))) {
                right--;
            }
            
            if (left < len && right >= 0) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
    
    
    private boolean isAlphanumeric(char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }
}