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
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    private int[][] graph;
    private Point[][] parents;
    private int num;
    
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // Special cases
        if (n <= 0 || m <= 0 || operators == null || operators.length == 0) {
            return new ArrayList<>();
        }
        
        // Initializations
        this.num = 0;
        this.graph = new int[n][m];
        this.parents = new Point[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.graph[i][j] = 0;
                this.parents[i][j] = new Point(i, j);
            }
        }
        
        int[] dX = new int[]{0, 1, -1, 0};
        int[] dY = new int[]{1, 0, 0, -1};
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < operators.length; i++) {
            int x = operators[i].x;
            int y = operators[i].y;
            if (graph[x][y] == 1) {
                res.add(num);
                continue;
            }
            graph[x][y] = 1;
            this.num++;
            
            for (int j = 0; j < 4; j++) {
                int newX = x + dX[j];
                int newY = y + dY[j];
                
                if (inBound(newX, newY) && graph[newX][newY] == 1) {
                    connect(x, y, newX, newY);
                }
            }
            res.add(num);
        }
        return res;
    }
    
    private void connect(int x1, int y1, int x2, int y2) {
        Point root1 = find(x1, y1);
        Point root2 = find(x2, y2);
        if (root1.x != root2.x || root1.y != root2.y) {
            parents[root1.x][root1.y] = root2;
            this.num--;
        }
    }
    
    private Point find(int x, int y) {
        Point root = new Point(x, y);
        
        while (parents[root.x][root.y].x != root.x || 
               parents[root.x][root.y].y != root.y) {
            root = parents[root.x][root.y];
        }
        
        Point curr = new Point(x, y);
        while (curr.x != root.x || curr.y != root.y) {
            Point father = parents[curr.x][curr.y];
            parents[curr.x][curr.y] = root;
            curr = father;
        }
        
        return root;
    }
    
    private boolean inBound(int x, int y) {
        int n = graph.length;
        int m = graph[0].length;
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}