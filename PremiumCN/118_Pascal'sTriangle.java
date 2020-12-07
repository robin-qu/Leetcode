class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }

        res.add(Arrays.asList(1));
        if (numRows == 1) {
            return res;
        }

        for (int i = 2; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            List<Integer> prev = res.get(i - 2);
            for (int j = 0; j + 1 < prev.size(); j++) {
                list.add(prev.get(j) + prev.get(j + 1));
            }
            list.add(1);
            res.add(list);
        }

        return res;
    }
}