class Solution {
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        return dfs(0, n, new ArrayList<>());
    }

    private int dfs(int idx, int n, List<Integer> list) {
        if (idx == n) {
            return 1;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (canPlace(list, i)) {
                list.add(i);
                res += dfs(idx + 1, n, list);
                list.remove(list.size() - 1);
            }
        }

        return res;
    }

    private boolean canPlace(List<Integer> list, int b) {
        for (int i = 0; i < list.size(); i++) {
            int x1 = list.get(i);
            int y1 = i;
            int x2 = b;
            int y2 = list.size();
            if (x1 == x2 || y1 == y2 || x1 + y1 == x2 + y2 || x1 - y1 == x2 - y2) {
                return false;
            }
        }
        return true;
    }
}