// public class MinStack {
//     private Stack<Integer> vals;
//     private Stack<Integer> mins;
    
//     public MinStack() {
//         this.vals = new Stack<>();
//         this.mins = new Stack<>();
//     }

//     /*
//      * @param number: An integer
//      * @return: nothing
//      */
//     public void push(int number) {
//         vals.push(number);
//         if (mins.isEmpty()) {
//             mins.push(number);
//             return;
//         }
        
//         mins.push(number < mins.peek() ? number : mins.peek());
//     }

//     /*
//      * @return: An integer
//      */
//     public int pop() {
//         mins.pop();
//         return vals.pop();
//     }

//     /*
//      * @return: An integer
//      */
//     public int min() {
//         return mins.peek();
//     }
// }


// Optimized by using only one stack
public class MinStack {
    private Stack<Integer> stack;
    private int min;
    
    public MinStack() {
        this.stack = new Stack<>();
        this.min = Integer.MAX_VALUE;
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        if (number <= min) {
            stack.push(min);
            min = number;
        }
        stack.push(number);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        int val = stack.pop();
        if (val == min) {
            min = stack.pop();
        }
        return val;
    }

    /*
     * @return: An integer
     */
    public int min() {
        return min;
    }
}