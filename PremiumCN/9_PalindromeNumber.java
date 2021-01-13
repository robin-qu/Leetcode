class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }

        int copy = x;
        int rev = 0;
        while (copy > rev) {
            rev = rev * 10 + copy % 10;
            copy /= 10;
        }

        return rev == copy || rev / 10 == copy;
    }
}