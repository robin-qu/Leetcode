class MedianFinder {
    private final PriorityQueue<Integer> left;
    private final PriorityQueue<Integer> right;

    /** initialize your data structure here. */
    public MedianFinder() {
        this.left = new PriorityQueue<>(Collections.reverseOrder());
        this.right = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        left.offer(num);
        if (left.size() == right.size() + 2) {
            right.offer(left.poll());
        } else if (left.size() == right.size() + 1 && !right.isEmpty()) {
            right.offer(left.poll());
            left.offer(right.poll());
        }
    }
    
    public double findMedian() {
        return left.size() == right.size() ? ((double) left.peek() + (double) right.peek()) / 2 : (double) left.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */