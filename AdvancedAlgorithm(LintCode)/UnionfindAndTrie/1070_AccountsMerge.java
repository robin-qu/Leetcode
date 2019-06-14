// public class Solution {  // has bug somewhere
//     /**
//      * @param accounts: List[List[str]]
//      * @return: return a List[List[str]]
//      */
     
//     // represents unionfind email to email
//     private Map<String, String> graph;  
//     // stores the username of each email
//     private Map<String, String> userName;  
    
//     public List<List<String>> accountsMerge(List<List<String>> accounts) {
//         if (accounts == null || accounts.size() == 0) {
//             return new ArrayList<>();
//         }
        
//         graph = new HashMap<>();
//         userName = new HashMap<>();
//         // stores the list of users who use the same email
//         Map<String, Set<Integer>> connection = new HashMap<>();
//         for (int i = 0; i < accounts.size(); i++) { // i is user id
//             for (int j = 1; j < accounts.get(i).size(); j++) {
//                 String email = accounts.get(i).get(j);
//                 if (!graph.containsKey(email)) {
//                     graph.put(email, email);
//                     userName.put(email, accounts.get(i).get(0));
//                     connection.put(email, new HashSet<>());
//                 }
//                 connection.get(email).add(i);
//             }
//         }
        
//         for (int i = 0; i < accounts.size(); i++) {
//             for (int j = 1; j < accounts.get(i).size() - 1; j++) {
//                 connect(accounts.get(i).get(j),
//                         accounts.get(i).get(j + 1));
//             }
//         }
        
//         for (String email : connection.keySet()) {
//             if (connection.get(email).size() > 1) {
//                 for (int i = 0 ; i < connection.get(email).size() - 1; i++) {
//                     connect(accounts.get(i).get(1), accounts.get(i + 1).get(1));
//                 }
//             }
//         }
        
//         Map<String, Set<String>> resMap = new HashMap<>();
        
//         for (int i = 0; i < accounts.size(); i++) {
//             String root = find(accounts.get(i).get(1));
//             if (!resMap.containsKey(root)) {
//                 resMap.put(root, new HashSet<String>());
//             }
//             for (int j = 1; j < accounts.get(i).size(); j++) {
//                 resMap.get(root).add(accounts.get(i).get(j));
//             }
//         }
        
//         List<List<String>> res = new ArrayList<>();
//         for (String root : resMap.keySet()) {
//             List<String> user = new ArrayList<>();
//             user.add(userName.get(root));
//             List<String> emailList = new ArrayList<>();
//             for (String email : resMap.get(root)) {
//                 emailList.add(email);
//             }
//             Collections.sort(emailList);
//             user.addAll(emailList);
//             res.add(user);
//         }
        
//         return res;
//     }
    
//     private String find(String s) {
//         String root = s;
//         while (!graph.get(root).equals(root)) {
//             root = graph.get(root);
//         }
        
//         String curr = s;
//         while (!curr.equals(root)) {
//             String parent = graph.get(curr);
//             graph.put(curr, root);
//             curr = parent;
//         }
        
//         return root;
//     }
    
//     private void connect(String email1, String email2) {
//         String root1 = find(email1);
//         String root2 = find(email2);
//         if (!root1.equals(root2)) {
//             graph.put(root1, root2);
//         }
//     }
// }


public class Solution {
    /**
     * @param accounts: List[List[str]]
     * @return: return a List[List[str]]
     */
     
    // represents unionfind account to account
    private int[] unionfind; 
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.size() == 0) {
            return new ArrayList<>();
        }
        
        // Initialization
        this.unionfind = new int[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            unionfind[i] = i;
        }
        
        // stores the user ids that have the same email
        Map<String, List<Integer>> connection = getConnection(accounts);
        // connects the users that have same email
        for (String email : connection.keySet()) {
            List<Integer> ids = connection.get(email);
            for (int i = 0; i < ids.size() - 1; i++) {
                connect(ids.get(i), ids.get(i + 1));
            }
        }
        
        Map<Integer, Set<String>> users = getUsers(accounts);
        List<List<String>> res = new ArrayList<>();
        for (int id : users.keySet()) {
            List<String> emails = new ArrayList<>();
            emails.addAll(users.get(id));
            Collections.sort(emails);
            List<String> user = new ArrayList<>();
            user.add(accounts.get(id).get(0));
            user.addAll(emails);
            res.add(user);
        }
        
        return res;
    }
    
    private Map<Integer, Set<String>> getUsers(List<List<String>> accounts) {
        Map<Integer, Set<String>> res = new HashMap<>();
        
        for (int i = 0; i < accounts.size(); i++) {
            int id = find(i);
            List<String> list = accounts.get(i);
            Set<String> emails = new HashSet<>();
            for (int j = 1; j < list.size(); j++) {
                emails.add(list.get(j));
            }
            if (!res.containsKey(id)) {
                res.put(id, new HashSet<>());
            }
            res.get(id).addAll(emails);
        }
        
        return res;
    }
    
    private Map<String, List<Integer>> getConnection(List<List<String>> accounts) {
        Map<String, List<Integer>> connection = new HashMap<>();
        
        for (int i = 0; i < accounts.size(); i++) { // i is userid
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                if (!connection.containsKey(account.get(j))) {
                    connection.put(account.get(j), new ArrayList<>());
                }
                connection.get(account.get(j)).add(i);
            }
        }
        
        return connection;
    }
    
    private int find(int a) {
        int root = a;
        while (unionfind[root] != root) {
            root = unionfind[root];
        }
        
        int i = a;
        while (i != root) {
            int parent = unionfind[i];
            unionfind[i] = root;
            i = parent;
        }
        
        return root;
    }
    
    private void connect(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA != rootB) {
            unionfind[rootA] = rootB;
        }
    }
}