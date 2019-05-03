public class TwoSum {
    /**
     * @param number: An integer
     * @return: nothing
     */
     private Map<Integer, Integer> map;
     
     public TwoSum() {
         map = new HashMap<Integer, Integer>();
     }
     
    public void add(int number) {
        if (!map.containsKey(number)) {
            map.put(number, 0);
        }
        map.put(number, map.get(number) + 1);
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        for (int num : map.keySet()) {
            map.put(num, map.get(num) - 1);
            if (map.containsKey(value - num) && map.get(value - num) > 0) {
                return true;
            }
            map.put(num, map.get(num) + 1);
        }
        return false;
    }
}