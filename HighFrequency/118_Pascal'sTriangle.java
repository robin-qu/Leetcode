// DP O(n^2)time O(1)space
class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows < 1) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        
        int rowIdx = 1;
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        rowIdx++;
        while (rowIdx <= numRows) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int i = 1; i < rowIdx - 1; i++) {
                row.add(res.get(rowIdx - 2).get(i - 1) + res.get(rowIdx - 2).get(i));
            }
            row.add(1);
            res.add(row);
            rowIdx++;
        }
        
        return res;
    }
}