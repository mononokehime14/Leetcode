package backtrackdfs;

public class RottingOranges {
    /*
     * 这道题目的思路是DFS遍历 和岛屿问题类似 但是有非常非常多的细节
     * 1. 如何决定停止条件 首先越界或者value为0没有什么疑问 但是我们碰到2到底停不停呢
     * 停的话是不对的 应该继续扩散 但是不停的话 就会cycle了 我想的是保持一个count 当count = fresh orange数量
     * 也就是说我们完全的搞定了所有orange就停 同时每个orange遇到之后都变成0
     * 首先这样子的话 我们要把这个0变回来 因为之后还有loop不能更改grid 总之bug很多
     * 这里答案有一个很巧妙的思路 它不是把经过的grid value变成0 而是变成min 这样之后的停止条件里 多加一个 如果min > 那个grid上
     * 记录的min就不用继续递归了 这样既解决了visited的问题 还保证了我们之后能拿到最小值
     * 这样 解法的最后我们只要判断是否还有1没有变 加上找到最大的min 就能够得到答案
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                if(grid[i][j] != 2) continue;
                traverse(grid, i, j, m, n, 2);
            }   
        }
        int min = 2;
        for(int[] i: grid) {
            for(int j: i) {
                if(j == 1) return -1;
                min = Math.max(j, min);
            }
        }
        return min - 2;
    }
    private void traverse(int[][] grid, int x, int y, int m, int n, int min) {
        if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || (grid[x][y] > 1 && grid[x][y] < min)) { // last condition: rotten by another rotten orrange, that has a better solution, 相当于visited + best min
            return;
        }
        //System.out.println(x + " " + y + " " + min);
        grid[x][y] = min;
        min++;
        traverse(grid, x + 1, y, m, n, min);
        traverse(grid, x - 1, y, m, n, min);
        traverse(grid, x, y + 1, m, n, min);
        traverse(grid, x, y - 1, m, n, min);
    }
}
