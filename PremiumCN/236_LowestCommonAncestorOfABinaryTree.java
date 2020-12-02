class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) {
            return 0;
        }

        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }

        int res = 1;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        words.remove(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                for (int j = 0; j < curr.length(); j++) {
                    char[] ss = curr.toCharArray();
                    for (int k = 0; k < 26; k++) {
                        ss[j] = (char) ('a' + k);
                        String next = String.valueOf(ss);
                        if (next.equals(endWord)) {
                            return res;
                        }
                        if (words.contains(next)) {
                            queue.offer(next);
                            words.remove(next);
                        }
                    }
                }
            }
        }

        return 0;
    }
}