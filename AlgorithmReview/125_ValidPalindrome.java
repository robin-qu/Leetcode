class Solution {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        
        if (s.length() == 0) {
            return true;
        }
        
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            while (left < right && !isAlphanumerical(s.charAt(left))) {
                left++;
            }
            
            while (left < right && !isAlphanumerical(s.charAt(right))) {
                right--;
            }
            
            if (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        
        return true;
    }
    
    private boolean isAlphanumerical(char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }
}