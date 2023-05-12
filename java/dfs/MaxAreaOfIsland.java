package dfs;

public class MaxAreaOfIsland {
    /* 解题思路和前面的岛屿问题是一模一样的 只需要DFS记录路径就可以了
     * 注意细节 这里甚至不用用记录路径的方法 可以直接每次从0开始 加在一起就可以了 因为只需要一个len数字嘛 又不用路径
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = 0;
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                max = Math.max(dfs(grid, i, j), max);
            }
        }
        return max;
    }
    private int dfs(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0) return 0;
        grid[x][y] = 0;
        int len = 1;
        len += dfs(grid, x-1, y);
        len += dfs(grid, x+1, y);
        len += dfs(grid, x, y-1);
        len += dfs(grid, x, y+1);
        return len;
    }
}
