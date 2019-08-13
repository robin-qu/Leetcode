class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        char[] ss = s.toCharArray();
        
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(ss[left])) {
                left++;
            }
            
            while (left < right && !Character.isLetterOrDigit(ss[right])) {
                right--;
            }
            
            if (left < right) {
                if (Character.toLowerCase(ss[left]) != Character.toLowerCase(ss[right])) {
                    return false;
                }
                left++;
                right--;
            }
        }
        
        return true;
    }
}