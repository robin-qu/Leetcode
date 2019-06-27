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
     * @param seqA: the list of intervals
     * @param seqB: the list of intervals
     * @return: the time periods
     */
    class Event implements Comparable<Event> {
        public int time;
        public int start;  // 1 is start, 2 is end
        
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
    
    public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
        if (seqA == null || seqA.size() == 0 ||
            seqB == null || seqB.size() == 0) {
            return new ArrayList<>();
        }
        
        List<Event> events = new ArrayList<>();
        
        for (Interval i : seqA) {
            events.add(new Event(i.start, 1));
            events.add(new Event(i.end, 0));
        }
        
        for (Interval i : seqB) {
            events.add(new Event(i.start, 1));
            events.add(new Event(i.end, 0));
        }
        
        Collections.sort(events);
        
        List<Interval> res = new ArrayList<>();
        int count = 0;
        for (Event e : events) {
            if (e.start == 1) {
                count++;
                if (count == 2) {
                    res.add(new Interval(e.time, 0));
                }
            } else {
                count--;
                if (count == 1) {
                    res.get(res.size() - 1).end = e.time;
                }
            }
        }
        
        return res;
    }
}