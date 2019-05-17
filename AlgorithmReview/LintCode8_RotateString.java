public class Solution {
    /**
     * @param str: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        if (str == null || str.length <= 1) {
            return;
        }
        
        for (int i = 0; i < offset % str.length; i++) {
            rotateOnce(str);
        }
    }
    
    private void rotateOnce(char[] str) {
        char temp = str[str.length - 1];
        for (int i = str.length - 1; i > 0; i--) {
            str[i] = str[i - 1];
        }
        str[0] = temp;
    }
} 