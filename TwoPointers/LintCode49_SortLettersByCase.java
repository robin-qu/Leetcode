public class Solution {
    /*
     * @param chars: The letter array you should sort by Case
     * @return: nothing
     */
    public void sortLetters(char[] chars) {
        if (chars == null || chars.length == 0) {
            return;
        }
        
        int left = 0;
        int right = chars.length - 1;
        
        while (left < right) {
            while (left < right && Character.isLowerCase(chars[left])) {
                left++;
            }
            while (left < right && Character.isUpperCase(chars[right])) {
                right--;
            }
            
            if (left <right) {
                swap(chars, left, right);
                left++;
                right--;
            }
        }
    }
    
    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i]  = chars[j];
        chars[j] = temp;
    } 
}