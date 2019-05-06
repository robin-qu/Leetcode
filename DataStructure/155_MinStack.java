// // One stack
// class MinStack {
//     private List<Integer> list;
//     private int min;

//     /** initialize your data structure here. */
//     public MinStack() {
//         list = new LinkedList<Integer>();
//         min = Integer.MAX_VALUE;
//     }
    
//     public void push(int x) {
//         if (x <= min) {
//             list.add(min);
//             min = x;
//         }
//         list.add(x);
//     }
    
//     public void pop() {
//         if (list.remove(list.size() - 1) == min){
//             min = list.remove(list.size() - 1);
//         } 
//     }
    
//     public int top() {
//         return list.get(list.size() - 1);
//     }
    
//     public int getMin() {
//         return min;
//     }
// }


// Two stacks
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(x, minStack.peek()));
        }
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */