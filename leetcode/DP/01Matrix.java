public class 01Matrix {
    /**
     * 这道题第一开始感觉是要用DP DP = min(四个方向) + 1
     * 但是这样的话 无法做到一遍迭代完成更新 因为最小值有可能来自下面或者右边 这就和N皇后的一行行放皇后不同了
     * 于是我觉得可能是需要Bellman Ford 算全源最小路径 然后不知道怎么写BF
     * 实际上这道题可以做两遍DP 第一遍正常 最小值取决于left和top 第二遍反过来 最小值取决于bottom和right
     * 为什么第二遍能够推出正确的值呢 我觉得是这道题的特殊性 最小路径要不就是上半部分来 要不就是下半部分来 这两个set能够
     * 组成完整的solution space
     * 然后注意这道题的base case 我们给第一行的1赋了最大值 因为自信第二遍迭代它能够作为end case推出最小值
     * 最大值我们给的不是Int max(在这道题可能也可以) 而是用了这个问题中路径的最大值n + m
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length, max = n + m;
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                if(mat[i][j] == 0) continue;
                int left = i == 0 ? max : mat[i-1][j];
                int top = j == 0 ? max : mat[i][j-1];
                mat[i][j] = Math.min(left, top) + 1;
            }
        }
        for(int i = m - 1;i >= 0;i--) {
            for(int j = n - 1;j >= 0;j--) {
                if(mat[i][j] == 0) continue;
                int left = i == m - 1 ? max : mat[i+1][j];
                int top = j == n - 1 ? max : mat[i][j+1];
                mat[i][j] = Math.min(Math.min(left, top) + 1, mat[i][j]);
            }
        }
        return mat;
    }
}
