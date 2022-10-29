public class HouseRobber2 {
    /* 213
     * 这道题目和198的区别在于环形 现在首个house和最后一个house是连在一起的
     * 直观上 这给base case的设计带来了麻烦 现在我们假设dp[0]的最大利润就是nums[0]了
     * 这里的方法 是分类讨论 比较两种不同的可能 抢了第一个 和 不抢第一个
     * 另外 修改了一下base case 其实可以加一个element在最前面 DP[n+1] 这样base case DP[0]可以设置成0 迭代从1开始
     * 这样做省了很多事情 可以不用特殊处理数组length不足2的情况
     */
    public int rob(int[] nums) {
        int n = nums.length; 
        if(n == 1) return nums[0];
        return Math.max(helper(nums, 0, n-2), helper(nums, 1, n-1));
    }
    private int helper(int[] nums, int start, int end) {
        int prev = nums[start], prevPrev = 0;
        for(int i = start + 1;i <= end;i++) {
            int current = Math.max(prev, prevPrev + nums[i]);
            prevPrev = prev;
            prev = current;
        }
        return prev;
    }
}
