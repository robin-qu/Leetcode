// // O(nlogn)time O(n)space
// class Solution {    
//     public String reorganizeString(String s) {
//         if (s == null || s.length() <= 1) {
//             return s;
//         }
        
//         int n = s.length();
//         Character[] ss = new Character[n];
//         for (int i = 0; i < n; i++) {
//             ss[i] = s.charAt(i);
//         }
//         Map<Character, Integer> counts = new HashMap<>();
        
//         for (char c : ss) {
//             counts.put(c, counts.getOrDefault(c, 0) + 1);
//             if (counts.get(c) > (n + 1) / 2) {
//                 return "";
//             }
//         }
        
//         Arrays.sort(ss, new Comparator<Character>() {
//             @Override
//             public int compare(Character a, Character b) {
//                 if (counts.get(a) != counts.get(b)) {
//                     return counts.get(b) - counts.get(a);
//                 }
//                 return a - b;
//             }
//         });
        
//         char[] res = new char[n];
//         int idx = 0;
//         for (int i = 0; i < n; i += 2) {
//             res[i] = ss[idx++];
//         }
//         for (int i = 1; i < n; i += 2) {
//             res[i] = ss[idx++];
//         }
        
//         return String.valueOf(res);
//     }
// }


// pq O(nlogn)time O(n)space
class Solution {    
    public String reorganizeString(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        int n = s.length();
        Map<Character, Integer> counts = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] != b[1]) {
                    return b[1] - a[1];
                }
                
                return a[0] - b[0];
            }
        });
        
        for (char key : counts.keySet()) {
            if (counts.get(key) > (n + 1) / 2) {
                return "";
            }
            pq.offer(new int[]{key, counts.get(key)});
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != first[0]) {
                sb.append((char) first[0]);
                first[1]--;
            } else {
                int[] second = pq.poll();
                sb.append((char) second[0]);
                second[1]--;
                if (second[1] > 0) {
                    pq.offer(second);
                }
            }
            
            if (first[1] > 0) {
                pq.offer(first);
            }
        }
        
        return sb.toString();
    }
}