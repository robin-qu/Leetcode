// Sweep Line O(nlogn)time O(n)space
class Solution {
    class Pair implements Comparable<Pair> {
        public int pos;
        public int start;  // 0 is start, 1 is end; first start
        public int height;
        
        public Pair(int pos, int start, int height) {
            this.pos = pos;
            this.start = start;
            this.height = height;
        }
        
        @Override
        public int compareTo(Pair other) {
            if (this.pos != other.pos) {
                return this.pos - other.pos;
            }
            
            if (this.start != other.start) {  // starting point comes first
                return this.start - other.start;
            }
            
            if (this.start == 0 && other.start == 0) {   // taller one comes first when starts
                return other.height - this.height;
            }
            
            // shorter one comes first when ends
            return this.height - other.height;
        }
    }
    
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0 ||
            buildings[0] == null || buildings[0].length == 0) {
            return new ArrayList<>();
        }
        
        int n = buildings.length;
        Pair[] pairs = new Pair[2 * n];
        
        for (int i = 0; i < n; i++) {
            pairs[2 * i] = new Pair(buildings[i][0], 0, buildings[i][2]);
            pairs[2 * i + 1] = new Pair(buildings[i][1], 1, buildings[i][2]);
        }
        
        Arrays.sort(pairs);
        
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(0);
        
        for (Pair p : pairs) {
            if (p.start == 0) {  // start
                if (p.height > pq.peek()) {
                    List<Integer> coor = new ArrayList<>();
                    coor.add(p.pos);
                    coor.add(p.height);
                    res.add(coor);
                }
                pq.offer(p.height);
            } else {  // end
                if (p.height != pq.peek()) {
                    pq.remove(p.height);
                } else {  // is max height
                    pq.poll();
                    if (p.height != pq.peek()) {
                        List<Integer> coor = new ArrayList<>();
                        coor.add(p.pos);
                        coor.add(pq.peek());
                        res.add(coor);
                    }
                }
            }
        }
        
        return res;
    }
}