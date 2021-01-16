class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }

        int n = s.length();
        int m = words[0].length();
        Map<String, Integer> counts = new HashMap<>();
        int count = 0;
        for (String word : words) {
            if (!counts.containsKey(word)) {
                counts.put(word, 1);
                count++;
            } else {
                counts.put(word, counts.get(word) + 1);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int left = i;
            Map<String, Integer> currCounts = new HashMap<>();
            int currCount = 0;
            for (int right = i; right + m <= n; right += m) {
                String curr = s.substring(right, right + m);
                if (counts.containsKey(curr)) {
                    currCounts.put(curr, currCounts.getOrDefault(curr, 0) + 1);
                    if (currCounts.get(curr).equals(counts.get(curr))) {
                        currCount++;
                    }
                    while (currCounts.get(curr) > counts.get(curr)) {
                        String leftS = s.substring(left, left + m);
                        currCounts.put(leftS, currCounts.get(leftS) - 1);
                        if (currCounts.get(leftS).equals(counts.get(leftS) - 1)) {
                            currCount--;
                        }
                        left += m;
                    }
                    if (count == currCount) {
                        res.add(left);
                    }
                } else {
                    left = right + m;
                    currCount = 0;
                    currCounts = new HashMap<>();
                }
            }
        }
        return res;
    }
}