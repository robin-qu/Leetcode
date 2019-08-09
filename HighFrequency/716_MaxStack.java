// stack popmaxO(n)time othersO(1)time O(n)space
class MaxStack {
    private Stack<Integer> stack;
    private int max;

    /** initialize your data structure here. */
    public MaxStack() {
        this.stack = new Stack<>();
        this.max = Integer.MIN_VALUE;
    }
    
    public void push(int x) {
        if (x >= max) {
            stack.push(max);
            max = x;
        }
        stack.push(x);
    }
    
    public int pop() {
        int res = stack.pop();
        if (res == max) {
            max = stack.pop();
        }
        
        return res;
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return max;
    }
    
    public int popMax() {
        Stack<Integer> temp = new Stack<>();
        while (stack.peek() != max) {
            temp.push(stack.pop());
        }
        int res = stack.pop();
        max = stack.pop();
        while (!temp.isEmpty()) {
            push(temp.pop());
        }
        
        return res;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */