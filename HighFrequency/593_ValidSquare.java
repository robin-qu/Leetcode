// O(1)time O(1)space
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }
        
        int[][] ps = new int[][]{p1, p2, p3, p4};
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                set.add(getDist(ps[i], ps[j]));
            }
        }
        
        return !set.contains(0) && set.size() == 2;
    }
    
    private int getDist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}