public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    class Pair implements Comparable<Pair> {
        public int pos;
        public int height;
        public int start;  // 1 is start, 0 is end
        
        public Pair(int pos, int height, int start) {
            this.pos = pos;
            this.height = height;
            this.start = start;
        }
        
        @Override
        public int compareTo(Pair other) {
            if (this.pos != other.pos) {
                return this.pos - other.pos;
            } 
            
            if (this.start == 1 && other.start == 1) {
                return other.height - this.height;
                // for starting point, add higher one first
            }
            
            if (this.start == 0 && other.start == 0) {
                return this.height - other.height;
                // for ending point, remove lower one first
            }
            
            return other.start - this.start;
            // beginning first
        }
    }
    
    public List<List<Integer>> buildingOutline(int[][] buildings) {
        if (buildings == null || buildings.length == 0 ||
            buildings[0] == null || buildings[0].length == 0) {
            return new ArrayList<>();
        }
        
        List<Pair> outline = new ArrayList<>();
        for (int[] building : buildings) {
            outline.add(new Pair(building[0], building[2], 1));
            outline.add(new Pair(building[1], building[2], 0));
        }
        
        Collections.sort(outline);
        
        List<int[]> temp = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (Pair p : outline) {
            if (p.start == 1) {
                if (pq.isEmpty() || p.height > pq.peek()) {
                    temp.add(new int[]{p.pos, p.height});
                }
                pq.offer(p.height);
            } else {
                pq.remove(p.height);
                if (pq.isEmpty() || p.height > pq.peek()) {
                    if (pq.isEmpty()) {
                        temp.add(new int[]{p.pos, 0});
                    } else {
                        temp.add(new int[]{p.pos, pq.peek()});
                    }
                }
            }
        }
        
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < temp.size() - 1; i++) {
            List<Integer> list = new ArrayList<>();
            int start = temp.get(i)[0];
            int end = temp.get(i + 1)[0];
            int height = temp.get(i)[1];
            if (start == end || height == 0) {
                continue;
            }
            
            list.add(start);
            list.add(end);
            list.add(height);
            res.add(list);
        }
        
        return res;
    }
}
