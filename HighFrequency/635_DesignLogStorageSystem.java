// // Ordered List
// class LogSystem {
//     private class Log {
//         public int id;
//         public String ts;
        
//         public Log(int id, String ts) {
//             this.id = id;
//             this.ts = ts;
//         }
//     }
    
//     private List<Log> logs;
//     private Map<String, Integer> map;

//     public LogSystem() {
//         this.logs = new LinkedList<>();
//         this.map = new HashMap<>();
//         map.put("Year", 4);
//         map.put("Month", 7);
//         map.put("Day", 10);
//         map.put("Hour", 13);
//         map.put("Minute", 16);
//         map.put("Second", 19);
//     }
    
//     public void put(int id, String timestamp) {
//         Log newLog = new Log(id, timestamp);
//         int i = logs.size() - 1;
//         while (i >= 0 && newLog.ts.compareTo(logs.get(i).ts) > 0) {
//             i--;
//         }
//         logs.add(i + 1, newLog);
//     }
    
//     public List<Integer> retrieve(String s, String e, String gra) {
//         List<Integer> res = new ArrayList<>();
        
//         for (Log log : logs) {
//             if (compare(log.ts, s, gra) >= 0 && compare(log.ts, e, gra) <= 0) {
//                 res.add(log.id);
//             }
//         }
        
//         return res;
//     }
    
//     private int compare(String a, String b, String gra) {
//         return a.substring(0, map.get(gra)).compareTo(b.substring(0, map.get(gra)));
//     }
// }

// /**
//  * Your LogSystem object will be instantiated and called as such:
//  * LogSystem obj = new LogSystem();
//  * obj.put(id,timestamp);
//  * List<Integer> param_2 = obj.retrieve(s,e,gra);
//  */


// Unordered List
class LogSystem {
    private class Log {
        public int id;
        public String ts;
        
        public Log(int id, String ts) {
            this.id = id;
            this.ts = ts;
        }
    }
    
    private List<Log> logs;
    private Map<String, Integer> map;

    public LogSystem() {
        this.logs = new LinkedList<>();
        this.map = new HashMap<>();
        map.put("Year", 4);
        map.put("Month", 7);
        map.put("Day", 10);
        map.put("Hour", 13);
        map.put("Minute", 16);
        map.put("Second", 19);
    }
    
    public void put(int id, String timestamp) {
        logs.add(new Log(id, timestamp));
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> res = new ArrayList<>();
        
        for (Log log : logs) {
            if (compare(log.ts, s, gra) >= 0 && compare(log.ts, e, gra) <= 0) {
                res.add(log.id);
            }
        }
        
        return res;
    }
    
    private int compare(String a, String b, String gra) {
        return a.substring(0, map.get(gra)).compareTo(b.substring(0, map.get(gra)));
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */