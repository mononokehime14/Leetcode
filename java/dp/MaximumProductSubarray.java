
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        /*
         * 牛！自己做了一下就做出来了 首先是发现 最大乘积是能够有最优子解的 只不过有一个关键的细节
         * 负数不能直接抛弃掉 因为之后还有可能乘上负数翻正 说不定答案更大 那么就自然想到了要两个状态 正数和负数
         * 基本的状态转移没问题 这道题卡了几个test case的base case的设计 如果是正数 负数的状态应该设置为0 这样后面没有遇到负数的话
         * 这个状态就会一直是0 如果有负数 那么能够通过Math.min把负数状态更新成当前的负数 所以0是合适的base case
         * 注意好像不能空间优化 因为最优解是留在dp array中的 哦那通过保持一个max也是可以的
         */
        int n = nums.length;
        int negative = nums[0] < 0 ? nums[0] : 0;
        int positive = nums[0] >= 0 ? nums[0] : 0;
        int max = nums[0];
        for(int i = 1;i < n;i++) {
            if(nums[i] < 0) {
                int negativeCopy = negative;
                negative = Math.min(positive * nums[i], nums[i]);
                positive = negativeCopy * nums[i];
            }else{
                positive = Math.max(positive * nums[i], nums[i]);
                negative = negative * nums[i];
            }
            max = Math.max(positive, max);
        }
        return max;
        // int n = nums.length;
        // int[][] dp = new int[n][2];
        // dp[0][0] = nums[0] < 0 ? nums[0] : 0;
        // dp[0][1] = nums[0] >= 0 ? nums[0] : 0;
        // for(int i = 1;i < n;i++) {
        //     if(nums[i] < 0) {
        //         dp[i][0] = Math.min(dp[i-1][1] * nums[i], nums[i]);
        //         dp[i][1] = dp[i-1][0] * nums[i];
        //     }else{
        //         dp[i][1] = Math.max(dp[i-1][1] * nums[i], nums[i]);
        //         dp[i][0] = dp[i-1][0] * nums[i];
        //     }
        // }
        // int max = nums[0];
        // for(int i = 1;i < n;i++) max = Math.max(dp[i][1], max);
        // return max;
    }
}