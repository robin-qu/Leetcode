// /**
//  * Definition of Interval:
//  * public classs Interval {
//  *     int start, end;
//  *     Interval(int start, int end) {
//  *         this.start = start;
//  *         this.end = end;
//  *     }
//  * }
//  */

// public class Solution {
//     /**
//      * @param airplanes: An interval array
//      * @return: Count of airplanes are in the sky.
//      */
//     class Flight implements Comparable<Flight> {
//         public int time;
//         public boolean start;
        
//         public Flight(int time, boolean start) {
//             this.time = time;
//             this.start = start;
//         }
        
//         @Override
//         public int compareTo(Flight other) {
//             return this.time - other.time;
//         }
//     }
    
//     public int countOfAirplanes(List<Interval> airplanes) {
//         if (airplanes == null || airplanes.size() == 0) {
//             return 0;
//         }
        
//         List<Flight> schedule = new ArrayList<>();
//         for (Interval airplane : airplanes) {
//             schedule.add(new Flight(airplane.start, true));
//             schedule.add(new Flight(airplane.end, false));
//         }
        
//         Collections.sort(schedule);
        
        
//         int max = 0;
//         int curr = 0;
//         int idx = 0;
//         int size = schedule.size();
//         while (idx < size) {
//             int time = schedule.get(idx).time;
//             while (idx < size && time == schedule.get(idx).time) {
//                 if (!schedule.get(idx).start) {
//                     curr--;
//                 } else {
//                     curr++;
//                 }
//                 idx++;
//             }
            
//             max = Math.max(max, curr);
//         }
        
//         return max;
//     }
// }


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
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    class Flight implements Comparable<Flight> {
        public int time;
        public int start; // 1 is taking off, 0 is touching down
        
        public Flight(int time, int start) {
            this.time = time;
            this.start = start;
        }
        
        @Override
        public int compareTo(Flight other) {
            if (this.time != other.time) {
                return this.time - other.time;
            } else {
                return this.start - other.start;
            }
        }
    }
    
    public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        
        List<Flight> schedule = new ArrayList<>();
        for (Interval airplane : airplanes) {
            schedule.add(new Flight(airplane.start, 1));
            schedule.add(new Flight(airplane.end, 0));
        }
        
        Collections.sort(schedule);
        
        
        int max = 0;
        int curr = 0;
        
        for (Flight f : schedule) {
            if (f.start == 1) {
                curr++;
            } else {
                curr--;
            }
            
            max = Math.max(max, curr);
        }
        
        return max;
    }
}