public class HouseRobber {
    public int rob(int[] nums) {
        /* 198
         * 好像不是很特别 就是一个DP 可以做空间优化
         */
        int n = nums.length;
        if(n == 1) return nums[0];
        int prev = Math.max(nums[0], nums[1]), prevPrev = nums[0];
        for(int i = 2;i < n;i++) {
            int current = Math.max(prev, prevPrev + nums[i]);
            prevPrev = prev;
            prev = current;
        }
        return prev;
        // int n = nums.length;
        // int[] dp = new int[n];
        // if(n == 1) return nums[0];
        // dp[0] = nums[0];
        // dp[1] = Math.max(nums[0], nums[1]);
        // for(int i = 2;i < n;i++) {
        //     dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        // }
        // return dp[n-1];
    }
}