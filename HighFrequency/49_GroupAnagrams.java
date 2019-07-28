// // Sort + Hash  O(nllogl)time O(n)space
// class Solution {
//     public List<List<String>> groupAnagrams(String[] strs) {
//         if (strs == null || strs.length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = strs.length;
        
//         Map<String, List<String>> map = new HashMap<>();
        
//         for (String str : strs) {
//             char[] cc = str.toCharArray();
//             Arrays.sort(cc);
//             StringBuilder sb = new StringBuilder();
//             for (char c : cc) {
//                 sb.append(c);
//             }
//             String s = sb.toString();
            
//             if (!map.containsKey(s)) {
//                 map.put(s, new ArrayList<>());
//             }
//             map.get(s).add(str);
//         }
        
//         List<List<String>> res = new ArrayList<>();
//         for (String key : map.keySet()) {
//             res.add(map.get(key));
//         }
        
//         return res;
//     }
// }


// Hash  O(nl)time O(n)space
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        int n = strs.length;
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            int[] counts = new int[26];
            char[] cc = str.toCharArray();
            for (char c : cc) {
                counts[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int count : counts) {
                sb.append(count);
                sb.append(' ');
            }
            String s = sb.toString();
            
            if (!map.containsKey(s)) {
                map.put(s, new ArrayList<>());
            }
            map.get(s).add(str);
        }
        
        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        
        return res;
    }
}