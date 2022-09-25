package backtrack;

public class NumberOfClosedIsland {
    /* 岛屿系列的第二步 和基本型相比 就是不能假设每一个0都会形成一个岛屿了 如果其碰到了边缘
     * 那便不是一个closed island 所以我的方法就是dfs return做一个分别 碰到1 return和碰到边缘return分开判断
     * 还有一个更加巧妙的思路 我们利用我们的淹没DFS会把原数值改成1的特点 先把周围一圈的DFS一遍 但是不用增加count
     * 然后中间剩下的必然是close island 因为必然和边缘隔着海水 不然就在前一轮被淹掉了
     * 这样做之后果然时间到了97%
     */
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for(int i = 0;i < m;i++) {
            dfs(grid, i, 0, m, n);
            dfs(grid, i, n-1, m, n);
        }
        for(int i = 0;i < n;i++) {
            dfs(grid, 0, i, m, n);
            dfs(grid, m-1, i, m, n);
        }
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                if(grid[i][j] == 1) continue;
                dfs(grid, i, j, m, n);
                count++;
            }
        }
        return count;
    }
    private void dfs(int[][] grid, int x, int y, int m, int n) {
        if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 1) {
            return;
        }
        grid[x][y] = 1;
        dfs(grid, x, y-1, m, n);
        dfs(grid, x, y+1, m, n);
        dfs(grid, x-1, y, m, n);
        dfs(grid, x+1, y, m, n);
    }
    // public int closedIsland(int[][] grid) {
    //     int m = grid.length;
    //     int n = grid[0].length;
    //     int count = 0;
    //     for(int i = 0;i < m;i++) {
    //         for(int j = 0;j < n;j++) {
    //             if(grid[i][j] == 1) continue;
    //             if(dfs(grid, i, j, m, n)) count++;
    //         }
    //     }
    //     return count;
    // }
    // private boolean dfs(int[][] grid, int x, int y, int m, int n) {
    //     if(x < 0 || x >= m || y < 0 || y >= n) {
    //         return false;
    //     }  
    //     if(grid[x][y] == 1) return true;
    //     grid[x][y] = 1;
    //     boolean up = dfs(grid, x, y-1, m, n);
    //     boolean down = dfs(grid, x, y+1, m, n);
    //     boolean left = dfs(grid, x-1, y, m, n);
    //     boolean right = dfs(grid, x+1, y, m, n);
    //     if(!up || !down || !left || !right) return false;
    //     return true;
    // }
}
