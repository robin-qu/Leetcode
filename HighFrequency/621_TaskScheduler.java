// Greedy O(n)time O(26)space
class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        
        int len = tasks.length;
        
        if (n == 0) {
            return len;
        }
        
        int[] counter = new int[26];
        int max = 0;
        int maxCount = 0;
        for (int i = 0; i < len; i++) {
            counter[tasks[i] - 'A']++;
            if (counter[tasks[i] - 'A'] > max) {
                max = counter[tasks[i] - 'A'];
                maxCount = 1;
            } else if (counter[tasks[i] - 'A'] == max) {
                maxCount++;
            }
        }
        
        int partNum = max - 1;
        int availablePartLength = n - (maxCount - 1);
        int availableSlots = partNum * availablePartLength;
        int remainingTasks = len - max * maxCount;
        int idleNum = Math.max(0, availableSlots - remainingTasks);
        
        
        return len + idleNum;
    }
}