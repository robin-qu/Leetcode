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

// public class Solution {
//     /**
//      * @param intervals: an array of meeting time intervals
//      * @return: if a person could attend all meetings
//      */
//     class Event implements Comparable<Event> {
//         public int time;
//         public int start; // 1 is start and 0 is end
        
//         public Event(int time, int start) {
//             this.time = time;
//             this.start = start;
//         }
        
//         @Override
//         public int compareTo(Event other) {
//             if (this.time != other.time) {
//                 return this.time - other.time;
//             } else {
//                 return this.start - other.start;
//             }
//         }
//     }
    
//     public boolean canAttendMeetings(List<Interval> intervals) {
//         if (intervals == null || intervals.size() == 0) {
//             return true;
//         }
        
//         List<Event> events = new ArrayList<>();
        
//         for (Interval interval : intervals) {
//             events.add(new Event(interval.start, 1));
//             events.add(new Event(interval.end, 0));
//         }
        
//         Collections.sort(events);
        
//         for (int i = 0; i < events.size(); i++) {
//             if (events.get(i).start == i % 2) {
//                 return false;
//             }
//         }
        
//         return true;
//     }
// }

public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }
    
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return true;
        }
        
        Collections.sort(intervals, new IntervalComparator());
        
        int end = Integer.MIN_VALUE;
        for (Interval i : intervals) {
            if (i.start < end) {
                return false;
            }
            end = Math.max(end, i.end);
        }
        
        return true;
    }
}