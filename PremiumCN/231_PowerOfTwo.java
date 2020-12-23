// class Solution {
//     public boolean isPowerOfTwo(int n) {
//         if (n <= 0) {
//             return false;
//         }
//         while (n > 1) {
//             if (((n >> 1) << 1) == n) {
//                 n = n >> 1;
//             } else {
//                 return false;
//             }
//         }

//         return true;
//     }
// }

class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        long x = (long) n;
        return (x & (x - 1)) == 0;
    }
}

