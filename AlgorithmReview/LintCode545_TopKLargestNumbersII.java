public class Solution {
    /*
    * @param k: An integer
    */
    
    private PriorityQueue<Integer> pq;
    private int cap;
    
    public Solution(int k) {
        pq = new PriorityQueue<>();
        cap = k;
    }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        if (pq.size() < cap) {
            pq.add(num);
            return;
        }
        
        if (num > pq.peek()) {
            pq.poll();
            pq.offer(num);
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
        
        for (int i = 0; i < list.size(); i++) {
            pq.offer(list.get(i));
        }
        
        return list;
    }
}