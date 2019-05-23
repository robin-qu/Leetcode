public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if (seqs == null || seqs.length == 0) {
            return false;
        }
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> income = new HashMap<>();
        for (int[] seq : seqs) {
            for (int i = 0; i < seq.length; i++) {
                graph.putIfAbsent(seq[i], new ArrayList<Integer>());
                income.putIfAbsent(seq[i], 0);
                if (i > 0) {
                    graph.get(seq[i - 1]).add(seq[i]);
                    income.put(seq[i], income.get(seq[i]) + 1);
                }
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> order = new ArrayList<>();
        
        for (int i : income.keySet()) {
            if (income.get(i) == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                return false;
            }
            int curr = queue.poll();
            order.add(curr);
            for (int neighbor : graph.get(curr)) {
                income.put(neighbor, income.get(neighbor) - 1);
                if (income.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
            
        }
        
        if (order.size() != org.length) {
            return false;
        }
        
        for (int i = 0; i < org.length; i++) {
            if (order.get(i) != org[i]) {
                return false;
            }
        }
        
        return true;
    }
}