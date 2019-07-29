// Hash O(m + n)time O(m)space
class Solution {
    public int numJewelsInStones(String J, String S) {
        if (J == null || S == null) {
            return 0;
        }
        
        Set<Character> set = new HashSet<>();
        for (char c : J.toCharArray()) {
            set.add(c);
        }
        
        int res = 0;
        for (char c : S.toCharArray()) {
            if (set.contains(c)) {
                res++;
            }
        }
        
        return res;
    }
}