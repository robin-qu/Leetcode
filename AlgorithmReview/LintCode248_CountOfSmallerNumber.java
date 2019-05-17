public class Solution {
    /**
     * @param A: An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        if (queries == null || queries.length == 0) {
            return new ArrayList<>();
        }
        
        Arrays.sort(A);
        List<Integer> res = new ArrayList<>();
        
        for (int query : queries) {
            res.add(binarySearch(A, query));
        }
        
        return res;
    }
    
    private int binarySearch(int[] A, int query) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = A.length - 1;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (query <= A[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (A[left] >= query) {
            return left;
        }
        if (A[right] >= query) {
            return right;
        }
        return right + 1;
    }
}