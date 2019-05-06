// // Stack
// public class Vector2D implements Iterator<Integer> {
//     Stack<Object> stack = new Stack<>();

//     public Vector2D(List<List<Integer>> vec2d) {
//         for (int i = vec2d.size() - 1; i >= 0; i--) {
//             stack.push(vec2d.get(i));
//         }
//     }

//     @Override
//     public Integer next() {
//         return (int) stack.pop();
//     }

//     @Override
//     public boolean hasNext() {
//         while (!stack.isEmpty() && !(stack.peek() instanceof Integer)) {
//             List<Integer> vec = (List<Integer>) stack.pop();
//             for (int i = vec.size() - 1; i >= 0; i--) {
//                 stack.push(vec.get(i));
//             }
//         }
//         return !stack.isEmpty();
//     }

//     @Override
//     public void remove() {
//         stack.pop();
//     }
// }

// Iterator
public class Vector2D implements Iterator<Integer> {
    private Iterator<Integer> intItr;
    private Iterator<List<Integer>> listItr;

    public Vector2D(List<List<Integer>> vec2d) {
        listItr = vec2d.iterator();
        intItr = null;
    }

    @Override
    public Integer next() {
        return intItr.next();
    }

    @Override
    public boolean hasNext() {
        if (intItr != null && intItr.hasNext()) {
            return true;
        }
        while (listItr.hasNext()) {
            List<Integer> vec = listItr.next();
            intItr = vec.iterator();
            if (intItr.hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove() {
        intItr.next();
    }
}


/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */