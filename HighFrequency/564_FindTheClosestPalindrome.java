// O(n)time O(1)space
class Solution {
    public String nearestPalindromic(String n) {
        if (n == null || n.length() == 0) {
            return "";
        }
        
        int len = n.length();
        int mid = (len % 2 == 0 ? len / 2 - 1 : len / 2);
        long left = Long.parseLong(n.substring(0, mid + 1)); //  (123)45 or (12)34
        
        long[] candidates = new long[5];
        boolean isOdd = (len % 2 == 1);
        // length does not change
        candidates[0] = constructPalindrome(left, isOdd);      // 12321
        candidates[1] = constructPalindrome(left - 1, isOdd);  // 12221
        candidates[2] = constructPalindrome(left + 1, isOdd);  // 12421
        // length changes
        candidates[3] = (long) Math.pow(10, len - 1) - 1;       // 9999
        candidates[4] = (long) Math.pow(10, len) + 1;        //  100001
        
        long min = Long.MAX_VALUE;
        long res = 0;
        long original = Long.parseLong(n);
        for (long cand : candidates) {
            if (cand == original) {
                continue;
            }
            
            if (Math.abs(cand - original) < min) {
                min = Math.abs(cand - original);
                res = cand;                
            } else if (Math.abs(cand - original) == min) {
                res = Math.min(res, cand);
            }
        }
        
        return String.valueOf(res);
    }
    
    private long constructPalindrome(long left, boolean isOdd) {
        long res = left;
        if (isOdd) {
            left /= 10;
        }
        
        while (left > 0) {
            res = res * 10 + left % 10;
            left /= 10;
        }
        
        return res;
    }
}