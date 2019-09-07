// O(nlen)time O(1)space
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || order == null) {
            return false;
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            if (!inOrder(words[i], words[i + 1], order)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean inOrder(String a, String b, String order) {
        int i = 0;
        while (i < Math.max(a.length(), b.length())) {
            int ia = (i < a.length() ? order.indexOf(a.charAt(i)) : -1);
            int ib = (i < b.length() ? order.indexOf(b.charAt(i)) : -1);
            
            if (ia > ib) {
                return false;
            }
            
            if (ia < ib) {
                return true;
            }
            
            i++;
        }
        
        return true;
    }
}