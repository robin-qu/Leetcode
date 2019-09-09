class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        if (phrases == null || phrases.length == 0) {
            return new ArrayList<>();
        }
        
        int n = phrases.length;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String curr = phrases[i];
            String[] s1 = curr.split(" ");
            String suffix = s1[s1.length - 1];
            
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                
                String[] s2 = phrases[j].split(" ");
                String prefix = s2[0];
                if (suffix.equals(prefix)) {
                    StringBuilder sb = new StringBuilder(curr);
                    for (int k = 1; k < s2.length; k++) {
                        sb.append(' ');
                        sb.append(s2[k]);
                    }
                    
                    if (!res.contains(sb.toString())) {
                        res.add(sb.toString());
                    }
                }
            }
        }

        Collections.sort(res);
        
        return res;
    }
}