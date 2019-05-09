/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    private Point origin;
     
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Point[] res = new Point[k];
        if (points == null || origin == null || 
            points.length == 0 || k <= 0) {
            return res;
        }
        
        this.origin = origin;
        PriorityQueue<Point> pq = new PriorityQueue<>(k, new PointComparator());
        
        for (Point p : points) {
            pq.add(p);
        }
        
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        
        return res;
    }
    
    class PointComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            int d1 = getDistance(p1, origin);
            int d2 = getDistance(p2, origin);
            
            if (d1 != d2) {
                return d1 - d2;
            } else if (p1.x != p2.x) {
                return p1.x - p2.x;
            } else {
                return p1.y - p2.y;
            }
        }
    }
    
    private int getDistance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
}