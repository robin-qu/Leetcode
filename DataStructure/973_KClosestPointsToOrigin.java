class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][];
        PriorityQueue<int[]> pq = new PriorityQueue<>(K, new PointComparator());
        
        for (int[] p : points) {
            pq.add(p);
        }
        
        for (int i = 0; i < K; i++) {
            res[i] = pq.poll();
        }
        
        return res;
    }
    
    class PointComparator implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            int[] origin = new int[]{0, 0};
            int d1 = getDistance(origin, p1);
            int d2 = getDistance(origin, p2);
            
            return d1 - d2;
        }
    }
    
    private int getDistance(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + 
            (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}