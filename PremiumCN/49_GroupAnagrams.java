class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] counts = new int[26];
            for (char c : s.toCharArray()) {
                counts[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int count : counts) {
                sb.append(count).append("#");
            }
            List<String> list = map.computeIfAbsent(sb.toString(), k -> new ArrayList<>());
            list.add(s);
        }
        return new ArrayList<>(map.values()); 
    }
}