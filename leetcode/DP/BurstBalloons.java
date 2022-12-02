public class BurstBalloons {
    /* 312
     * 这道题非常非常非常巧妙 也非常经典 我认为它可以代表某种 非常规的思考最优子问题的模式 同时也是独特的dp迭代方向
     * 首先就是正常的思路 只能回溯 无法有最优子解 比如3 1 5 8 正常的思考的话 选择树就是每次选择戳破一个
     * (3 1 5 8)
     * (1 5 8) (3 5 8) (3 1 8) (3 1 5)
     * ... (3 5) (3 8) (5 8) 
     * 注意(3 5 8)(3 1 8)两个组合 如果考虑dp[3][8]这种记录方式的话 这两个组合其实是不一样的 能保证一样的只能完整的记录358 318的组合
     * 哎那不是如果用备忘录记录这个组合的话 阶乘复杂度的回溯就能被剪枝 似乎也能
     * anw如何用dp再去思考这道题 就是dp[i][j] = max(dp[i][k] + dp[k][j] + nums[i] * nums[j] * nums[k])
     * 其中k是ij之间最后一个戳破的气球 ikj -> ij 那么ij之间的最大值就是上述状态转移方程 这个解法是可以最优子解叠加的
     * 根据这个状态转移 对角线就是我们的base case 这里应该是0 因为我们会在dp size上加2 以及nums size加2 用来处理边界coins为1的情况
     * 而且这样正好是能够处理状态转移方程ik 和 kj互不冲突 因为是exclusive的 这样最后的答案就在dp[0][n+1]
     * 这里有一个很妙的思考循环方向的方法 就是把二维数组画出来 
     * 这样我们的答案在第一行右上角 base case在对角线 计算ij的时候 必须要先敲定ik kj 所以从下往上 从左往右
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n+2][n+2];
        int[] newNums = new int[n+2];
        for(int i = 1;i < n + 1;i++) {
            newNums[i] = nums[i-1];
        }
        newNums[0] = 1;
        newNums[n + 1] = 1;
        for(int i = n;i >= 0;i--) {
            for(int j = i + 1;j <= n + 1;j++) {
                int max = 0;
                for(int k = i+1;k < j;k++) {
                    max = Math.max(max, dp[i][k] + dp[k][j] + newNums[i] * newNums[j] * newNums[k]);
                }
                dp[i][j] = max;
            }
        }
        return dp[0][n+1];
    }
}
