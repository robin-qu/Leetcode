// // Sweep Line O(nlogn)time O(n)space
// class Solution {
//     class Event implements Comparable<Event> {
//         public int pos;
//         public int end;  // 0 is start, 1 is end
        
//         public Event(int pos, int end) {
//             this.pos = pos;
//             this.end = end;
//         }
        
//         @Override
//         public int compareTo(Event other) {
//             if (this.pos != other.pos) {
//                 return this.pos - other.pos;
//             }
            
//             return this.end - other.end;
//         }
//     }
    
//     public int[][] merge(int[][] intervals) {
//         if (intervals == null || intervals.length == 0 ||
//             intervals[0] == null || intervals[0].length != 2) {
//             return new int[0][0];
//         }
        
//         int n = intervals.length;
//         Event[] events = new Event[2 * n];
//         for (int i = 0; i < n; i++) {
//             events[2 * i] = new Event(intervals[i][0], 0);
//             events[2 * i + 1] = new Event(intervals[i][1], 1);
//         }
        
//         Arrays.sort(events);
//         List<Integer> temp = new ArrayList<>();
        
//         int status = 0;  // status is the number of intervals that we are currently in
//         for (int i = 0; i < 2 * n; i++) {
//             if (events[i].end == 0) {
//                 status++;
//                 if (status == 1) {  // start of an interval
//                     temp.add(events[i].pos);
//                 }
//             } else {
//                 status--;
//                 if (status == 0) {  // end of an interval
//                     temp.add(events[i].pos);
//                 }
//             }
//         }
        
//         int len = temp.size();  // len must be an even number
//         int[][] res = new int[len / 2][2];
//         for (int i = 0; i < len / 2; i++) {
//             res[i][0] = temp.get(2 * i);
//             res[i][1] = temp.get(2 * i + 1);
//         }
        
//         return res;
//     }
// } 


// // Sweep Line without define a new class O(nlogn)time O(1)space
// class Solution {    
//     public int[][] merge(int[][] intervals) {
//         if (intervals == null || intervals.length == 0 ||
//             intervals[0] == null || intervals[0].length != 2) {
//             return new int[0][0];
//         }
        
//         int n = intervals.length;
        
//         Arrays.sort(intervals, new Comparator<int[]>() {
//             @Override
//             public int compare(int[] a, int[] b) {
//                 return a[0] - b[0];
//             }
//         });
        
//         List<List<Integer>> list = new ArrayList<>();
//         int endTime = Integer.MIN_VALUE;
//         for (int i = 0; i < n; i++) {
//             if (intervals[i][0] > endTime) {
//                 List<Integer> interval = new ArrayList<>();
//                 interval.add(intervals[i][0]);
//                 interval.add(intervals[i][1]);
//                 list.add(interval);
//                 endTime = intervals[i][1];
//             } else {
//                 endTime = Math.max(endTime, intervals[i][1]);
//                 list.get(list.size() - 1).set(1, endTime);
//             }
//         }
        
//         int[][] res = new int[list.size()][2];
//         for (int i = 0; i < list.size(); i++) {
//             res[i][0] = list.get(i).get(0);
//             res[i][1] = list.get(i).get(1);
//         }
        
//         return res;
//     }
// } 



// // Sweep Line O(nlogn)time O(1)space
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
            
//             return this.status - other.status;
//         }
//     }
    
//     public int[][] merge(int[][] intervals) {
//         if (intervals == null || intervals.length == 0 ||
//             intervals[0] == null || intervals[0].length != 2) {
//             return new int[0][0];
//         }
        
//         int n = intervals.length;
//         Event[] events = new Event[2 * n];
//         for (int i = 0; i < n; i++) {
//             events[2 * i] = new Event(intervals[i][0], 0);
//             events[2 * i + 1] = new Event(intervals[i][1], 1);
//         }
        
//         Arrays.sort(events);
//         List<Integer> merged = new ArrayList<>();
//         int count = 0;
//         for (Event event : events) {
//             if (event.status == 0) {
//                 if (count == 0) {
//                     merged.add(event.time);
//                 }
//                 count++;
//             } else {
//                 if (count == 1) {
//                     merged.add(event.time);
//                 }
//                 count--;
//             }
//         }
        
//         int[][] res = new int[merged.size() / 2][2];
//         for (int i = 0; i < merged.size() / 2; i++) {
//             res[i][0] = merged.get(2 * i);
//             res[i][1] = merged.get(2 * i + 1);
//         }
        
//         return res;
//     }
// } 


// Sweep Line without define a new class O(nlogn)time O(1)space
class Solution {    
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 ||
            intervals[0] == null || intervals[0].length != 2) {
            return new int[0][0];
        }
        
        int n = intervals.length;
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        
        List<Integer> merged = new ArrayList<>();
        int end = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (intervals[i][0] > end) {
                if (i != 0) {
                    merged.add(end);
                }
                merged.add(intervals[i][0]);
            }
            end = Math.max(end, intervals[i][1]);
        }
        merged.add(end);
        
        int[][] res = new int[merged.size() / 2][2];
        for (int i = 0; i < merged.size() / 2; i++) {
            res[i][0] = merged.get(2 * i);
            res[i][1] = merged.get(2 * i + 1);
        }
        
        return res;
    }
} 