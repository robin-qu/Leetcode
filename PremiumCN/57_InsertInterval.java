class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || newInterval == null) {
            return new int[0][0];
        }

        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i][0]);
            list.add(intervals[i][1]);
            i++;
            continue;
        }

        int start = newInterval[0];
        int end = newInterval[1];
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            start = Math.min(intervals[i][0], start);
            end = Math.max(intervals[i][1], end);
            i++;
        }
        list.add(start);
        list.add(end);

        while (i < intervals.length && intervals[i][0] > newInterval[1]) {
            list.add(intervals[i][0]);
            list.add(intervals[i][1]);
            i++;
            continue;
        }

        int[][] res = new int[list.size() / 2][2];
        for (int j = 0; j < list.size() / 2; j++) {
            res[j][0] = list.get(j * 2);
            res[j][1] = list.get(j * 2 + 1);
        }
        return res;
    }
}