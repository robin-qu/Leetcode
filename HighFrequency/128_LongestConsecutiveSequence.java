// Union find O(n)time O(n)space
class Solution {
    private int n;
    private Map<Integer, Integer> unionFind;
    private Map<Integer, Integer> counts;
    
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        unionFind = new HashMap<>();
        counts = new HashMap<>();
        for (int num : nums) {
            unionFind.put(num, num);
            counts.put(num, 1);
        }
        
        for (int num : nums) {
            if (unionFind.containsKey(num + 1)) {
                connect(num, num + 1);
            }
            
            if (unionFind.containsKey(num - 1)) {
                connect(num, num - 1);
            }
        }
        
        int max = 0;
        for (int num : counts.keySet()) {
            max = Math.max(max, counts.get(num));
        }
        return max;
    }
    
    private int find(int num) {
        int root = num;
        while (unionFind.get(root) != root) {
            root = unionFind.get(root);
        }
        
        int curr = num;
        while (curr != root) {
            int father = unionFind.get(curr);
            unionFind.put(curr, root);
            curr = father;
        }
        
        return root;
    }
    
    // connect a to b
    private void connect(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA != rootB) {
            unionFind.put(rootA, rootB);
            counts.put(rootB, counts.get(rootA) + counts.get(rootB));
        }
    }
}