// // O(logn)time O(1)space initial version
// class Solution {
//     public boolean isPalindrome(int x) {
//         if (x < 0) {
//             return false;
//         }
        
//         int original = x;
//         int reverse = 0;
//         while (x != 0) {
//             reverse = reverse * 10 + x % 10;
//             x /= 10;
//         }
        
//         return reverse == original;
//     }
// }

// O(logn)time O(1)space compare half
class Solution {
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        
        return reverse == x || reverse / 10 == x;
    }
}