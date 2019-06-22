public class MyQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;
    
    public MyQueue() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        s1.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        if (!s2.isEmpty()) {
            return s2.pop();
        }
        
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        
        return s2.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        if (!s2.isEmpty()) {
            return s2.peek();
        }
        
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        
        return s2.peek();
        
    }
}