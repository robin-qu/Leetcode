// class Solution {
//     class Event implements Comparable<Event> {
//         public int time;
//         public int isEnd;
        
//         public Event(int time, int isEnd) {
//             this.time = time;
//             this.isEnd = isEnd;
//         }
        
//         @Override
//         public int compareTo(Event other) {
//             if (this.time != other.time) {
//                 return this.time - other.time;
//             }
//             return other.isEnd - this.isEnd;
//         }
//     }
//     public int minMeetingRooms(int[][] intervals) {
//         if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
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
//         int curr = 0;
//         for (Event event : events) {
//             if (event.isEnd == 0) {
//                 curr++;
//                 res = Math.max(res, curr);
//             } else {
//                 curr--;
//             }
//         }
        
//         return res;
//     }
// }


class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
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
        
        int res = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (starts[i] < ends[j]) {
                res++;
            } else {
                j++;
            }
        }
        
        return res;
    }
}