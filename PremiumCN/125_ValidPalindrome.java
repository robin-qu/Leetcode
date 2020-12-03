class Solution {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        int left = 0;
        int right = s.length() - 1;
        char[] ss = s.toCharArray();
        while (left < right) {
            if (!Character.isDigit(ss[left]) && !Character.isLetter(ss[left])) {
                left++;
            } else if (!Character.isDigit(ss[right]) && !Character.isLetter(ss[right])) {
                right--;
            } else if (Character.toLowerCase(ss[left]) != Character.toLowerCase(ss[right])) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}