class Solution {
    public int getSum(int a, int b) {
        int sum = a ^ b;
        int carrys = (a & b) << 1;
        while (carrys != 0) {
            int oldSum = sum;
            sum = sum ^ carrys;
            carrys = (oldSum & carrys) << 1;
        }
        
        return sum;
    }
}