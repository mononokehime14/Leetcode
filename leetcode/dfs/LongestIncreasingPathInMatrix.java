package dfs;

public class LongestIncreasingPathInMatrix {
    /*
     * 一开始用DFS超时了 答案是要用DP + DFS
     * 一开始的情况每个点辐射出去 trace每个sequence然后将长度和globalMax比较更新 这个方法超时的问题很好想 就是每一个点辐射一次
     * 理论上的时间复杂度是4 ** N 然后我就思考能不能用DP 我的想法是能够记录dp[i][j]延伸出去的最长increasing path
     * 但是这个想法碰到一个问题 就是如果方向不一致的怎么办 (x, y) -> (i, j), 从(i, j)延伸出去的不一定和xy连得上呀
     * 然后就走歪了路 妈的01matrix还在害人 我就想正着一遍反着一遍 但是这个算法是不正确的 具体没推理
     * 事实上 确实应该用dp[i][j] ij是起点 就必然是sequence的最小的 xy到ij如果xy比ij小 那么必然可以顺延这个sequence 反之则会提前return
     * 注意track或者是visited还是需要的
     * 这样时间复杂度是剪枝过后的N^2
     */
    public int longestIncreasingPath(int[][] matrix) {
        int max = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        boolean[][] track = new boolean[m][n];
        for(int i = 0;i < m;i++) {
            Arrays.fill(dp[i], -1);
        }
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                max = Math.max(traverse(matrix, dp, track, m, n, i, j, Integer.MIN_VALUE), max);
            }
        }
        return max;
    }
    private int traverse(int[][] matrix, int[][] dp, boolean[][] track, int m, int n, int x, int y, int prev) {
        if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= prev) return 0;
        if(dp[x][y] != -1) return dp[x][y];
        track[x][y] = true;
        int down = traverse(matrix, dp, track, m, n, x + 1, y, matrix[x][y]);
        int up = traverse(matrix, dp,track, m, n, x - 1, y, matrix[x][y]);
        int right = traverse(matrix, dp,track, m, n, x, y + 1, matrix[x][y]);
        int left = traverse(matrix, dp,track, m, n, x, y - 1, matrix[x][y]);
        int currentMax = Math.max(Math.max(Math.max(down, up), right), left) + 1;
        dp[x][y] = currentMax;
        track[x][y] = false;
        return currentMax;
    }
    // public int longestIncreasingPath(int[][] matrix) {
    //     int m = matrix.length, n = matrix[0].length;
    //     int[][] dp = new int[m][n];
    //     dp[0][0] = 1;
    //     for(int j = 1;j < n;j++) {
    //         if(matrix[0][j] > matrix[0][j-1]) {
    //             dp[0][j] = dp[0][j-1] + 1;
    //         }
    //     }
    //     for(int i = 1;i < m;i++) {
    //         if(matrix[i][0] > matrix[i-1][0]) {
    //             dp[i][0] = dp[i-1][0] + 1;
    //         }
    //     }
    //     for(int i = 1;i < m;i++) {
    //         for(int j = 1;j < n;j++) {
    //             int up = matrix[i][j] > matrix[i-1][j] ? dp[i-1][j] : 0;
    //             int left = matrix[i][j] > matrix[i][j-1] ? dp[i][j-1] : 0;
    //             dp[i][j] = Math.max(up, left) + 1;
    //         }
    //     }
    //     for(int i = m - 1;i >= 0;i--) {
    //         for(int j = n - 1;j >= 0;j--) {
    //             int down = i+1 < m && matrix[i][j] > matrix[i+1][j] ? dp[i+1][j] : 0;
    //             int right = j+1 < n && matrix[i][j] > matrix[i][j+1] ? dp[i][j+1] : 0;
    //             dp[i][j] = Math.max(Math.max(down, right) + 1, dp[i][j]);
    //         }
    //     }
    //     int max = 1;
    //     for(int[] i: dp) {
    //         for(int j: i) {
    //             max = Math.max(max, j);
    //         }
    //     }
    //     return max;
    // }
}
