class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return new int[0];
        }

        int[] incomings = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            incomings[prerequisite[0]]++;
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (incomings[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            list.add(curr);
            for (int neighbor : graph.get(curr)) {
                incomings[neighbor]--;
                if (incomings[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        if (list.size() < numCourses) {
            return new int[0];
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}