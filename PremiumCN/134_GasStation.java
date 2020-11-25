// class Solution {
//     public int canCompleteCircuit(int[] gas, int[] cost) {
//         if (gas == null || cost == null || gas.length != cost.length) {
//             return -1;
//         }

//         int n = gas.length;
//         int[] remains = new int[2 * n];
//         for (int i = 0; i < 2 * n; i++) {
//             remains[i] = gas[i % n] - cost[i % n];
//         }

//         int start = 0;
//         int sum = 0;
//         for (int i = 0; i < 2 * n; i++) {
//             if (sum < 0) {
//                 start = i;
//                 sum = remains[i];
//             } else if (i - start == n) {
//                 return start;
//             } else {
//                 sum += remains[i];
//             }
//         }
//         return -1;
//     }
// }


class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length) {
            return -1;
        }

        int n = gas.length;

        int start = 0;
        int sum = 0;
        int store = 0;
        int dist = 0;
        for (int i = 0; i < n; i++) {
            if (sum < 0) {
                start = i;
                sum = 0;
            }
            store += gas[i];
            dist += cost[i];
            sum += gas[i] - cost[i];
        }
        if (store < dist) {
            return -1;
        }
        return start;
    }
}