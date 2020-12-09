class MedianFinder {
    private PriorityQueue<Integer> left;
    private PriorityQueue<Integer> right;

    /** initialize your data structure here. */
    public MedianFinder() {
        this.left = new PriorityQueue<>((a, b) -> b - a);
        this.right = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        left.offer(num);
        if (left.size() > right.size() + 1) {
            right.offer(left.poll());
        } else {
            right.offer(left.poll());
            left.offer(right.poll());
        }
    }
    
    public double findMedian() {
        if (left.size() != right.size()) {
            return (double) left.peek();
        }

        return ((double) left.peek() + (double) right.peek()) / 2; 
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */