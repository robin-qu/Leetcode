class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 0) {
            return false;
        }
        
        if (numCourses <= 1 || prerequisites == null || prerequisites.length <= 1) {
            return true;
        }
        
        Map<Integer, Integer> income = getIncome(numCourses, prerequisites);
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> canFinish = new ArrayList<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (income.get(i) == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            canFinish.add(course);
            
            for (int[] pair : prerequisites) {
                if (pair[1] == course) {
                    income.put(pair[0], income.get(pair[0]) - 1);
                    if (income.get(pair[0]) == 0) {
                        queue.offer(pair[0]);
                    }
                }
            }
        }
        
        return canFinish.size() == numCourses;
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