// // PriorityQueue
// class Solution {
//     public int nthUglyNumber(int n) {
//         if (n <= 0) {
//             return 0;
//         }
//         Long curr = 0l;
//         PriorityQueue<Long> pq = new PriorityQueue<>();
//         pq.add(1l);
        
//         for (int i = 0; i < n; i++) {
//             Long next = pq.poll();
//             while (next.equals(curr)) {
//                 next = pq.poll();
//             }
//             pq.add(2 * next);
//             pq.add(3 * next);
//             pq.add(5 * next);
//             curr = next;
//         }
        
//         return curr.intValue();
//     }
// }


// DP O(n) scan
class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        List<Integer> uglys = new ArrayList<Integer>();
        uglys.add(1);
        
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        for (int i = 1; i < n; i++) {
            int lastNumber = uglys.get(i - 1);
            if (uglys.get(p2) * 2 <= lastNumber) p2++;
            if (uglys.get(p3) * 3 <= lastNumber) p3++;
            if (uglys.get(p5) * 5 <= lastNumber) p5++;
            
            uglys.add(Math.min(
                Math.min(uglys.get(p2) * 2, uglys.get(p3) * 3),
                uglys.get(p5) * 5));
        }

        return uglys.get(n - 1);
    }
};