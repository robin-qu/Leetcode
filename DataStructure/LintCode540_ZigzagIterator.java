// public class ZigzagIterator {
//     /*
//     * @param v1: A 1d vector
//     * @param v2: A 1d vector
//     */
    
//     private int curr1;
//     private int curr2;
//     private List<Integer> v1;
//     private List<Integer> v2;
//     private boolean selector;
    
//     public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
//         curr1 = 0;
//         curr2 = 0;
//         this.v1 = v1;
//         this.v2 = v2;
//         selector = true;
//     }

//     /*
//      * @return: An integer
//      */
//     public int next() {
//         int res;
//         if (selector) {
//             if (curr1 < v1.size()) {
//                 res = v1.get(curr1);
//                 curr1++;
//             } else {
//                 res = v2.get(curr2);
//                 curr2++;
//             }
//         } else {
//             if (curr2 < v2.size()) {
//                 res = v2.get(curr2);
//                 curr2++;
//             } else {
//                 res = v1.get(curr1);
//                 curr1++;
//             }
//         }
//         selector = !selector;
//         return res;
//     }

//     /*
//      * @return: True if has next
//      */
//     public boolean hasNext() {
//         return curr1 < v1.size() || curr2 < v2.size();
//     }
// }


public class ZigzagIterator {
    /*
    * @param v1: A 1d vector
    * @param v2: A 1d vector
    */
    
    private Iterator<Integer> itr1;
    private Iterator<Integer> itr2;
    private boolean selector;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        itr1 = v1.iterator();
        itr2 = v2.iterator();
        selector = true;
    }

    /*
     * @return: An integer
     */
    public int next() {
        int res;
        if (selector) {
            res = itr1.hasNext() ? itr1.next() : itr2.next();
        } else {
            res = itr2.hasNext() ? itr2.next() : itr1.next();
        }
        selector = !selector;
        return res;
    }

    /*
     * @return: True if has next
     */
    public boolean hasNext() {
        return itr1.hasNext() || itr2.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator solution = new ZigzagIterator(v1, v2);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */