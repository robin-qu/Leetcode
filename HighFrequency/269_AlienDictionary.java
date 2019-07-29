// BFS Topological Sort O(m + n)
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        if (words.length == 1) {
            return words[0];
        }
        
        Map<Character, List<Character>> graph = buildGraph(words);
        Map<Character, Integer> income = getIncome(graph);
        StringBuilder sb = new StringBuilder();
        
        Queue<Character> queue = new LinkedList<>();
        for (char c : income.keySet()) {
            if (income.get(c) == 0) {
                queue.offer(c);
            }
        }
        
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            
            for (char neighbor : graph.get(c)) {
                income.put(neighbor, income.get(neighbor) - 1);
                if (income.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        return sb.length() == graph.size() ? sb.toString() : "";
    }
    
    private Map<Character, Integer> getIncome(Map<Character, List<Character>> graph) {
        Map<Character, Integer> map = new HashMap<>();
        for (char key : graph.keySet()) {
            map.put(key, 0);
        }
        
        for (char key : graph.keySet()) {
            for (char c : graph.get(key)) {
                map.put(c, map.get(c) + 1);
            }
        }
        
        return map;
    }
    
    private Map<Character, List<Character>> buildGraph(String[] words) {
        Map<Character, List<Character>> map = new HashMap<>();
        int n = words.length;
        
        for (int i = 1; i < n; i++) {
            String w1 = words[i - 1];
            String w2 = words[i];
            int n1 = w1.length();
            int n2 = w2.length();
            
            for (int j = 0; j < n1; j++) {
                char c1 = w1.charAt(j);
                if (!map.containsKey(c1)) {
                    map.put(c1, new ArrayList<>());
                }
            }
            
            for (int j = 0; j < n2; j++) {
                char c2 = w2.charAt(j);
                if (!map.containsKey(c2)) {
                    map.put(c2, new ArrayList<>());
                }
            }

            for (int j = 0; j < Math.min(n1, n2); j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    map.get(c1).add(c2);
                    break;
                }
            }
        }
        
        return map;
    }
}