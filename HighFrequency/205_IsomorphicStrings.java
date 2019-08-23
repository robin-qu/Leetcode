// // O(n)time O(n)space
// class Solution {
//     public boolean isIsomorphic(String s, String t) {
//         if (s == null || s.length() == 0 ||
//             t == null || t.length() == 0) {
//             return true;
//         }
        
//         int n = s.length();
//         Map<Character, Character> sToT = new HashMap<>();
//         Map<Character, Character> tToS = new HashMap<>();
        
//         for (int i = 0; i < n; i++) {
//             if (!sToT.containsKey(s.charAt(i))) {
//                 sToT.put(s.charAt(i), t.charAt(i));
//             } else {
//                 if (sToT.get(s.charAt(i)) != t.charAt(i)) {
//                     return false;
//                 }
//             }
            
//             if (!tToS.containsKey(t.charAt(i))) {
//                 tToS.put(t.charAt(i), s.charAt(i));
//             } else {
//                 if (tToS.get(t.charAt(i)) != s.charAt(i)) {
//                     return false;
//                 }
//             } 
//         }
        
//         return true;
//     }
// }


// O(n)time O(n)space
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || s.length() == 0 ||
            t == null || t.length() == 0) {
            return true;
        }
        
        int n = s.length();
        int[] a1 = new int[256];
        int[] a2 = new int[256];
        
        for (int i = 0; i < n; i++) {
            if (a1[s.charAt(i)] != a2[t.charAt(i)]) {
                return false;
            }
            a1[s.charAt(i)] = i + 1;
            a2[t.charAt(i)] = i + 1;
        }
        
        return true;
    }
}