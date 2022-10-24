public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        /* 不多说 在下DP小王子
         * 这道题本来在考虑滑动窗口 因为感觉DP不大行 我加还是不加当前的数字 后面的最优解好像都不一定是基于当前的最优解
         * 后来一想 好像是基于当前最优解的 对于a来说 a-1的最大subarray加上自己 或者不带a-1的最大subarray 这必然是以a为终点的subarray的最优解
         * 这样 最大值就是留在array中的 而不是累加到最后一个位置
         * dp的思路不是以a为终点前面的array里最大的subarray的sum 而是以a为终点的subarray的最大的sum
         * 这个答案做出来之后 可以用variable优化空间
         */
        // int n = nums.length;
        // int[] dp = new int[n];
        // dp[0] = nums[0];
        // for(int i = 1;i < n;i++) {
        //     dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
        // }
        // return Arrays.stream(dp).max().getAsInt();
        public int maxSubArray(int[] nums) {
            int n = nums.length;
            int max = nums[0];
            int prev = nums[0];
            for(int i = 1;i < n;i++) {
                prev = Math.max(prev + nums[i], nums[i]);
                max = Math.max(prev, max);
            }
            return max;
        }
    }
}
