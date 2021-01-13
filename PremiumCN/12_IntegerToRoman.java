class Solution {
    public String intToRoman(int num) {
        int[] nums = new int[] {1000, 500, 100, 50, 10, 5, 1};
        char[] ss = new char[] {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            int count = num / nums[i];
            if (count == 4) {
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ss[i - 1]) {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(ss[i]).append(ss[i - 2]);
                } else {
                    sb.append(ss[i]).append(ss[i - 1]);
                }
            } else {
                for (int j = 0; j < count; j++) {
                    sb.append(ss[i]);
                }
            }
            num -= count * nums[i];
        }
        return sb.toString();
    }
}