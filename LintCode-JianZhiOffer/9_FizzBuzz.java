// public class Solution {
//     /**
//      * @param n: An integer
//      * @return: A list of strings.
//      */
//     public List<String> fizzBuzz(int n) {
//         List<String> res = new ArrayList<>();
        
//         for (int i = 1; i <= n; i++) {
//             if (i % 3 == 0 && i % 5 == 0) {
//                 res.add("fizz buzz");
//             } else if (i % 3 == 0) {
//                 res.add("fizz");
//             } else if (i % 5 == 0) {
//                 res.add("buzz");
//             } else {
//                 res.add(String.valueOf(i));
//             }
//         }
        
//         return res;
//     }
// }


// // Implement it with only one if
// public class Solution {
//     /**
//      * @param n: An integer
//      * @return: A list of strings.
//      */
//     public List<String> fizzBuzz(int n) {
//         List<String> res = new ArrayList<>();
        
//         Map<Integer, String> map1 = new HashMap<>();
//         map1.put(3, "fizz");
//         Map<Integer, String> map2 = new HashMap<>();
//         map2.put(5, "buzz");
        
//         for (int i = 1; i <= n; i++) {
//             String s = map1.getOrDefault(i % 3 + 3, "") + " " + map2.getOrDefault(i % 5 + 5, "");
//             if (s.trim().length() == 0) {
//                 res.add(String.valueOf(i));
//             } else {
//                 res.add(s.trim());
//             }
//         }
        
//         return res;
//     }
// }


class Solution {
    /**
     * param n: As description.
     * return: A list of strings.
     */
    public ArrayList<String> fizzBuzz(int n) {
        ArrayList<String> results = new ArrayList<String>();
        int i = 1;
        int p3 = 1;
        int p5 = 1;
        
        while (i <= n) {
          while (i < p3 * 3 && i < p5 * 5) {
            results.add(i + "");
            i++;
          }
        
          if (i <= n && p3 * 3 == p5 * 5) {
            results.add("fizz buzz");
            p3++;
            p5++;
            i++;
            continue;
          }
        
          while (i <= n && p3 * 3 == i) {
            results.add("fizz");
            p3++;
            i++;
          }
        
          while (i <= n && p5 * 5 == i) {
            results.add("buzz");
            p5++;
            i++;
          }
        }
        
        return results;
    }
}