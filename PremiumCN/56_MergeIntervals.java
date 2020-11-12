class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return new int[0][0];
        }
        
        int n = intervals.length;
        
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        
        List<Integer> list = new ArrayList<>();
        
        int end = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                list.add(intervals[i][0]);
                end = intervals[i][1];
            } else if (intervals[i][0] > end) {
                list.add(end);
                list.add(intervals[i][0]);
                end = intervals[i][1];
            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }
        list.add(end);
        int[][] res = new int[list.size() / 2][2];
        for (int i = 0; i < res.length; i++) {
            res[i][0] = list.get(i * 2);
            res[i][1] = list.get(i * 2 + 1);
        }
        
        return res;
    }
}