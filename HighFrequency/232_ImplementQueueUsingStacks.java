// O(1)amotizedtime O(n)space
class MyQueue {
    private Stack<Integer> in;
    private Stack<Integer> out;

    /** Initialize your data structure here. */
    public MyQueue() {
        this.in = new Stack<>();
        this.out = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        in.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek();
        return out.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (!out.isEmpty()) {
            return out.peek();
        }
        
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        
        return out.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */