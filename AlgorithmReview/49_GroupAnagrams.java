class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            String hashCode = hash(s);
            if (!map.containsKey(hashCode)) {
                map.put(hashCode, new ArrayList<>());
            }
            map.get(hashCode).add(s);
        }
        
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        
        return res;
    }
    
    private String hash(String s) {
        String hashCode = "";
        char[] counts = new char[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < 26; i++) {
            hashCode += counts[i] + "#";
        }
        
        return hashCode;
    }
}