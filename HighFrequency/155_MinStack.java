class MinStack {
    private Stack<Integer> stack;
    private int min;
    
    /** initialize your data structure here. */
    public MinStack() {
        this.stack = new Stack<>();
        this.min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (x <= min) {
            stack.push(min);  // backup previous min value
            min = x;  // update min value
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