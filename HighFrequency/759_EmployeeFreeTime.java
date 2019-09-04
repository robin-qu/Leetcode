/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start,int _end) {
        start = _start;
        end = _end;
    }
};
*/

// // sweep line O(nlogn)time O(n)space  n is the number of all the events
// class Solution {
//     private class Event implements Comparable<Event> {
//         public int time;
//         public int status;  // 0 is start, 1 is end, start first
        
//         public Event(int time, int status) {
//             this.time = time;
//             this.status = status;
//         }
        
//         @Override
//         public int compareTo(Event other) {
//             if (this.time != other.time) {
//                 return this.time - other.time;
//             }
            
//             return this.status - other.status;
//         }
//     }
    
//     public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
//         if (schedule == null || schedule.size() == 0 || 
//             schedule.get(0) == null || schedule.get(0).size() == 0) {
//             return new ArrayList<>();
//         }

//         List<Event> events = new ArrayList<>();
//         for (List<Interval> employee : schedule) {
//             for (Interval interval : employee) {
//                 events.add(new Event(interval.start, 0));
//                 events.add(new Event(interval.end, 1));
//             }
//         }
        
//         Collections.sort(events);
        
//         List<Interval> res = new ArrayList<>();
//         int count = 0;
//         Interval interval = new Interval();
//         for (int i = 0; i < events.size(); i++) {
//             Event e = events.get(i);
//             if (e.status == 0) {
//                 count++;
//                 if (i > 0 && count == 1) {  // skip the first interval
//                     interval.end = e.time;
//                     res.add(interval);
//                     interval = new Interval();
//                 }
//             } else {
//                 count--;
//                 if (count == 0) {
//                     interval.start = e.time;
//                 }
//             }
//         }
        
//         return res;
//     }
// }


// sweep line O(nlogK)time O(n)space  n is the number of all the events, k is the number of employees
class Solution {
    private class Job {
        public int eid;
        public int idx;
        
        public Job(int eid, int idx) {
            this.eid = eid;
            this.idx = idx;
        }
    }
    
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0 || 
            schedule.get(0) == null || schedule.get(0).size() == 0) {
            return new ArrayList<>();
        }
        
        PriorityQueue<Job> pq = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job a, Job b) {
                return schedule.get(a.eid).get(a.idx).start - schedule.get(b.eid).get(b.idx).start;
            }
        });
        
        int i = 0;
        for (List<Interval> employee : schedule) {
            pq.offer(new Job(i++, 0));
        }
        int end = schedule.get(pq.peek().eid).get(0).start;
        
        List<Interval> res = new ArrayList<>();
        
        while (!pq.isEmpty()) {
            Job job = pq.poll();
            Interval interval = schedule.get(job.eid).get(job.idx);  // get the current interval
            if (end < interval.start) {  //  there is a free time
                res.add(new Interval(end, interval.start));
            }
            end = Math.max(end, interval.end);  // end keep track of the latest ending time
            
            if (job.idx + 1 < schedule.get(job.eid).size()) {  // has next interval
                pq.offer(new Job(job.eid, job.idx + 1));
            }
            
        }
        
        return res;
    }
}