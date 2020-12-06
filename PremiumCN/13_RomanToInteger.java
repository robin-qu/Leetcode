class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;
        char[] ss = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            res += map.get(ss[i]);
            if (i > 0) {
                if (map.get(ss[i]) > map.get(ss[i - 1])) {
                    res -= 2 * map.get(ss[i - 1]);
                }
            }
        }

        return res;
    }
}