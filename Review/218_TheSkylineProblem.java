// class Solution {
//     class Pos implements Comparable<Pos> {
//         public int x;
//         public int y;
//         public int isLeft;
        
//         public Pos(int x, int y, int isLeft) {
//             this.x = x;
//             this.y = y;
//             this.isLeft = isLeft;
//         }
        
//         @Override
//         public int compareTo(Pos other) {
//             if (this.x != other.x) {
//                 return this.x - other.x;
//             }
//             if (this.isLeft == 0 && other.isLeft == 0) {
//                 return this.y - other.y;
//             }
//             if (this.isLeft == 1 && other.isLeft == 1) {
//                 return other.y - this.y;
//             }
//             return other.isLeft - this.isLeft;
//         }
//     }
                                        
//     public List<List<Integer>> getSkyline(int[][] buildings) {
//         if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = buildings.length;
//         PriorityQueue<Pos> pq = new PriorityQueue<>();
//         for (int[] building : buildings) {
//             pq.offer(new Pos(building[0], building[2], 1));
//             pq.offer(new Pos(building[1], building[2], 0));
//         }
//         PriorityQueue<Integer> heights = new PriorityQueue<>((a, b) -> b - a);
//         heights.offer(0);
        
//         List<List<Integer>> res = new ArrayList<>();
//         while (!pq.isEmpty()) {
//             Pos curr = pq.poll();
//             if (curr.isLeft == 1) {
//                 if (heights.peek() < curr.y) {
//                     res.add(Arrays.asList(curr.x, curr.y));
//                 }
//                 heights.offer(curr.y);
//             } else {
//                 if (curr.y != heights.peek()) {
//                     heights.remove(curr.y);
//                 } else {
//                     heights.poll();
//                     if (heights.peek() < curr.y) {
//                         res.add(Arrays.asList(curr.x, heights.peek()));
//                     }
//                 }
//             }
//         }
        
//         return res;
//     }
// }




class Solution {
    class Pos implements Comparable<Pos> {
        public int x;
        public int y;
        public int isLeft;

        public Pos(int x, int y, int isLeft) {
            this.x = x;
            this.y = y;
            this.isLeft = isLeft;
        }

        @Override
        public int compareTo(Pos other) {
            if (this.x != other.x) {
                return this.x - other.x;
            }
            if (this.isLeft != other.isLeft) {
                return other.isLeft - this.isLeft;
            }
            if (this.isLeft == 1) {
                return other.y - this.y;
            }
            return this.y - other.y;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0) {
            return new ArrayList<>();
        }

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        for (int[] building : buildings) {
            pq.offer(new Pos(building[0], building[2], 1));
            pq.offer(new Pos(building[1], building[2], 0));
        }

        PriorityQueue<Integer> heights = new PriorityQueue<>(Collections.reverseOrder());
        List<List<Integer>> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            Pos curr = pq.poll();
            if (curr.isLeft == 1) {
                if (heights.isEmpty() || heights.peek() < curr.y) {
                    res.add(Arrays.asList(curr.x, curr.y));
                }
                heights.offer(curr.y);
            } else {
                heights.remove(curr.y);
                if (heights.isEmpty() || heights.peek() < curr.y) {
                    res.add(Arrays.asList(curr.x, heights.isEmpty() ? 0 : heights.peek()));
                }
            }
        }

        return res;   
    }
}