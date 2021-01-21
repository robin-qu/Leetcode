class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int y1 = Math.min(D, H);
        int y2 = Math.max(B, F);
        int x1 = Math.max(A, E);
        int x2 = Math.min(C, G);

        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);

        if (A >= G || C <= E || B >= H || D <= F) {
            return area1 + area2;
        }

        int overlap = Math.abs(x1 - x2) * Math.abs(y1 - y2);

        return area1 + area2 - overlap;
    }
}