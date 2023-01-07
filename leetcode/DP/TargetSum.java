public class TargetSum {
    /* 494
     * 这道题可以代表某种 计算combination -> 划分子集问题 -> 背包问题的转化思路
     * 首先是思路 这道题回溯 DP都能做 回溯非常简单 每次选择加还是减 自然形成了2的N次方的递归树
     * 这时候注意重叠子问题 index和target的组合可以重叠 发现方法是看回溯的调用 
     * backtrack(nums, index + 1, target - nums[index]) 
     * backtrack(nums, index + 1, target + nums[index])
     * 比如nums[index] = 0 这里直接就造成了两个一摸一样的call 所以相应的备忘录一下 时间复杂度应该是target * N
     * 
     * DP则需要转换思路 一开始是想DP[i][j] = SUM( DP[i-1][j-nums[i]] + DP[i-1][j+nums[i]]) 这里首先target可以为负数 第二个column有点麻烦 
     * 再者是base case有点不好定
     * 我们可以转化成划分子集问题 SumA是+的数字和 SumB是-的数字和 那么
     * SumA - SumB = target; SumA = target + SumB; SumA * 2 = target + SumB + SumA = target + SumAll;
     * 这样SumA就是(target + SumAll) / 2 我们只要找到能够加到这个值的SumA集合数量就可以了 这就转成了背包问题
     * 这里base case是dp[i][0] = 1 不加也是一种方法 这是比较特殊的base case
     * 注意只需要初始化dp[0][0] 之后的loop中 最左边的column可以用上一行的更新成1
     * 注意这里dp[i-1][j - nums[i-1]]而不是dp[i][j - nums[i-1]] 因为dp[i]已经是加过i更新好了的 所以应该依靠上一行i-1的值
     * 同时 空间压缩的时候 第二个loop要反向 原因是
     * 因为二维压缩到一维的根本原理是，dp[j] 和 dp[j-nums[i-1]] 还没被新结果覆盖的时候，相当于二维 dp 中的 dp[i-1][j] 和 dp[i-1][j-nums[i-1]]
     * 那么，我们就要做到：在计算新的 dp[j] 的时候，dp[j] 和 dp[j-nums[i-1]] 还是上一轮外层 for 循环的结果
     * 如果你从前往后遍历一维 dp 数组，dp[j] 显然是没问题的，但是 dp[j-nums[i-1]] 已经不是上一轮外层 for 循环的结果了，这里就会使用错误的状态，当然得不到正确的答案。
     */
    /* DP 空间优化 */
    public int findTargetSumWays(int[] nums, int target) {
        int sumAll = Arrays.stream(nums).sum();
        if (sumAll < Math.abs(target) || (sumAll + target) % 2 == 1) { // 全加也不能达到 或者不能整除 意味不存在合理的划分
            return 0;
        }
        int sum = (target + sumAll) / 2;
        int n = nums.length;
        int[] dp = new int[sum+1];
        dp[0] = 1;
        for(int i = 1;i <= n;i++) {
            for(int j = sum;j >= 0;j--) {
                if(j - nums[i-1] >= 0) dp[j] += dp[j - nums[i-1]];
            }
        }
        return dp[sum];
    }
    /* DP 原版 */
    // public int findTargetSumWays(int[] nums, int target) {
    //     int sumAll = Arrays.stream(nums).sum();
    //     if (sumAll < Math.abs(target) || (sumAll + target) % 2 == 1) { // 全加也不能达到 或者不能整除 意味不存在合理的划分
    //         return 0;
    //     }
    //     int sum = (target + sumAll) / 2;
    //     int n = nums.length;
    //     int[][] dp = new int[n+1][sum+1];
    //     dp[0][0] = 1;
    //     for(int i = 1;i <= n;i++) {
    //         for(int j = 0;j <= sum;j++) {
    //             dp[i][j] = dp[i-1][j];
    //             if(j - nums[i-1] >= 0) dp[i][j] += dp[i-1][j - nums[i-1]];
    //         }
    //     }
    //     return dp[n][sum];
    // }

    /* 回溯带备忘录 */
    // private HashMap<String, Integer> memo;
    // public int findTargetSumWays(int[] nums, int target) {
    //     memo = new HashMap<>();
    //     return backtrack(nums, 0, target);
    // }
    // private int backtrack(int[] nums, int index, int target) {
    //     if(index >= nums.length) {
    //         if(target == 0) return 1;
    //         return 0;
    //     }
    //     String state = index + "," + target;
    //     if(memo.containsKey(state)) return memo.get(state);
    //     int result = backtrack(nums, index + 1, target - nums[index]) + backtrack(nums, index + 1, target + nums[index]);
    //     memo.put(state, result);
    //     return result;
    // }
}
