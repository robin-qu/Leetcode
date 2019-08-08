// O(n^2)time O(n)space
class Solution {
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0 ||
            points[0] == null || points[0].length != 2) {
            return 0;
        }
        
        int n = points.length;
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            Map<Double, Integer> map = new HashMap<>();
            int overlap = 1;
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                if (x1 == x2 && y1 == y2) {
                    overlap++;
                } else if (x1 == x2) {
                    if (!map.containsKey(Double.MAX_VALUE)) {
                        map.put(Double.MAX_VALUE, 1);
                    } else {
                        map.put(Double.MAX_VALUE, map.get(Double.MAX_VALUE) + 1);
                    }
                } else {
                    double slope = 10.0 * (double) (y2 - y1) / (double) (x2 - x1);
                    if (y1 == y2) {
                        slope = 0.0;
                    }
                    if (!map.containsKey(slope)) {
                        map.put(slope, 1);
                    } else {
                        map.put(slope, map.get(slope) + 1);
                    }
                }
            }
            
            int max = 0;
            for (double key : map.keySet()) {
                max = Math.max(max, map.get(key));
            }
            
            res = Math.max(res, max + overlap);
        }
        
        return res;
    }
}