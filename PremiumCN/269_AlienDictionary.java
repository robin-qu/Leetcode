class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        int n = words.length;

        Map<Character, Integer> incomings = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                incomings.put(c, 0);
                graph.put(c, new ArrayList<>());
            }
        }

        for (int i = 1; i < n; i++) {
            String prev = words[i - 1];
            String curr = words[i];
            if (prev.length() > curr.length() && prev.startsWith(curr)) {
                return "";
            }
            for (int j = 0; j < Math.min(prev.length(), curr.length()); j++) {
                char a = prev.charAt(j);
                char b = curr.charAt(j);
                if (a != b) {
                    incomings.put(b, incomings.get(b) + 1);
                    graph.get(a).add(b);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (char c : incomings.keySet()) {
            if (incomings.get(c) == 0) {
                queue.offer(c);
            }
        }

        while (!queue.isEmpty()) {
            // if (queue.size() > 1) {
            //     return "";
            // }
            char c = queue.poll();
            sb.append(c);
            for (char neighbor : graph.get(c)) {
                incomings.put(neighbor, incomings.get(neighbor) - 1);
                if (incomings.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (sb.length() < incomings.size()) {
            return "";
        }

        return sb.toString();
    }
}