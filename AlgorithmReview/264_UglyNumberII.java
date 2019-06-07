// class Solution {
//     public int nthUglyNumber(int n) {
//         if (n <= 0) {
//             return 0;
//         }
        
//         PriorityQueue<Long> pq = new PriorityQueue<>();
//         pq.offer(1l);
//         int[] prime = new int[]{2, 3, 5};
//         Set<Long> visited = new HashSet<>();
//         visited.add(1l);
        
//         while (n > 1) {
//             long curr = pq.poll();
//             for (int i = 0; i < 3; i++) {
//                 if (!visited.contains(curr * prime[i])) {
//                     pq.offer(curr * prime[i]);
//                     visited.add(curr * prime[i]);
//                 }
//             }
//             n--;
//         }
        
//         return pq.peek().intValue();
//     }
// }


class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        
        List<Integer> list = new ArrayList<>();
        list.add(1);
        
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        
        for (int i = 0; i < n - 1; i++) {
            int newNum = Math.min(list.get(p2) * 2, 
                              Math.min(list.get(p3) * 3, list.get(p5) * 5));
            list.add(newNum);
            if (newNum % 2 == 0 && newNum / 2 == list.get(p2)) {
                p2++;
            }
            if (newNum % 3 == 0 && newNum / 3 == list.get(p3)) {
                p3++;
            }
            if (newNum % 5 == 0 && newNum / 5 == list.get(p5)) {
                p5++;
            }
        }
        
        return list.get(list.size() - 1);
    }
}