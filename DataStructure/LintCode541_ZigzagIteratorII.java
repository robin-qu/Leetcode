public class ZigzagIterator2 {
    /*
    * @param vecs: a list of 1d vectors
    */
    
    private List<Iterator<Integer>> itrList;
    private int selector;
    
    public ZigzagIterator2(List<List<Integer>> vecs) {
        itrList = new ArrayList<>();
        for (List<Integer> list : vecs) {
            itrList.add(list.iterator());
        }
        selector = 0;
    }

    /*
     * @return: An integer
     */
    public int next() {
        while (!itrList.get(selector % itrList.size()).hasNext()) {
            selector++;
        }
        int res = itrList.get(selector % itrList.size()).next();
        selector++;
        return res;
    }

    /*
     * @return: True if has next
     */
    public boolean hasNext() {
        for (Iterator<Integer> itr : itrList) {
            if (itr.hasNext()) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your ZigzagIterator2 object will be instantiated and called as such:
 * ZigzagIterator2 solution = new ZigzagIterator2(vecs);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */