class Solution {
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<List<String>> res = new ArrayList<>();
        dfs(0, n, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int idx, int n, List<Integer> list, List<List<String>> res) {
        if (idx == n) {
            res.add(toRes(list, n));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (canPlace(list, i)) {
                list.add(i);
                dfs(idx + 1, n, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    private List<String> toRes(List<Integer> list, int n) {
        List<String> res = new ArrayList<>();
        for (int num : list) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i == num) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            res.add(sb.toString());
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