package greedy;

public class JumpGameII {
    /* 45
     * DP的做法是在所有子问题中寻找最小的
     * 但是实际上这是可以贪心的 我们可以找能够跳到的range里 下一步能跳到最远的
     * 因为这不是跳到什么地方 而是能跳的range 所以可以贪心 因为越大肯定越好
     * 如此则可在当前选择的i的range中 挑选出最大的下一跳的end
     */
    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0, furthest = 0, count = 0;
        for(int i = 0;i < n - 1;i++) {
            furthest = Math.max(nums[i] + i, furthest);
            if(end == i) {
                end = furthest;
                count++;
            }
        }
        return count;
    }
    // public int jump(int[] nums) {
    //     int n = nums.length;
    //     int[] dp = new int[n];
    //     Arrays.fill(dp, n + 1);
    //     dp[n-1] = 0;
    //     for(int i = n - 2;i >= 0;i--) {
    //         int minStep = n + 1;
    //         for(int j = i + 1;j <= Math.min(i + nums[i], n - 1);j++) {
    //             minStep = Math.min(minStep, dp[j] + 1);
    //         }
    //         dp[i] = minStep;
    //     }
    //     return dp[0];
    // }
}
