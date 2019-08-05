// Bit manipulation O(n)time O(1)space
class Solution {
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return true;
        }
        
        int n = data.length;
        int idx = 0;
        
        while (idx < n) {
            int bytes = 0;
            int mask = 1 << 7;
            while ((mask & data[idx]) > 0) {
                bytes++;
                mask = mask >> 1;
            }

            if (bytes == 1 || bytes > 4 || idx + bytes > n) {
                return false;
            }
            
            if (bytes == 0) {
                idx ++;
                continue;
            }

            for (int i = 1; i < bytes; i++) {
                if ((data[idx + i] & (1 << 7)) == 0) {
                    return false;
                }

                if ((data[idx + i] & (1 << 6)) != 0) {
                    return false;
                }
            }
            
            idx += bytes;
        }
        
        return true;
    }
}