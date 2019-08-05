// O(nm)time O(nm)space
class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        if (paths == null || paths.length == 0) {
            return new ArrayList<>();
        }
        
        int n = paths.length;
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            String[] files = paths[i].split(" ");
            
            for (int j = 1; j < files.length; j++) {
                String fileName = getFileName(files[j]);
                String content = getContent(files[j]);
                
                if (!map.containsKey(content)) {
                    map.put(content, new ArrayList<>());
                }
                map.get(content).add(files[0] + '/' + fileName);
            }
        }
        
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                res.add(map.get(key));
            }
        }
        
        return res;
    }
    
    private String getContent(String s) {
        return s.substring(s.indexOf('.') + 5, s.length() - 1);
    }
    
    private String getFileName(String s) {
        return s.substring(0, s.indexOf('.') + 4);
    }
}