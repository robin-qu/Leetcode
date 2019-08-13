// // Sweep line  O(nlogn)time  O(n)space
// class Solution {
//     class Event implements Comparable<Event> {
//         public int time;
//         public int start;
        
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
    
//     public int[][] insert(int[][] intervals, int[] newInterval) {
//         if (intervals == null) {
//             return new int[0][0];
//         }
        
//         int n = intervals.length;
//         Event[] events = new Event[2 * n + 2];
        
//         for (int i = 0; i < intervals.length; i++) {
//             events[2 * i] = new Event(intervals[i][0], 0);
//             events[2 * i + 1] = new Event(intervals[i][1], 1);
//         }
        
//         events[2 * n] = new Event(newInterval[0], 0);
//         events[2 * n + 1] = new Event(newInterval[1], 1);
        
//         Arrays.sort(events);
        
//         List<Integer> list = new ArrayList<>();
//         int count = 0;
//         for (int i = 0; i < events.length; i++) {
//             if (events[i].start == 0) {
//                 count++;
//                 if (count == 1) {
//                     list.add(events[i].time);
//                 }
//             }
            
//             if (events[i].start == 1) {
//                 count--;
//                 if (count == 0) {
//                     list.add(events[i].time);
//                 }
//             }
//         }
        
//         int[][] res = new int[list.size() / 2][2];
//         for (int i = 0; i < res.length; i++) {
//             res[i][0] = list.get(2 * i);
//             res[i][1] = list.get(2 * i + 1);
//         }
        
//         return res;
//     }
// }


// // O(n)time  O(n)space
// class Solution {    
//     public int[][] insert(int[][] intervals, int[] newInterval) {
//         if (intervals == null) {
//             return new int[0][0];
//         }
        
//         int n = intervals.length;
//         List<Integer> list = new ArrayList<>();
        
//         int i = 0;
//         // add all intervals that ends before the newInterval into the list
//         while (i < n && intervals[i][1] < newInterval[0]) {
//             list.add(intervals[i][0]);
//             list.add(intervals[i][1]);
//             i++;
//         }
        
//         // check whether the next interval starts early than new interval
//         if (i < n && intervals[i][0] < newInterval[0]) {
//             list.add(intervals[i][0]);
//         } else {
//             list.add(newInterval[0]);
//         }
        
//         // fint the interval that ends later than new interval
//         while (i < n && intervals[i][1] <= newInterval[1]) {
//             i++;
//         }
//         if (i < n && intervals[i][0] <= newInterval[1]) {
//             list.add(intervals[i][1]);
//         } else if (i < n) {
//             list.add(newInterval[1]);
//             list.add(intervals[i][0]);
//             list.add(intervals[i][1]);
//         } else {
//             list.add(newInterval[1]);
//         }
        
//         // add the subsequent intervals
//         while (i + 1 < n) {
//             i++;
//             list.add(intervals[i][0]);
//             list.add(intervals[i][1]);
//         }
        
//         // convert result
//         int[][] res = new int[list.size() / 2][2];
//         for (int j = 0; j < res.length; j++) {
//             res[j][0] = list.get(2 * j);
//             res[j][1] = list.get(2 * j + 1);
//         }
        
//         return res;
//     }
// }


// O(n)time  O(n)space
class Solution {    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null) {
            return new int[0][0];
        }
        
        int n = intervals.length;
        List<Integer> list = new ArrayList<>();
        
        int i = 0;
        // add all intervals that ends before the newInterval into the list
        while (i < n && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i][0]);
            list.add(intervals[i][1]);
            i++;
        }
        
        // merge intervals that has overlap with newinterval
        int start = newInterval[0];
        int end = newInterval[1];
        while (i < n && intervals[i][0] <= newInterval[1]) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        list.add(start);
        list.add(end);        
        
        // add the subsequent intervals
        while (i < n) {
            list.add(intervals[i][0]);
            list.add(intervals[i][1]);
            i++;
        }
        
        // convert result
        int[][] res = new int[list.size() / 2][2];
        for (int j = 0; j < res.length; j++) {
            res[j][0] = list.get(2 * j);
            res[j][1] = list.get(2 * j + 1);
        }
        
        return res;
    }
}