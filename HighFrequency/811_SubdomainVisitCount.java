// // O(nl)time O(n)space
// class Solution {
//     public List<String> subdomainVisits(String[] cpdomains) {
//         if (cpdomains == null || cpdomains.length == 0) {
//             return new ArrayList<>();
//         }
        
//         Map<String, Integer> map = new HashMap<>();
        
//         for (String s: cpdomains) {
//             String[] temp = s.split(" ");
//             int count = Integer.parseInt(temp[0]);
//             System.out.print(temp[1]);
//             String[] ss = temp[1].split("\\.");
            
//             String domain = "";
//             for (int i = ss.length - 1; i >= 0; i--) {
//                 if (i < ss.length - 1) {
//                     domain = "." + domain;
//                 }
//                 domain = ss[i] + domain;
                
//                 if (!map.containsKey(domain)) {
//                     map.put(domain, 0);
//                 }
//                 map.put(domain, map.get(domain) + count);                
//             }
//         }
        
//         List<String> res = new ArrayList<>();
//         for (String key : map.keySet()) {
//             res.add(map.get(key) + " " + key);
//         }
        
//         return res;
//     }
// }


// O(nl)time O(n)space  indexOf(".") is faster than split("\\.")
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        if (cpdomains == null || cpdomains.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String s: cpdomains) {
            int count = Integer.parseInt(s.substring(0, s.indexOf(" ")));
            String ss = s.substring(s.indexOf(" ") + 1, s.length());
            
            if (!map.containsKey(ss)) {
                map.put(ss, 0);
            }
            map.put(ss, map.get(ss) + count);
            for (int i = 0; i < ss.length(); i++) {
                if (ss.charAt(i) == '.') {
                    String domain = ss.substring(i + 1, ss.length());
                    if (!map.containsKey(domain)) {
                        map.put(domain, 0);
                    }
                    map.put(domain, map.get(domain) + count);
                }
            }
        }
        
        List<String> res = new ArrayList<>();
        for (String key : map.keySet()) {
            res.add(map.get(key) + " " + key);
        }
        
        return res;
    }
}