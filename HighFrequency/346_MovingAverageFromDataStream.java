// O(1)time O(1)space
class MovingAverage {
    private Deque<Integer> deque;
    private int size;
    private double sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.deque = new LinkedList<>();
        this.size = size;
        this.sum = 0.0;
    }
    
    public double next(int val) {
        if (deque.size() == size) {
            sum -= deque.pollFirst();
        }
        
        deque.offerLast(val);
        sum += val;
        return sum / deque.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */