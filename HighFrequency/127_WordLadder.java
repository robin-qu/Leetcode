// BFS O(nl)time O(nl)space
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return 0;
        }
        
        Set<String> words = new HashSet<>();
        for (String word : wordList) {
            words.add(word);
        }
        
        if (!words.contains(endWord)) {
            return 0;
        }
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        int len = beginWord.length();
        int res = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(endWord)) {
                    return res;
                }
                
                for (int j = 0; j < len; j++) {
                    char[] cc = curr.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        cc[j] = c;
                        String s = String.valueOf(cc);
                        if (words.contains(s) && !visited.contains(s)) {
                            queue.offer(s);
                            visited.add(s);
                        }
                    }
                }
            }
        }
        
        return 0;
    }
}