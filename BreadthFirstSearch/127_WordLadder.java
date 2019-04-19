// class Solution {
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         if (wordList.size() == 0 || !wordList.contains(endWord)) {
//             return 0;
//         }
        
//         Set<String> wordSet = new HashSet<String>(wordList);
//         Queue<String> queue = new LinkedList<>();
//         int length = 0;
//         queue.add(beginWord);
        
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             length++;
            
//             while (size-- > 0) {
//                 String curr = queue.remove();

//                 if (curr.equals(endWord)) {
//                     return length;
//                 }
                
//                 for (int i = 0; i < curr.length(); i++) {
//                     StringBuilder sb = new StringBuilder(curr);
//                     for (char c = 'a'; c <= 'z'; c++) {
//                         sb.setCharAt(i, c);
//                         String temp = sb.toString();
//                         if (wordSet.contains(temp)) {
//                             queue.add(temp);
//                             wordSet.remove(temp);
//                         }
//                     }
//                 }
//             }
//         }
        
//         return 0;
//     }
    
// //     // Returns a list of words in wordList which has only one different
// //     // letter with the given word
// //     private List<String> findNeighbor(String word, List<String> wordList) {
// //         List<String> res = new ArrayList<>();
// //         for (String w : wordList) {
// //             if (isNeighbor(word, w)) {
// //                 res.add(w);
// //             }
// //         }
// //         return res;
// //     }
    
// //     // Returns whether w1 only has one different letter with w2
// //     // Pre: w1 and w2 are non null and have the same length
// //     private boolean isNeighbor(String w1, String w2) {
// //         int len = w1.length();
// //         int count = 0;
// //         for (int i = 0; i < len; i++) {
// //             if (w1.charAt(i) != w2.charAt(i)) {
// //                 count++;
// //             }
// //         }
// //         return count == 1;
// //     }
// }


class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }
        
        // Set up front variables
        List<String> visitedFront = new ArrayList<>();
        Queue<String> queueFront = new LinkedList<>();
        int lengthFront = 0;
        queueFront.add(beginWord);
        
        // Set up back variables
        List<String> visitedBack = new ArrayList<>();
        Queue<String> queueBack = new LinkedList<>();
        int lengthBack = 0;
        queueBack.add(endWord);
        
        
        while (!queueFront.isEmpty() && !queueBack.isEmpty()) {
            int sizeFront = queueFront.size();
            lengthFront++;
            
            while (sizeFront-- > 0) {
                String curr = queueFront.remove();
                if (visitedBack.contains(curr)) {
                    return lengthFront + lengthBack - 1;
                }
                visitedFront.add(curr);
                
                for (String neighbor : findNeighbor(curr, wordList)) {
                    if (!visitedFront.contains(neighbor)) {
                        queueFront.add(neighbor);
                    }
                }
            }
            
            int sizeBack = queueBack.size();
            lengthBack++;
            
            while (sizeBack-- > 0) {
                String curr = queueBack.remove();
                if (visitedFront.contains(curr)) {
                    return lengthFront + lengthBack - 1;
                }
                visitedBack.add(curr);
                
                for (String neighbor : findNeighbor(curr, wordList)) {
                    if (!visitedBack.contains(neighbor)) {
                        queueBack.add(neighbor);
                    }
                }
            }
        }
        
        return 0;
    }
    
    // Returns a list of words in wordList which has only one different
    // letter with the given word
    private List<String> findNeighbor(String word, List<String> wordList) {
        List<String> res = new ArrayList<>();
        for (String w : wordList) {
            if (isNeighbor(word, w)) {
                res.add(w);
            }
        }
        return res;
    }
    
    // Returns whether w1 only has one different letter with w2
    // Pre: w1 and w2 are non null and have the same length
    private boolean isNeighbor(String w1, String w2) {
        int len = w1.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }
}