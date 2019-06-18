/**
 * Definition for a Connection.
 * public class Connection {
 *   public String city1, city2;
 *   public int cost;
 *   public Connection(String city1, String city2, int cost) {
 *       this.city1 = city1;
 *       this.city2 = city2;
 *       this.cost = cost;
 *   }
 * }
 */
public class Solution {
    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    
    private Map<String, String> unionfind;
    
    class pathComparator implements Comparator<Connection> {
        public int compare(Connection a, Connection b) {
            if (a.cost != b.cost) {
                return a.cost - b.cost;
            } else if (!a.city1.equals(b.city1)) {
                return a.city1.compareTo(b.city1);
            } else {
                return a.city2.compareTo(b.city2);
            }
        }
    }
    
    public List<Connection> lowestCost(List<Connection> connections) {
        if (connections == null || connections.size() == 0) {
            return new ArrayList<>();
        }
        
        this.unionfind = new HashMap<String, String>();
        for (Connection connection : connections) {
            unionfind.put(connection.city1, connection.city1);
            unionfind.put(connection.city2, connection.city2);
        }
        
        Collections.sort(connections, new pathComparator());
        List<Connection> res = new ArrayList<>();
        
        for (int i = 0; i < connections.size(); i++) {
            Connection path = connections.get(i);
            if (!find(path.city1).equals(find(path.city2))) {
                connect(path.city1, path.city2);
                res.add(path);
            }
        }
        
        return res.size() == unionfind.size() - 1 ? res : new ArrayList<>();
    }
    
    private String find(String s) {
        String root = s;
        while (!unionfind.get(root).equals(root)) {
            root = unionfind.get(root);
        }
        
        String curr = s;
        while (!curr.equals(root)) {
            String parent = unionfind.get(curr);
            unionfind.put(curr, root);
            curr = parent;
        }
        
        return root;
    }
    
    private void connect(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);
        if (!rootA.equals(rootB)) {
            unionfind.put(rootA, rootB);
        }
    }
}