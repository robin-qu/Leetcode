// O(n)time O(n)space
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) {
            return asteroids;
        }
        
        int n = asteroids.length;
        Stack<Integer> stack = new Stack<>();
        
        for (int a : asteroids) {
            if (a > 0) {
                stack.push(a);
            } else {
                boolean isExploded = false;
                while (!stack.isEmpty() && stack.peek() > 0) {
                    if (stack.peek() < -a) {
                        stack.pop();
                    } else if (stack.peek() > -a) {
                        isExploded = true;
                        break;
                    } else {
                        stack.pop();
                        isExploded = true;
                        break;
                    }
                }
                
                if (!isExploded) {
                    stack.push(a);
                }
            }
        }
        
        int m = stack.size();
        int[] res = new int[m];
        
        for (int i = m - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        
        return res;
    }
}