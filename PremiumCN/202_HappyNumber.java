// class Solution {
//     public boolean isHappy(int n) {
//         Set<Integer> set = new HashSet<>();
//         while (n != 1 && !set.contains(n)) {
//             set.add(n);
//             int curr = 0;
//             while (n > 0) {
//                 curr += (n % 10) * (n % 10);
//                 n /= 10;
//             }
//             n = curr;
//         }
//         return n == 1;
//     }
// }


class Solution {
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        while (getNext(getNext(fast)) != getNext(slow)) {
            fast = getNext(getNext(fast));
            slow = getNext(slow);
        }
        return getNext(slow) == 1;
    }

    private int getNext(int n) {
        int curr = 0;
        while (n > 0) {
            curr += (n % 10) * (n % 10);
            n /= 10;
        }
        return curr;
    }
}