class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses < 0) {
            return new int[0];
        }
        
        Map<Integer, Integer> income = getIncome(numCourses, prerequisites);
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (income.get(i) == 0) {
                queue.offer(i);
            }
        }
        
        int[] order = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[idx] = course;
            idx++;
            
            for (int[] pair : prerequisites) {
                if (pair[1] == course) {
                    income.put(pair[0], income.get(pair[0]) - 1);
                    if (income.get(pair[0]) == 0) {
                        queue.offer(pair[0]);
                    }
                }
            }
        }
        
        return idx == numCourses ? order : new int[0];
    }
    
    private Map<Integer, Integer> getIncome(int n, int[][] prerequisites) {
        Map<Integer, Integer> income = new HashMap<>();
        for (int i = 0; i < n; i++) {
            income.put(i, 0);
        }
        
        for (int[] pair : prerequisites) {
            income.put(pair[0], income.get(pair[0]) + 1);
        }
        
        return income;
    }
}