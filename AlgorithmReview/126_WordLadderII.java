class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        
        if (wordList == null || wordList.size() == 0 || 
            !wordList.contains(endWord)) {
            return res;
        }
        
        Map<String, Integer> mapping = new HashMap<>();
        
        for (String word : wordList) {
            mapping.put(word, Integer.MAX_VALUE);
        }
        mapping.put(beginWord, Integer.MAX_VALUE);
        
        bfs(endWord, mapping);
        
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, mapping, path, res);
        
        return res;
    }
    
    private void bfs(String endWord, Map<String, Integer> mapping) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(endWord);
        Set<String> visited = new HashSet<>();
        visited.add(endWord);
        int distance = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                mapping.put(curr, distance);
                
                for (String neighbor : getNeighbors(curr, mapping)) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            
            distance++;
        }
    }
    
    private List<String> getNeighbors(String curr, 
                                      Map<String, Integer> mapping) {
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < curr.length(); i++) {
            StringBuilder sb = new StringBuilder(curr);
            for (char c = 'a'; c <= 'z'; c++) {
                sb.setCharAt(i, c);
                String temp = sb.toString();
                if (mapping.containsKey(temp) && !temp.equals(curr)) {
                    res.add(temp);
                }
            }
        }
        
        return res;
    }
    
    private void dfs(String start,
                     String endWord,
                     Map<String, Integer> mapping,
                     List<String> path,
                     List<List<String>> res) {
        if (start.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for (String neighbor : getNeighbors(start, mapping)) {
            if (mapping.get(neighbor) != mapping.get(start) - 1) {
                continue;
            }
            path.add(neighbor);
            dfs(neighbor, endWord, mapping, path, res);
            path.remove(path.size() - 1);
        }
    }
}