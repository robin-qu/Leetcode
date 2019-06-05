public class TwoSum {
    /**
     * @param number: An integer
     * @return: nothing
     */
     
    private Map<Integer, Integer> map;
    
    public TwoSum() {
        this.map = new HashMap<Integer, Integer>();
    }
    
    public void add(int number) {
        if (!map.containsKey(number)) {
            map.put(number, 1);
        } else {
            map.put(number, map.get(number) + 1);
        }
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        for (int n : map.keySet()) {
            if (map.containsKey(value - n)) {
                if (value - n == n && map.get(n) > 1) {
                    return true;
                }
                if (value - n != n) {
                    return true;
                }
            }
        }
        
        return false;
    }
}