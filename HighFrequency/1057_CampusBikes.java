// // O(n^2m)time O(n+m)space
// class Solution {
//     public int[] assignBikes(int[][] workers, int[][] bikes) {
//         if (workers == null || bikes == null) {
//             return new int[0];
//         }
        
//         int n = workers.length;
//         int m = bikes.length;
//         int[] res = new int[n];
//         Set<Integer> usedWorkers = new HashSet<>();
//         Set<Integer> usedBikes = new HashSet<>();
        
//         for (int k = 0; k < n; k++) {
//             int min = Integer.MAX_VALUE;
//             int worker = n;
//             int bike = m;
//             for (int i = m - 1; i >= 0; i--) {
//                 if (usedBikes.contains(i)) {
//                     continue;
//                 }
//                 for (int j = n - 1; j >= 0; j--) {
//                     if (usedWorkers.contains(j)) {
//                         continue;
//                     }
//                     if (getDist(workers[j], bikes[i]) <= min) {
//                         min = getDist(workers[j], bikes[i]);
//                         worker = j;
//                         bike = i;
//                     }
//                 }
//             }
            
//             usedWorkers.add(worker);
//             usedBikes.add(bike);
//             res[worker] = bike;
//         }
        
//         return res;
//     }
    
//     private int getDist(int[] worker, int[] bike) {
//         return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
//     }
// }


// bucket sort O(nm)time O(nm)space
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if (workers == null || bikes == null) {
            return new int[0];
        }
        
        int n = workers.length;
        int m = bikes.length;
        int[] res = new int[n];
        Set<Integer> usedWorkers = new HashSet<>();
        Set<Integer> usedBikes = new HashSet<>();
        
        List<int[]>[] dist = new List[2001];
        for (int i = 0; i < 2001; i++) {
            dist[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[getDist(workers[i], bikes[j])].add(new int[]{i, j});
            }
        }
        
        for (int i = 0; i < 2001; i++) {
            for (int j = 0; j < dist[i].size(); j++) {
                int worker = dist[i].get(j)[0];
                int bike = dist[i].get(j)[1];
                if (!usedWorkers.contains(worker) && !usedBikes.contains(bike)) {
                    res[worker] = bike;
                    usedWorkers.add(worker);
                    usedBikes.add(bike);
                    if (usedWorkers.size() == n) {
                        return res;
                    }
                }
            }
        }
        
        return res;
    }
    
    private int getDist(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}