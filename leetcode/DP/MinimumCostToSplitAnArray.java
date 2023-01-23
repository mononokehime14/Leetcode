public class MinimumCostToSplitAnArray {
    /* 2547
     * 颇难想对细节 基本上照抄了答案才过的
     * 首先是被迷惑了一下 n的length限定是n**5 我以为n**2的算法是过不了的 所以没有思考dp 在那里思考是不是贪心
     * 但是实际上dp才是解法 这里要先算出来从i到j的subarray中的trim(subarray) length 这就n**2了 具体的实现不难
     * 题目规定了数值小于length 故此可以用array替代hashmap
     * 然后是dp 状态转移是非常粗暴的在当前的i前寻找切口切开 最后一个subarray的cost+dp的
     * 这里我自己的dp定义没有调整对 时间来不及了
     * 答案的dp定义是 DP[i] as the minimum cost for the nums[0..i-1].
     * 在这个定义下DP[i] = min(DP[j]+k+trimmed[j][i-1]), where 0 <= j <= i-1
     */
    public int minCost(int[] nums, int k) {
        int n = nums.length;
        int[][] trim = new int[n][n];
        for(int i = 0;i < n;i++) {
            int sum = 0;
            int[] freq = new int[n];
            for(int j = i;j < n;j++) {
                freq[nums[j]]++;
                if(freq[nums[j]] == 2) {
                    sum += 2;
                }else if(freq[nums[j]] > 2) {
                    sum++;
                }
                trim[i][j] = sum;
            }
        }
        int[] dp = new int[n+1];
        for(int i = 1;i <= n;i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 0;j < i;j++) {
                min = Math.min(dp[j] + k + trim[j][i-1], min);
            }
            dp[i] = min;
        }
        return dp[n];
        // int[] dp = new int[n];
        // dp[0] = k;
        // for(int i = 1;i < n;i++) {
        //     int min = Integer.MAX_VALUE;
        //     for(int j = 0;j < i;j++) {
        //         min = Math.min(dp[j] + k + trim[j+1][i], min);
        //     }
        //     dp[i] = min;
        // }
        // return dp[n-1];
    }
}
