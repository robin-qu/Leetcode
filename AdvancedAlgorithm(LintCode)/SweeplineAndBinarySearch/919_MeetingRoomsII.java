/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    
    class Event implements Comparable<Event> {
        public int time;
        public int start;  // 1 is start, 0 is end
        
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
    
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }
        
        List<Event> events = new ArrayList<>();
        
        for (Interval i : intervals) {
            events.add(new Event(i.start, 1));
            events.add(new Event(i.end, 0));
        }
        
        Collections.sort(events);
        
        int max = 0;
        int count = 0;
        for (Event e : events) {
            if (e.start == 1) {
                count++;
            } else {
                count--;
            }
            
            max = Math.max(count, max);
        }
        
        return max;
    }
}