// // Ordinary DFS backtracking (TLE)
// class Solution {
//     class ItineraryComparator implements Comparator<List<String>> {
//         public int compare(List<String> i1, List<String> i2) {
//             for (int i = 0; i < i1.size(); i++) {
//                 if (i1.get(i).compareTo(i2.get(i)) != 0) {
//                     return i1.get(i).compareTo(i2.get(i));
//                 }
//             }
            
//             return 0;
//         }
//     }
    
//     public List<String> findItinerary(List<List<String>> tickets) {
//         if (tickets == null || tickets.size() == 0) {
//             return new ArrayList<>();
//         }
        
//         List<List<String>> sols = new ArrayList<>();
//         List<String> sol = new ArrayList<>();
//         sol.add("JFK");
//         boolean[] used = new boolean[tickets.size()];
//         dfs("JFK", tickets, used, sol, sols);
        
//         if (sols.size() == 0) {
//             return new ArrayList<>();
//         }
        
//         Collections.sort(sols, new ItineraryComparator());
        
//         return sols.get(0);
//     }
    
//     private void dfs(String departure, List<List<String>> tickets, boolean[] used, List<String> sol, List<List<String>> sols) {
//         if (sol.size() == tickets.size() + 1) {
//             sols.add(new ArrayList<>(sol));
//             return;
//         }
        
//         for (int i = 0; i < tickets.size(); i++) {
//             List<String> ticket = tickets.get(i);
//             if (ticket.get(0).equals(departure) && !used[i]) {
//                 sol.add(ticket.get(1));
//                 used[i] = true;
//                 dfs(ticket.get(1), tickets, used, sol, sols);
//                 sol.remove(sol.size() - 1);
//                 used[i] = false;
//             }
//         }
//     }
// }



// Hierholzer's  algorithm
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        if (tickets == null || tickets.size() == 0) {
            return new ArrayList<>();
        }
        
        Map<String, PriorityQueue<String>> graph = buildGraph(tickets);
        List<String> res = new ArrayList<>();
        
        dfs("JFK", graph, res);
        
        return res;
    }
    
    private void dfs(String departure, Map<String, PriorityQueue<String>> graph, List<String> res) {        
        PriorityQueue<String> pq = graph.get(departure);
        
        while (pq != null && !pq.isEmpty()) {
            String arrival = pq.poll();
            dfs(arrival, graph, res);
        }
        
        res.add(0, departure);
    }
    
    private Map<String, PriorityQueue<String>> buildGraph(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        
        for (List<String> ticket : tickets) {
            String departure = ticket.get(0);
            String arrival = ticket.get(1);
            if (!map.containsKey(departure)) {
                map.put(departure, new PriorityQueue<String>());
            }
            map.get(departure).offer(arrival);
        }
        
        return map;
    }
}