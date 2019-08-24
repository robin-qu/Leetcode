// sweep line O(nlogn)time O(n)space
class Solution {
    private class Event implements Comparable<Event> {
        public int time;
        public int start;  // 1 is start
        
        public Event(int time, int start) {
            this.time = time;
            this.start = start;
        }
        
        @Override
        public int compareTo(Event other) {
            if (this.time != other.time) {
                return this.time - other.time;
            }
            
            return this.start - other.start;
        }
    }
    
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0 ||
            intervals[0] == null || intervals[0].length != 2) {
            return true;
        }
        
        int n = intervals.length;
        Event[] events = new Event[2 * n];
        
        for (int i = 0; i < n; i++) {
            events[2 * i] = new Event(intervals[i][0], 1);
            events[2 * i + 1] = new Event(intervals[i][1], 0);
        }
        
        Arrays.sort(events);
        
        int count = 0;
        for (Event e : events) {
            if (e.start == 1) {
                count++;
            } else {
                count--;
            }
            if (count > 1) {
                return false;
            }
        }
        
        return true;
    }
}