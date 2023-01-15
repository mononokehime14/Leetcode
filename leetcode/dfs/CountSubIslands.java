package dfs;

public class CountSubIslands {
    /* 这道题需要一些巧妙的思路 思路的基本和closed island那道题 先他娘的把不合适的先淹掉
     * 我本来的做法又再一次的陷入了判断的陷阱 我想通过在回溯/DFS中判断node是否能是sub岛屿 return boolean
     * 这个做法也许是可以的 但是我他娘的过不了一个test case 我觉得没啥问题 但是就是过不了
     * 我觉得可能这种方法处理edge case确实比较难理清逻辑
     * 正确的方法要简单得多 首先如果任何一个grid2的岛屿中 有任何一个陆地在grid1在海 那么整个岛屿都不是sub island了
     * 所以可以用第一个循环把这些岛屿全部淹掉
     * 剩下的就自然而然 通通是sub islands
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        for(int i = 0;i < grid2.length;i++) {
            for(int j = 0;j < grid2[0].length;j++) {
                if(grid2[i][j] == 1 && grid1[i][j] == 0) {
                    dfs(grid2, i, j);
                }
            }
        }
        for(int i = 0;i < grid2.length;i++) {
            for(int j = 0;j < grid2[0].length;j++) {
                if(grid2[i][j] == 0) continue;
                dfs(grid2, i, j);
                count++;
            }
        }
        return count;
    }
    private void dfs(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0) return;
        grid[x][y] = 0;
        dfs(grid, x-1, y);
        dfs(grid, x+1, y);
        dfs(grid, x, y-1);
        dfs(grid, x, y+1);
    }
}
