// PriorityQueue O(nlogk)time O(n)space
class Solution {
    class Point implements Comparable<Point> {
        public int x;
        public int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Point other) {
            return getDistance(other.x, other.y) - getDistance(this.x, this.y);
        }
    }
    
    private int getDistance(int x, int y) {
        return x * x + y * y;
    }
    
    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 ||
            points[0] == null || points[0].length != 2) {
            return new int[0][0];
        }
        
        int n = points.length;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            pq.offer(new Point(points[i][0], points[i][1]));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            Point curr = pq.poll();
            res[i][0] = curr.x;
            res[i][1] = curr.y;
        }
        
        return res;
    }
}