// O(nlogn)time O(n)space
class Solution {
    class UF {
        public int[] uf;
        
        public UF(int size) {
            uf = new int[size];
            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }
        }
        
        public int find(int a) {
            int root = a;
            
            while (root != uf[root]) {
                root = uf[root];
            }
            
            int curr = a;
            while (curr != root) {
                int parent = uf[curr];
                uf[curr] = root;
                curr = parent;
            }
            
            return root;
        }
        
        public void connect(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            
            if (rootA != rootB) {
                uf[rootB] = rootA;
            }
        }
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.size() == 0) {
            new ArrayList<>();
        }
        
        UF uf = new UF(accounts.size());
        
        Map<String, List<Integer>> emailToID = getConnection(accounts, uf);
        for (String email : emailToID.keySet()) {
            List<Integer> IDs = emailToID.get(email);
            
            for (int i = 0; i < IDs.size() - 1; i++) {
                uf.connect(IDs.get(i), IDs.get(i + 1));
            }
        }
        
        Map<Integer, Set<String>> IDtoEmail = getEmails(accounts, uf);
        List<List<String>> res = new ArrayList<>();
        
        for (int id : IDtoEmail.keySet()) {
            List<String> value = new ArrayList<>();
            for (String email : IDtoEmail.get(id)) {
                value.add(email);
            }
            
            Collections.sort(value);
            res.add(value);
        }
        
        return res;
    }
    
    private Map<Integer, Set<String>> getEmails(List<List<String>> accounts, UF uf) {
        Map<Integer, Set<String>> res = new HashMap<>();
        
        for (int i = 0; i < accounts.size(); i++) {
            int id = uf.find(i);
            
            if (!res.containsKey(id)) {
                res.put(id, new HashSet<>());
            }
            
            for (String email : accounts.get(i)) {
                res.get(id).add(email);
            }
        }
        
        return res;
    }
    
    private Map<String, List<Integer>> getConnection(List<List<String>> accounts, UF uf) {
        Map<String, List<Integer>> res = new HashMap<>();
        
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            String name = account.get(0);
            for (int j = 1; j < account.size(); j++) {
                if (!res.containsKey(account.get(j))) {
                    res.put(account.get(j), new ArrayList<>());
                }
                
                res.get(account.get(j)).add(i);
            }
        }
        
        return res;
    }
}