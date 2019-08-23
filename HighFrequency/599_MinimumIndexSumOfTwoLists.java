// Hash O(n1 + n2)time O(n1)space
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1 == null || list1.length == 0 ||
            list2 == null || list2.length == 0) {
            return new String[0];
        }
        
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        
        List<String> resList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (!map.containsKey(list2[i])) {
                continue;
            }
            
            if (i + map.get(list2[i]) < min) {
                resList = new ArrayList<>();
                resList.add(list2[i]);
                min = i + map.get(list2[i]);
            } else if (i + map.get(list2[i]) == min) {
                resList.add(list2[i]);
            }
        }
        
        String[] res = new String[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        
        return res;
    }
}