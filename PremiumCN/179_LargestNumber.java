class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return String.valueOf(0);
        }
        
        int n = nums.length;
        
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        
        Collections.sort(list, (a, b) -> (b + a).compareTo(a + b));
        
        for (String s : list) {
            sb.append(s);
        }
        
        String res = sb.toString();
        if (res.startsWith("0")) {
            return "0";
        }
        
        return res;
    }
}