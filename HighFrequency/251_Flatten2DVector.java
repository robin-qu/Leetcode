class Vector2D {
    private List<List<Integer>> vector;
    private Iterator<List<Integer>> itr1;  // outer iterator
    private Iterator<Integer> itr2;  // inner iterator

    public Vector2D(int[][] v) {
        this.vector = new ArrayList<>();
        for (int i = 0; i < v.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < v[i].length; j++) {
                list.add(v[i][j]);
            }
            vector.add(list);
        }
        this.itr1 = vector.iterator();
        if (itr1.hasNext()) {
            this.itr2 = itr1.next().iterator();
        }
    }
    
    public int next() {
        hasNext();
        return itr2.next();
    }
    
    public boolean hasNext() {
        while ((itr2 == null || !itr2.hasNext()) && itr1.hasNext()) {
            itr2 = itr1.next().iterator();
        }
        
        return itr2 != null && itr2.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(v);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */