class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return new ArrayList<>();
        }
        
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return new ArrayList<>();
        }
        
        List<List<String>> res = new ArrayList<>();
        Map<String, Integer> map = bfs(beginWord, words);  // word -> distance
        dfs(beginWord, endWord, 1, map, new ArrayList<>(), res);
        
        return res;
    }
    
    private Map<String, Integer> bfs(String beginWord, Set<String> words) {
        int len = beginWord.length();
        Map<String, Integer> map = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        map.put(beginWord, 1);
        
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int dist = map.get(curr);
            
            for (int i = 0; i < len; i++) {
                char[] ss = curr.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    ss[i] = c;
                    String neighbor = String.valueOf(ss);
                    if (words.contains(neighbor) && !visited.contains(neighbor)) {
                        map.put(neighbor, dist + 1);
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        
        return map;
    }
    
    private void dfs(String word, String endWord, int dist, Map<String, Integer> map, List<String> list, List<List<String>> res) {
        if (word.equals(endWord)) {
            list.add(endWord);
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        
        list.add(word);
        for (int i = 0; i < word.length(); i++) {
            char[] ss = word.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                ss[i] = c;
                String neighbor = String.valueOf(ss);
                if (map.containsKey(neighbor) && map.get(neighbor) == dist + 1) {
                    dfs(neighbor, endWord, dist + 1, map, list, res);
                }
            }
        }
        list.remove(list.size() - 1);
    }
}