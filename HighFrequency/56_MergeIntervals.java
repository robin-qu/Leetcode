// Sweep Line O(nlogn)time O(n)space
class Solution {
    class Event implements Comparable<Event> {
        public int pos;
        public int end;  // 0 is start, 1 is end
        
        public Event(int pos, int end) {
            this.pos = pos;
            this.end = end;
        }
        
        @Override
        public int compareTo(Event other) {
            if (this.pos != other.pos) {
                return this.pos - other.pos;
            }
            
            return this.end - other.end;
        }
    }
    
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 ||
            intervals[0] == null || intervals[0].length != 2) {
            return new int[0][0];
        }
        
        int n = intervals.length;
        Event[] events = new Event[2 * n];
        for (int i = 0; i < n; i++) {
            events[2 * i] = new Event(intervals[i][0], 0);
            events[2 * i + 1] = new Event(intervals[i][1], 1);
        }
        
        Arrays.sort(events);
        List<Integer> temp = new ArrayList<>();
        
        int status = 0;  // status is the number of intervals that we are currently in
        for (int i = 0; i < 2 * n; i++) {
            if (events[i].end == 0) {
                status++;
                if (status == 1) {  // start of an interval
                    temp.add(events[i].pos);
                }
            } else {
                status--;
                if (status == 0) {  // end of an interval
                    temp.add(events[i].pos);
                }
            }
        }
        
        int len = temp.size();  // len must be an even number
        int[][] res = new int[len / 2][2];
        for (int i = 0; i < len / 2; i++) {
            res[i][0] = temp.get(2 * i);
            res[i][1] = temp.get(2 * i + 1);
        }
        
        return res;
    }
} 