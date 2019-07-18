public class Vector2D implements Iterator<Integer> {
    private Iterator<List<Integer>> itr1;
    private Iterator<Integer> itr2;
    
    public Vector2D(List<List<Integer>> vec2d) {
        itr1 = vec2d.iterator();
        if (itr1.hasNext()) {
            itr2 = itr1.next().iterator();
        }
    }

    @Override
    public Integer next() {
        return itr2.next();
    }

    @Override
    public boolean hasNext() {
        if (itr2 == null) {
            return false;
        }
        
        if (itr2.hasNext()) {
            return true;
        }
        
        while (itr1.hasNext()) {
            itr2 = itr1.next().iterator();
            if (itr2.hasNext()) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public void remove() {}
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */