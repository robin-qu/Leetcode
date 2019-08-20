/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf); 
 */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    
    private int bufferPointer = 0;
    private int bufferCount = 0;
    private char[] prevBuffer = new char[4];
    
    public int read(char[] buf, int n) {
        int pointer = 0;
        while (pointer < n) {
            if (bufferPointer < bufferCount) {  // there are still number in previous buffer
                buf[pointer++] = prevBuffer[bufferPointer++];
            } else {
                bufferCount = read4(prevBuffer);
                bufferPointer = 0;
                if (bufferCount == 0) {  // finish reading
                    break;
                }
            }
        }
        
        return pointer;
    }
}