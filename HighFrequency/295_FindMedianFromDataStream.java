// PriorityQueue O(logn)time O(n)space
class MedianFinder {
    private PriorityQueue<Integer> left;  // smaller half
    private PriorityQueue<Integer> right;  // bigger half, size no greater tahn left

    /** initialize your data structure here. */
    public MedianFinder() {
        this.left = new PriorityQueue<>(Collections.reverseOrder());
        this.right = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        left.offer(num);
        right.offer(left.poll());
        
        if (right.size() > left.size()) {
            left.offer(right.poll());
        }
    }
    
    public double findMedian() {
        if (left.size() != right.size()) {
            return left.peek();
        } else {
            if (left.isEmpty()) {
                return 0.0;
            }
            
            return (left.peek() + right.peek()) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */