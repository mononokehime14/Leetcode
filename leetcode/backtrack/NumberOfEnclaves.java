package backtrack;

public class NumberOfEnclaves {
    /* 只能说和closed island是一模一样的思路 但是一开始想的方法傻逼了 不需要通过DFS来数数
     * 中间剩下的既然全部都是closed island 那么要求closed island的面积 我们直接数数就可以了
     * 而之前要用到DFS的原因是因为我们要数closed island的数量 所以要dfs推演
     */
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i = 0;i < m;i++) {
            if(grid[i][0] == 1) dfs(grid, i, 0, m, n);
            if(grid[i][n-1] == 1) dfs(grid, i, n-1, m, n);
        }
        for(int i = 0;i < n;i++) {
            if(grid[0][i] == 1) dfs(grid, 0, i, m, n);
            if(grid[m-1][i] == 1) dfs(grid, m-1, i, m, n);
        }
        int count = 0;
        for(int i = 1;i < m;i++) {
            for(int j = 1;j < n;j++) {
                if(grid[i][j] == 1) count++;
            }
        }
        return count;
    }
    private void dfs(int[][] grid, int x, int y, int m, int n) {
        if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0) return;
        grid[x][y] = 0;
        dfs(grid, x-1, y, m, n);
        dfs(grid, x+1, y, m, n);
        dfs(grid, x, y-1, m, n);
        dfs(grid, x, y+1, m, n);
        return;
    }
}
