public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        int mask = 1;
        for (int i = 0; i < 31; i++) {
            if ((n & mask) != 0) {
                res++;
            }
            mask = mask << 1;
        }
        if ((n & mask) != 0) {
            res++;
        }

        return res;
    }
}