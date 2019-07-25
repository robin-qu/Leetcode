class Solution {
    public String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) {
            return new String[0];
        }
        
        Arrays.sort(logs, new LogsComparator());
        
        return logs;
    }
    
    class LogsComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            String[] ss1 = s1.split(" ");
            String[] ss2 = s2.split(" ");
            
            if (isDigit(ss1[1]) && isDigit(ss2[1])) {  // consider two number logs equal
                return 0;
            }
            
            // letter logs are "smaller" than number logs
            if (isDigit(ss1[1])) {
                return 1;
            }
            
            if (isDigit(ss2[1])) {
                return -1;
            }
            
            // compare two letter log
            for (int i = 1; i < Math.min(ss1.length, ss2.length); i++) {
                if (!ss1[i].equals(ss2[i])) {
                    return ss1[i].compareTo(ss2[i]);
                }
            }
            
            // the longer one is "bigger"
            if (ss1.length != ss2.length) {
                return ss1.length - ss2.length;
            }
            
            // break tie by the identifier
            return ss1[0].compareTo(ss2[0]);
        }
    }
    
    private boolean isDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}