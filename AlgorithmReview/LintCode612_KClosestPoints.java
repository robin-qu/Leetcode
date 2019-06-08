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
     
    class distanceComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            int d1 = getDistance(p1);
            int d2 = getDistance(p2);
            if (d1 != d2) {
                return d2 - d1;//d1 - d2;
            } else if (p1.x != p2.x) {
                return p2.x - p1.x;//p1.x - p2.x;
            } else {
                return p2.y - p1.x;//p1.y - p2.y;
            }
        }
    }
    
    private Point origin;
     
    public Point[] kClosest(Point[] points, Point origin, int k) {
        if (points == null || origin == null) {
            return null;
        }
        
        this.origin = origin;
        
        Point[] res = new Point[k];
        
        PriorityQueue<Point> pq = new PriorityQueue<>(new distanceComparator());
        
        for (Point p : points) {
            pq.offer(p);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll();
        }
        
        return res;
    }
    
    private int getDistance(Point p) {
        return (p.x - origin.x) * (p.x - origin.x) + (p.y - origin.y) * (p.y - origin.y);
    }
}