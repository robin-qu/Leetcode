// class Solution {
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         if (wordList == null || wordList.size() == 0) {
//             return 0;
//         } 
        
//         Set<String> wordSet = new HashSet<>();
//         for (String word : wordList) {
//             wordSet.add(word);
//         }
        
//         if (!wordSet.contains(endWord)) {
//             return 0;
//         }
        
//         Queue<String> queue = new LinkedList<>();
//         queue.add(beginWord);
//         int res = 1;
//         Set<String> visited = new HashSet<>();
//         visited.add(beginWord);
        
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             res++;
            
//             for (int i = 0; i < size; i++) {
//                 String curr = queue.poll();

//                 for (String word : wordSet) {
//                     if (!visited.contains(word) && isNeighbor(curr, word)) {
//                         if (word.equals(endWord)) {
//                             return res;
//                         }
//                         queue.add(word);
//                         visited.add(word);
//                     }
//                 }
//             }
            
//         }
        
//         return 0;
//     }
    
//     private boolean isNeighbor(String curr, String word) {
//         int diff = 0;
        
//         for (int i = 0; i < curr.length(); i++) {
//             if (curr.charAt(i) != word.charAt(i)) {
//                 diff++;
//             }
//         }
        
//         return diff == 1;
//     }
// }


// class Solution {
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         if (wordList == null || wordList.size() == 0) {
//             return 0;
//         } 
        
//         Set<String> wordSet = new HashSet<>();
//         for (String word : wordList) {
//             wordSet.add(word);
//         }
        
//         if (!wordSet.contains(endWord)) {
//             return 0;
//         }
        
//         Queue<String> queue = new LinkedList<>();
//         queue.add(beginWord);
//         int res = 1;
//         Set<String> visited = new HashSet<>();
//         visited.add(beginWord);
        
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             res++;
            
//             for (int i = 0; i < size; i++) {
//                 String curr = queue.poll();

//                 for (String word : getNeighbors(curr, wordSet)) {
//                     if (!visited.contains(word)) {
//                         if (word.equals(endWord)) {
//                             return res;
//                         }
//                         queue.offer(word);
//                         visited.add(word);
//                     }
//                 }
//             }
            
//         }
        
//         return 0;
//     }
    
//     private Set<String> getNeighbors(String word, Set<String> wordSet) {
//         Set<String> res = new HashSet<>();
        
//         for (int i = 0; i < word.length(); i++) {
//             StringBuilder sb = new StringBuilder(word);
//             for (char c = 'a'; c <= 'z'; c++) {
//                 sb.setCharAt(i, c);
//                 String temp = sb.toString();

//                 if (wordSet.contains(temp)) {
//                     res.add(temp);
//                 }
//             }   
//         }
        
//         return res;
//     }
// }


// Bidirectional bfs
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return 0;
        } 
        
        Set<String> wordSet = new HashSet<>();
        for (String word : wordList) {
            wordSet.add(word);
        }
        
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        Queue<String> leftQueue = new LinkedList<>();
        leftQueue.add(beginWord);
        int leftLen = 1;
        Set<String> leftVisited = new HashSet<>();
        leftVisited.add(beginWord);
        
        Queue<String> rightQueue = new LinkedList<>();
        rightQueue.add(endWord);
        int rightLen = 1;
        Set<String> rightVisited = new HashSet<>();
        rightVisited.add(endWord);
        
        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            int leftSize = leftQueue.size();
            leftLen++;
            
            for (int i = 0; i < leftSize; i++) {
                String curr = leftQueue.poll();

                for (String word : getNeighbors(curr, wordSet)) {
                    if (rightVisited.contains(word)) {
                        return leftLen + rightLen - 1;
                    }
                    
                    if (!leftVisited.contains(word)) {
                        leftQueue.offer(word);
                        leftVisited.add(word);
                    }
                }
            }
            
            int rightSize = rightQueue.size();
            rightLen++;
            
            for (int i = 0; i < rightSize; i++) {
                String curr = rightQueue.poll();
                
                for (String word : getNeighbors(curr, wordSet)) {
                    if (leftVisited.contains(word)) {
                        return leftLen + rightLen - 1;
                    }
                    
                    if (!rightVisited.contains(word)) {
                        rightQueue.offer(word);
                        rightVisited.add(word);
                    }
                }
            }
        }
        
        return 0;
    }
    
    private Set<String> getNeighbors(String word, Set<String> wordSet) {
        Set<String> res = new HashSet<>();
        
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
}