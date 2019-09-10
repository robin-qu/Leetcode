/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

// dfs O(mn)time O(mn)space
class Solution {
    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0, 0, 1, new HashSet<String>());
    }
    
    private void dfs(Robot robot, int x, int y, int dirX, int dirY, Set<String> visited) {
        robot.clean();
        visited.add(x + " " + y);
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dirX;
            int ny = y + dirY;
            
            if (!visited.contains(nx + " " + ny) && robot.move()) {
                dfs(robot, nx, ny, dirX, dirY, visited);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            
            robot.turnRight();
            
            int temp = dirX;
            dirX = dirY;
            dirY = -temp;
        }
    }
}