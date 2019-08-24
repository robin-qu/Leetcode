// O(nlogn)time O(n)space
class Solution {
    class StrCmp implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String concat1 = a + b;
            String concat2 = b + a;
            return concat2.compareTo(concat1);
        }
    }
    
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        
        int n = nums.length;
        
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        Arrays.sort(strs, new StrCmp());
        if (strs[0].equals("0")) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(strs[i]);
        }
        
        return sb.toString();
    }
}