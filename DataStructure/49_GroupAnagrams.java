// class Solution {
//     public List<List<String>> groupAnagrams(String[] strs) {
//         List<List<String>> res = new ArrayList<>();
        
//         if (strs == null || strs.length == 0) {
//             return res;
//         }
        
//         Map<String, List<String>> map = new HashMap<>();
        
//         for (String s : strs) {
//             char[] charArray = s.toCharArray();
//             Arrays.sort(charArray);
//             String sortedStr = String.valueOf(charArray);
//             map.putIfAbsent(sortedStr, new ArrayList<>());
//             map.get(sortedStr).add(s);
//         }
        
//         for (String key : map.keySet()) {
//             res.add(map.get(key));
//         }
        
//         return res;
//     }
// }


class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        
        if (strs == null || strs.length == 0) {
            return res;
        }
        
        Map<String, List<String>> map = new HashMap<>();
        int[] count = new int[26];
        
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }
            
            StringBuilder sb = new StringBuilder();
            for (int ct : count) {
                sb.append(ct);
                sb.append("#");
            }
            String countStr = sb.toString();
            
            map.putIfAbsent(countStr, new ArrayList<>());
            map.get(countStr).add(s);
        }
        
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        
        return res;
    }
}