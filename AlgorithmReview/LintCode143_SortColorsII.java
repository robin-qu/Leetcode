public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }

        partition(colors, 0, colors.length - 1, 1, k);
    }
    
    private void partition(int[] colors, int start, int end, int k1, int k2) {
        if (k1 >= k2) {
            return;
        }
        
        int pivot = k1 + (k2 - k1) / 2;
        int left = start;
        int right = end;
        
        while (left <= right) {
            while (left <= right && colors[left] <= pivot) {
                left++;
            }
            
            while (left <= right && colors[right] > pivot) {
                right--;
            }
            
            if (left <= right) {
                swap(colors, left, right);
                left++;
                right--;
            }
        }
        
        partition(colors, start, left - 1, k1, pivot);
        partition(colors, left, end, pivot + 1, k2);
    }
    
    private void swap(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }
}