class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = seperateWords(words, maxWidth);
        justify(res, maxWidth);
        
        return res;
    }
    
    private List<String> seperateWords(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (sb.length() == 0) {
                sb.append(words[i]);
                continue;
            }
            
            if (sb.length() + words[i].length() + 1 <= maxWidth) {
                sb.append(' ');
                sb.append(words[i]);
            } else {
                res.add(sb.toString());
                sb.delete(0, sb.length());
                sb.append(words[i]);
            }
        }
        if (sb.length() != 0) {
            res.add(sb.toString());
        }
        
        return res;
    }
    
    private void justify(List<String> res, int maxWidth) {
        for (int i = 0; i < res.size() - 1; i++) {
            String row = res.get(i);
            String[] words = row.split(" ");
            
            int totalLength = 0;
            for (String word : words) {
                totalLength += word.length();
            }
            
            int totalSpace = maxWidth - totalLength;
            int len = words.length;
            int[] space = new int[len - 1];
            if (len > 1) {
                Arrays.fill(space, totalSpace / (len - 1));
                for (int j = 0; j < totalSpace % (len - 1); j++) {
                    space[j]++;
                }
            }
                
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len - 1; j++) {
                sb.append(words[j]);
                for (int k = 0; k < space[j]; k++) {
                    sb.append(' ');
                }
            }
            sb.append(words[len - 1]);
            
            while (sb.length() != maxWidth) {
                sb.append(' ');
            }
            
            res.set(i, sb.toString());
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(res.get(res.size() - 1));
        while (sb.length() != maxWidth) {
            sb.append(' ');
        }
        
        res.set(res.size() - 1, sb.toString());
    }
}