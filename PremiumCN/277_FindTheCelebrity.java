/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

// public class Solution extends Relation {
//     public int findCelebrity(int n) {
//         if (n == 0) {
//             return 0;
//         }
//         for (int i = 0; i < n; i++) {
//             if (isCelebrity(i, n)) {
//                 return i;
//             }
//         }
//         return -1;
//     }

//     private boolean isCelebrity(int a, int n) {
//         for (int i = 0; i < n; i++) {
//             if (i == a) {
//                 continue;
//             }
//             if (knows(a, i)) {
//                 return false;
//             }
//             if (!knows(i, a)) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }


public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n == 0) {
            return 0;
        }
        
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (candidate == i) {
                continue;
            }
            if (knows(candidate, i) || !knows(i, candidate)) {
                return -1;
            }
        }

        return candidate;
    }
}