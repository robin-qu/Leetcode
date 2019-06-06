// // One stack, one iterator
// public class Vector2D implements Iterator<Integer> {
//     public Stack<Iterator<Integer>> stack;

//     public Vector2D(List<List<Integer>> vec2d) {
//         this.stack = new Stack<>()
//         for (int i = vec2d.size() - 1; i >= 0; i--) {
//             stack.push(vec2d.get(i).iterator());
//         }
//     }

//     @Override
//     public Integer next() {
//         return stack.peek().next();
//     }

//     @Override
//     public boolean hasNext() {
//         while (!stack.isEmpty()) {
//             if (stack.peek().hasNext()) {
//                 return true;
//             }
//             stack.pop();
//         }
//         return false;
//     }

//     @Override
//     public void remove() {}
// }

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
 
 // Two iterators
public class Vector2D implements Iterator<Integer> {
    private Iterator<List<Integer>> listItr;
    private Iterator<Integer> itr;

    public Vector2D(List<List<Integer>> vec2d) {
        listItr = vec2d.iterator();
        itr = null;
    }

    @Override
    public Integer next() {
        return itr.next();
    }

    @Override
    public boolean hasNext() {
        if (itr != null && itr.hasNext()) {
            return true;
        }
        while (listItr.hasNext()) {
            List<Integer> vec = listItr.next();
            itr = vec.iterator();
            if (itr.hasNext()) {
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