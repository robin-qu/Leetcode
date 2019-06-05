// // two queues
// class MyStack {
//     private Queue<Integer> q1;
//     private Queue<Integer> q2;
    
//     /** Initialize your data structure here. */
//     public MyStack() {
//         this.q1 = new LinkedList<>();
//         this.q2 = new LinkedList<>();
//     }
    
//     /** Push element x onto stack. */
//     public void push(int x) {
//         q1.offer(x);
//     }
    
//     /** Removes the element on top of the stack and returns that element. */
//     public int pop() {
//         while (q1.size() > 1) {
//             q2.offer(q1.poll());
//         }
//         int temp = q1.poll();
//         while (!q2.isEmpty()) {
//             q1.offer(q2.poll());
//         }
        
//         return temp;
//     }
    
//     /** Get the top element. */
//     public int top() {
//         while (q1.size() > 1) {
//             q2.offer(q1.poll());
//         }
//         int temp = q1.peek();
//         q2.offer(q1.poll());
//         while (!q2.isEmpty()) {
//             q1.offer(q2.poll());
//         }
        
//         return temp;
//     }
    
//     /** Returns whether the stack is empty. */
//     public boolean empty() {
//         return q1.isEmpty() && q2.isEmpty();
//     }
// }

// /**
//  * Your MyStack object will be instantiated and called as such:
//  * MyStack obj = new MyStack();
//  * obj.push(x);
//  * int param_2 = obj.pop();
//  * int param_3 = obj.top();
//  * boolean param_4 = obj.empty();
//  */


// one queue
class MyStack {
    private Queue<Integer> q;
    
    /** Initialize your data structure here. */
    public MyStack() {
        this.q = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        q.offer(x);
        for (int i = 0; i < q.size() - 1; i++) {
            q.offer(q.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return q.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */