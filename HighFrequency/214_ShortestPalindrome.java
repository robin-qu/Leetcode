// brute force O(n^2)time O(n)space
class Solution {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        int n = s.length();
        
        int end = 0;
        for (int i = 1; i <= n; i++) {
            if (isPalindrome(s.substring(0, i))) {
                end = i;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = n - 1; i >= end; i--) {
            sb.append(s.charAt(i));
        }
        
        sb.append(s);
        
        return sb.toString();
    }
    
    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}