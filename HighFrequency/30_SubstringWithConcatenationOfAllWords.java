// Two pointer(Sliding window) O(len*n + m)time O(m)space
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.length() == 0 ||
            words == null || words.length == 0) {
            return new ArrayList<>();
        }
        
        int n = s.length();
        int m = words.length;
        int len = words[0].length();
        
        Map<String, Integer> counts = new HashMap<>();
        int count = 0;
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
            if (counts.get(word) == 1) {
                count++;
            }
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Map<String, Integer> now = new HashMap<>();
            int currCount = count;
            
            int left = i;
            for (int right = i; right <= n - len; right += len) {
                if (right - left == m * len) {
                    String first = s.substring(left, left + len);
                    if (counts.containsKey(first)) {
                        now.put(first, now.get(first) - 1);
                        if (now.get(first) == counts.get(first) - 1) {
                            currCount++;
                        } else if (now.get(first) == counts.get(first)) {
                            currCount--;
                        }
                    }
                    
                    left += len;
                }
                
                String curr = s.substring(right, right + len);
                if (counts.containsKey(curr)) {
                    now.put(curr, now.getOrDefault(curr, 0) + 1);
                    if (now.get(curr) == counts.get(curr)) {
                        currCount--;
                    } else if (now.get(curr) == counts.get(curr) + 1) {
                        currCount++;
                    }
                    
                    if (currCount == 0) {
                        res.add(left);
                    }
                }
            }
        }
        
        return res;
    }
}