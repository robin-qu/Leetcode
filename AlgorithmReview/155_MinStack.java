// // Two stacks
// class MinStack {
//     private Stack<Integer> stack;
//     private Stack<Integer> mins;

//     /** initialize your data structure here. */
//     public MinStack() {
//         stack = new Stack<Integer>();
//         mins = new Stack<Integer>();
//     }
    
//     public void push(int x) {
//         stack.push(x);
//         if (mins.isEmpty()) {
//             mins.push(x);
//         } else {
//             mins.push(Math.min(x, mins.peek()));
//         }
//     }
    
//     public void pop() {
//         stack.pop();
//         mins.pop();
//     }
    
//     public int top() {
//         return stack.peek();
//     }
    
//     public int getMin() {
//         return mins.peek();
//     }
// }


// One stack
class MinStack {
    private Stack<Integer> stack;
    private int min;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        int curr = stack.pop();
        if (curr == min) {
            min = stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
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