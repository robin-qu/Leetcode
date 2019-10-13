// // Sweep Line O(nlogn)time O(n)space
// class Solution {
//     class Event implements Comparable<Event> {
//         public int time;
//         public int start;  // 0 is end, 1 is start
        
//         public Event(int time, int start) {
//             this.time = time;
//             this.start = start;
//         }
        
//         @Override
//         public int compareTo(Event other) {
//             if (this.time != other.time) {
//                 return this.time - other.time;
//             }
            
//             return this.start - other.start;
//         }
//     }
    
//     public int minMeetingRooms(int[][] intervals) {
//         if (intervals == null || intervals.length == 0 ||
//             intervals[0] == null || intervals[0].length != 2) {
//             return 0;
//         }
        
//         int n = intervals.length;
//         Event[] events = new Event[2 * n];
//         for (int i = 0; i < n; i++) {
//             events[2 * i] = new Event(intervals[i][0], 1);
//             events[2 * i + 1] = new Event(intervals[i][1], 0);
//         }
        
//         Arrays.sort(events);
        
//         int max = 0;
//         int count = 0;
//         for (Event e : events) {
//             if (e.start == 1) {
//                 count++;
//             } else {
//                 count--;
//             }
//             max = Math.max(max, count);
//         }
        
//         return max;
//     }
// }


// // Sweep Line O(nlogn)time O(n)space
// class Solution {
//     class Event implements Comparable<Event> {
//         public int time;
//         public int status;  // 0 is start, 1 is end
        
//         public Event(int time, int status) {
//             this.time = time;
//             this.status = status;
//         }
        
//         @Override
//         public int compareTo(Event other) {
//             if (this.time != other.time) {
//                 return this.time - other.time;
//             }
            
//             return other.status - this.status;
//         }
//     }
    
//     public int minMeetingRooms(int[][] intervals) {
//         if (intervals == null || intervals.length == 0 ||
//             intervals[0] == null || intervals[0].length != 2) {
//             return 0;
//         }
        
//         int n = intervals.length;
//         Event[] events = new Event[2 * n];
        
//         for (int i = 0; i < n; i++) {
//             events[2 * i] = new Event(intervals[i][0], 0);
//             events[2 * i + 1] = new Event(intervals[i][1], 1);
//         }
        
//         Arrays.sort(events);
        
//         int res = 0;
//         int count = 0;
//         for (Event event : events) {
//             if (event.status == 0) {
//                 count++;
//             } else 
//                 count--;
//             }
            
//             res = Math.max(res, count);
//         }
        
//         return res;
//     }
// }

// Sweep Line O(nlogn)time O(n)space
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 ||
            intervals[0] == null || intervals[0].length != 2) {
            return 0;
        }
        
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int max = 0;
        int count = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (starts[i] < ends[j]) {
                count++;
            } else {
                j++;
            }
            
            max = Math.max(max, count);
        }
        
        return max;
    }
}