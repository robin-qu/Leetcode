// // O(n)time O(n)space
// class Solution {
//     public boolean isHappy(int n) {
//         if (n <= 0) {
//             return false;
//         }
        
//         Set<Integer> seen = new HashSet<>();
        
//         while (!seen.contains(n)) {
//             seen.add(n);
//             n = getSquareSum(n);
//             if (n == 1) {
//                 return true;
//             }
//         }
        
//         return false;
//     }
    
//     private int getSquareSum(int n) {
//         int res = 0;
//         while (n > 0) {
//             res += (n % 10) * (n % 10);
//             n /= 10;
//         }
        
//         return res;
//     }
// }


// O(n)time O(n)space
class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }
        
        int slow = n;
        int fast = n;
        
        while (slow != 1) {
            slow = getSquareSum(slow);
            if (slow == 1) {
                return true;
            }
            
            fast = getSquareSum(getSquareSum(fast));
            if (fast == 1) {
                return true;
            }
            
            if (slow == fast) {
                return false;
            }
        }
        
        return true;
    }
    
    private int getSquareSum(int n) {
        int res = 0;
        while (n > 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        
        return res;
    }
}