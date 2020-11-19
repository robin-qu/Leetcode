import java.math.BigDecimal;
import java.math.MathContext;

class Solution {
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length != 2) {
            return 0;
        }
        
        int n = points.length;
        int res = 1;
        
        for (int i = 0; i < n; i++) {
            Map<BigDecimal, Integer> map = new HashMap<>();
            BigDecimal x1 = new BigDecimal(points[i][0]);
            BigDecimal y1 = new BigDecimal(points[i][1]);
            int overlap = 0;
            for (int j = i + 1; j < n; j++) {
                BigDecimal x2 = new BigDecimal(points[j][0]);
                BigDecimal y2 = new BigDecimal(points[j][1]);
                
                if (x1.equals(x2) && y1.equals(y2)) {
                    overlap++;
                } else if (x1.equals(x2)) {
                    map.put(new BigDecimal(Double.MAX_VALUE), map.getOrDefault(new BigDecimal(Double.MAX_VALUE), 1) + 1);
                } else {
                    BigDecimal numerator = y1.subtract(y2);
                    BigDecimal denominator = x1.subtract(x2);
                    BigDecimal slope = numerator.divide(denominator, new MathContext(20));
                    map.put(slope, map.getOrDefault(slope, 1) + 1);
                }
            }
            
            res = Math.max(res, 1 + overlap);
            for (BigDecimal key : map.keySet()) {
                res = Math.max(res, overlap + map.get(key));
            }
        }
        return res;
    }
}