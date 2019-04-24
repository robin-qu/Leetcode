class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) {
            return res;
        }
        
        Map<String, Integer> distances = new HashMap<>();
        Set<String> wordSet = new HashSet<String>(wordList);
        Map<String, List<String>> graph = new HashMap<>();
        
        wordSet.add(beginWord);
        bfs(beginWord, endWord, wordSet, distances, graph);
        
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, path, res, distances, graph);
        return res;        
    }
    
    private void bfs(String beginWord,
                     String endWord,
                     Set<String> wordSet,
                     Map<String, Integer> distances, 
                     Map<String, List<String>> graph) {
        for (String word : wordSet) {
            graph.put(word, new ArrayList<>());
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        distances.put(beginWord, 0);
        
        while (!queue.isEmpty()) {
            String curr = queue.remove();
            int currDist = distances.get(curr);

            for (String neighbor : getNeighbors(curr, wordSet)) {
                graph.get(curr).add(neighbor);
                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, currDist + 1);
                    queue.add(neighbor);
                }
            }
        }
    }    
    
    // Returns a list of neighbors of the given word
    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            for (char c = 'a'; c <= 'z'; c++) {
                sb.setCharAt(i, c);
                String temp = sb.toString();
                if (wordSet.contains(temp)) {
                    res.add(temp);
                }
            }
        }
        
        return res;
    }
    
    private void dfs(String curr,
                     String endWord,
                     List<String> path,
                     List<List<String>> res,
                     Map<String, Integer> distances, 
                     Map<String, List<String>> graph) {
        
        if (endWord.equals(curr)) {
            res.add(new ArrayList<String>(path));
            return;
        } 
        
        for (String word : graph.get(curr)) {
            if (distances.get(word) == distances.get(curr) + 1) {
                path.add(word);
                dfs(word, endWord, path, res, distances, graph);
                path.remove(path.size() - 1);
            }
        }
    }
}