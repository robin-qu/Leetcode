// O(n)time O(n)space
class Solution {
    public String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roms = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        int i = 0;
        StringBuilder sb = new StringBuilder();
        
        while (num > 0) {
            while (num / nums[i] > 0) {
                num -= nums[i];
                sb.append(roms[i]);
            }
            i++;
        }
        
        return sb.toString();
    }
}