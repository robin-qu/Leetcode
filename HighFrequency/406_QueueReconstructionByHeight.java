// // O(n^2)time O(n)space
// class Solution {
//     public int[][] reconstructQueue(int[][] people) {
//         if (people == null || people.length == 0) {
//             return new int[0][0];
//         }
        
//         int n = people.length;
//         int[][] res = new int[n][2];
//         for (int i = 0; i < n; i++) {
//             res[i][0] = -1;
//             res[i][1] = -1;
//         }
        
//         Arrays.sort(people, new Comparator<int[]>() {
//             @Override
//             public int compare(int[] a, int[] b) {
//                 if (a[0] != b[0]) {
//                     return a[0] - b[0];
//                 }
                
//                 return a[1] - b[1];
//             }
//         });
        
//         for (int i = 0; i < n; i++) {
//             int[] person = people[i];
            
//             int height = person[0];
//             int count = person[1];
//             int pos = 0;
//             while (count > 0) {
//                 if ((res[pos][0] == -1 && res[pos][1] == -1) || (res[pos][0] >= height)) {
//                     count--;
//                 }
//                 pos++;
//             }
            
//             // find next empty spot
//             while (!(res[pos][0] == -1 && res[pos][1] == -1)) {
//                 pos++;
//             }
            
//             res[pos][0] = person[0];
//             res[pos][1] = person[1];
//         }
        
//         return res;
//     }
// }

// O(n^2)time O(n)space
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) {
            return new int[0][0];
        }
        
        int n = people.length;
        
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return b[0] - a[0];
                }
                
                return a[1] - b[1];
            }
        });
        
        List<int[]> list = new ArrayList<>();
        
        for (int[] person : people) {
            list.add(person[1], person);
        }
        
        int[][] res = new int[n][2];
        for (int i = 0; i < n; i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
}