class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int res = 0;
        char[] ss = s.toCharArray();
        Map<Character, Integer> map = buildMap();
        
        for (int i = 0; i < n - 1; i++) {
            if (map.get(ss[i]) < map.get(ss[i + 1])) {
                res -= map.get(ss[i]);
            } else {
                res += map.get(ss[i]);
            }
        }
        
        return res + map.get(ss[n - 1]);
    }
    
    private Map<Character, Integer> buildMap() {
        Map<Character, Integer> map = new HashMap<>();
        
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        return map;
    }
}