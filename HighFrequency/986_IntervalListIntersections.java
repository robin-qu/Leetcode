// // Sweep Line O((m + n)log(m + n))time O(m + n)space
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
    
//     public int[][] intervalIntersection(int[][] A, int[][] B) {
//         if (A == null || B == null) {
//             return new int[0][0];
//         }
        
//         int m = A.length;
//         int n = B.length;
//         Event[] events = new Event[2 * m + 2 * n];
        
//         for (int i = 0; i < m; i++) {
//             events[2 * i] = new Event(A[i][0], 0);
//             events[2 * i + 1] = new Event(A[i][1], 1);
//         }
        
//         for (int i = 0; i < n; i++) {
//             events[2 * m + 2 * i] = new Event(B[i][0], 0);
//             events[2 * m + 2 * i + 1] = new Event(B[i][1], 1);
//         }
        
//         Arrays.sort(events);
//         List<Integer> temp = new ArrayList<>();
//         int count = 0;
//         for (Event e : events) {
//             if (e.status == 0) {
//                 count++;
//                 if (count == 2) {
//                     temp.add(e.time);
//                 }
//             } else {
//                 count--;
//                 if (count == 1) {
//                     temp.add(e.time);
//                 }
//             }
//         }
        
//         int[][] res = new int[temp.size() / 2][2];
//         for (int i = 0; i < temp.size() / 2; i++) {
//             res[i][0] = temp.get(2 * i);
//             res[i][1] = temp.get(2 * i + 1);
//         }
        
//         return res;
//     }
// }


// Sweep Line O(m + n)time O(m + n)space
class Solution {    
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null) {
            return new int[0][0];
        }
        
        int m = A.length;
        int n = B.length;
        int a = 0;
        int b = 0;
        List<Integer> temp = new ArrayList<>();
        
        while (a < m && b < n) {
            if (A[a][1] < B[b][0]) { // no intersection
                a++;
            } else if (B[b][1] < A[a][0]) {  // no intersection
                b++;
            } else {
                temp.add(Math.max(A[a][0], B[b][0]));
                temp.add(Math.min(A[a][1], B[b][1]));
                if (A[a][1] < B[b][1]) {
                    a++;
                } else {
                    b++;
                }
            }
        }
        
        int[][] res = new int[temp.size() / 2][2];
        for (int i = 0; i < temp.size() / 2; i++) {
            res[i][0] = temp.get(2 * i);
            res[i][1] = temp.get(2 * i + 1);
        }
        
        return res;
    }
}