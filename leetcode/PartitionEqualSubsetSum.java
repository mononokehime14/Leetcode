import java.util.Arrays;;
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        /* 第一步是转换成01背包问题, amount= sum/2, nums就是背包
         * 目标是找到凑成amount的组合
         * 根据背包问题的初始思路, DP[i][j] = ANY{DP[i-1][j], DP[i][j-nums[i]]}
         * 代表对于当前用到nums[i], 凑amount = j, 我可以不用当前的num或者用,
         * 不用则继承前面的, 用则减去相应num, 2d解决正向loop不会有问题
         * !!!重点在于memory优化的时候, 注意第二个loop要改成反向loop, 因为正向loop造成重复叠加
         * 比如nums 1 2 5, amount 4, 正向update全部是true, 因为凑成功3用了1和2,为了凑成功4又用了1
         * 应该反向loop,在当前num loop的时候, j只能用这个num, 不用就是还不行(initial state),
         * 这限制了j用前面成功了的amount已经用掉了的nums
         */
        int m = Arrays.stream(nums).sum();
        if(m % 2 != 0) return false;
        m /= 2;
        boolean[] dp = new boolean[m+1];
        dp[0] = true;
        for(int i = 0;i < nums.length;i++){
            for(int j = m;j >= 0;j--){ //!!!
                if(j - nums[i] >= 0){
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[m];
    }
}
