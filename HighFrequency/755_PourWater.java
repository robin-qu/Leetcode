// O(n^2)time  O(1)space
class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        if (heights == null || heights.length == 0) {
            return new int[0];
        }
        
        int n = heights.length;
        for (int i = 0; i < V; i++) {
            int pos = K;
            
            // move left
            while (pos > 0 && heights[pos - 1] <= heights[pos]) {
                pos--;
            }
            
            // move right
            while (pos < n - 1 && heights[pos] >= heights[pos + 1]) {
                pos++;
            }
            
            // move left to k
            while (pos > K && heights[pos - 1] == heights[pos]) {
                pos--;
            }
            
            heights[pos]++;
        }
        
        return heights;
    }
}