public class Solution {
    /*
    * @param k: An integer
    */
    
    private PriorityQueue<Integer> pq;
    private int k;
    
    public Solution(int k) {
        this.pq = new PriorityQueue<>();
        this.k = k;
    }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        pq.add(num);
        if (pq.size() > k) {
            pq.poll();
        }
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        List<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            list.add(0, pq.poll());
        }
        for (int num : list) {
            pq.add(num);
        }
        return list;
    }
}