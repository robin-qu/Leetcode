// // hash O(n)time O(n)space
// class Solution {
//     public String mostCommonWord(String paragraph, String[] banned) {
//         if (paragraph == null || banned == null) {
//             return null;
//         }
        
//         Set<String> banSet = new HashSet<>();
//         for (String ban : banned) {
//             banSet.add(ban);
//         }
        
//         Map<String, Integer> count = new HashMap<>();
//         int idx = 0;
//         StringBuilder sb = new StringBuilder();
//         while (idx < paragraph.length()) {
//             char c =  paragraph.charAt(idx);
//             if (Character.isLetter(c)) {
//                 sb.append(c);
//             } else {
//                 if (sb.length() != 0) {
//                     String key = sb.toString().toLowerCase();
//                     if (!banSet.contains(key)) {
//                         count.put(key, count.getOrDefault(key, 0) + 1);
//                     }
//                 }
//                 sb = new StringBuilder();
//             }
            
//             idx++;
//         }
        
//         if (sb.length() > 0) {
//             String key = sb.toString().toLowerCase();
//             if (!banSet.contains(key)) {
//                 count.put(key, count.getOrDefault(key, 0) + 1);
//             }
//         }
        
//         int max = 0;
//         String res = null;
//         for (String key : count.keySet()) {
//             if (count.get(key) > max) {
//                 max = count.get(key);
//                 res = key;
//             }
//         }
        
//         return res;
//     }
// }



// hash O(n)time O(n)space
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || banned == null) {
            return null;
        }
        
        Set<String> banSet = new HashSet<>();
        for (String ban : banned) {
            banSet.add(ban);
        }
        
        Map<String, Integer> count = new HashMap<>();
        
        String[] words = paragraph.toLowerCase().split("[ !?',;.]+");
        
        for (String word : words) {
            if (banSet.contains(word)) {
                continue;
            }
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        
        int max = 0;
        String res = null;
        for (String key : count.keySet()) {
            if (count.get(key) > max) {
                max = count.get(key);
                res = key;
            }
        }
        
        return res;
    }
}