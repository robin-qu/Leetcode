// hashmap O(1)time O(1)space
class Logger {
    private Set[] map;
    private int[] time;

    /** Initialize your data structure here. */
    public Logger() {
        this.map = new Set[10];
        for (int i = 0; i < 10; i++) {
            map[i] = new HashSet<>();
        }
        this.time = new int[10];
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        for (int i = 0; i < 10; i++) {
            if (timestamp - time[i] < 10) {
                if (map[i].contains(message)) {
                    return false;
                }
            }
        }
        
        int idx = timestamp % 10;
        if (time[idx] != timestamp) {
            time[idx] = timestamp;
            map[idx] = new HashSet<>();
        }
        map[idx].add(message);
        
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */



// // hashmap O(1)time O(n)space
// class Logger {
//     private Map<String, Integer> map;

//     /** Initialize your data structure here. */
//     public Logger() {
//         this.map = new HashMap<>();
//     }
    
//     /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
//         If this method returns false, the message will not be printed.
//         The timestamp is in seconds granularity. */
//     public boolean shouldPrintMessage(int timestamp, String message) {
//         if (map.containsKey(message) && timestamp < map.get(message)) {
//             return false;
//         }
        
//         map.put(message, timestamp + 10);
//         return true;
//     }
// }

// /**
//  * Your Logger object will be instantiated and called as such:
//  * Logger obj = new Logger();
//  * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
//  */