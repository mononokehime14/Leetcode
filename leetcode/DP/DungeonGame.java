public class DungeonGame {
    /* 174
     * DP的状态转移一开始想不出来 因为如果定义DP[i][j]是到ij为止的minimum health的 DP[i][j]依赖上面和左边的
     * 但是然后呢 要怎么加上当前的值 我觉得问题在于这是没法记录到路径上的血包加血 没办法从之前的状态推导出当前的状态
     * 一怒之下用了回溯 用了备忘录ij 但是是错误的 原因就是状态转移不能从00开始正常递推的原因 前面算出来的ij还不是最优解
     * 加入cost ijcost组成key 则备忘录完全无用 TLE
     * 实际上 这道题的递推要自底向上 DP[i][j]是从ij出发到右下角的minimum initial health
     * 这样的话 依赖下方和右边的dp 取其小的 然后当前的ij正是起点 起点有血包则minimum initial health就可以消掉点 不然就累加
     * 这里要注意用1封底 有多余的血量或者0 initial health应该是1
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = dungeon[m-1][n-1] < 0 ? 1 - dungeon[m-1][n-1] : 1;
        for(int i = m - 2;i >= 0;i--) {
            dp[i][n-1] = Math.max(dp[i+1][n-1] - dungeon[i][n-1], 1);
        }
        for(int i = n - 2;i >= 0;i--) {
            dp[m-1][i] = Math.max(dp[m-1][i+1] - dungeon[m-1][i], 1);
        }
        for(int i = m-2;i >= 0;i--) {
            for(int j = n-2;j >= 0;j--) {
                dp[i][j] = Math.max(Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }
    /* 错误的回溯 fuck回溯 fuck all */
    // private HashMap<String, Integer> memo;
    // public int calculateMinimumHP(int[][] dungeon) {
    //     memo = new HashMap<>();
    //     return backtrack(dungeon, 0, 0, 0) + 1;
    // }
    // private int backtrack(int[][] d, int i, int j, int cost) {
    //     if(i == d.length || j == d[0].length) return Integer.MAX_VALUE;
    //     String state = i + "," + j + "," + cost;
    //     if(memo.containsKey(state)) return memo.get(state);
    //     cost += d[i][j];
    //     int min = 0;
    //     if(cost < 0) {
    //         min += cost * -1;
    //         cost = 0;
    //     } 
    //     int choice = Math.min(backtrack(d, i + 1, j, cost), backtrack(d, i, j + 1, cost));
    //     if(choice != Integer.MAX_VALUE) min += choice;
    //     memo.put(state, min);
    //     return min;
    // }
}
