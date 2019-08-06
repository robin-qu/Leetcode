// // Without %
// class Solution {
//     public List<String> fizzBuzz(int n) {
//         List<String> res = new ArrayList<>();
//         int i = 1;
//         int p3 = 1;
//         int p5 = 1;
        
//         while (i <= n) {
//             if (i < 3 * p3 && i < 5 * p5) {
//                 res.add("" + i);
//             } else if (i == 3 * p3 && i == 5 * p5) {
//                 res.add("FizzBuzz");
//                 p3++;
//                 p5++;
//             } else if (i == 3 * p3) {
//                 res.add("Fizz");
//                 p3++;
//             } else if (i == 5 * p5) {
//                 res.add("Buzz");
//                 p5++;
//             }
//             i++;
//         }
        
//         return res;
//     }
// }


// Without if-else
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        Map<Integer, String> map3 = new HashMap<>();
        map3.put(0, "Fizz");
        Map<Integer, String> map5 = new HashMap<>();
        map5.put(0, "Buzz");
        
        for (int i = 1; i <= n; i++) {
            String curr = map3.getOrDefault(i % 3, "") + map5.getOrDefault(i % 5, "");
            if (curr.length() == 0) {
                curr = "" + i;
            }
            
            res.add(curr);
        }
        
        return res;
    }
}



// // Without if else and %
// class Solution {
//     public List<String> fizzBuzz(int n) {
//         List<String> res = new ArrayList<>();
//         for (int i = 1; i <= n; i++) {
//             res.add("" + i);
//         }
        
//         for (int i = 3; i <= n; i+=3) {
//             res.set(i - 1, "Fizz");
//         }
        
//         for (int i = 5; i <= n; i+=5) {
//             res.set(i - 1, "Buzz");
//         }
        
//         for (int i = 15; i <= n; i+=15) {
//             res.set(i - 1, "FizzBuzz");
//         }
        
//         return res;
//     }
// }
