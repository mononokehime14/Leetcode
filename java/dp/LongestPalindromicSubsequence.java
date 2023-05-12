public class LongestPalindromicSubsequence {
    /* 516
     * 最长回文子序列问题 DP三个经典子序列问题应该是最长增长子序列 最长公共子序列 最长回文子序列问题
     * 子序列问题是不同于子数组问题的 子数组要求连续 滑动窗口可能可以解决问题 但是子序列是可以断开挑选的 如果暴力解
     * 选择造成的递归树应该至少有2的n次方 所以几乎肯定要用DP了
     * 其中 最长增长子序列的状态选择方程是一维的 dp[i]的定义是到i为止最长的增长子序列 转移是取决于前面比i小的数字的dp值
     * 当然 有快速的nlogn方法
     * 最长公共子序列是两个string 所以dp二维 dp[i][j]的定义是第一个string到i和第二个string到j的最长公共子序列
     * 但是最长回文子序列要怎么操作呢 dp[i][j]可以定义成i到j之间的最长回文子序列
     * 这样状态转移就是如果i的char和j的char相同 就必然可以加入到回文序列里 因为子序列不要求连续 
     * 而如果不相同 i和j就不可能在一个回文序列里了 就可以dp[i+1][j] dp[i][j-1]挑一个最大的 这两个都是前面已经算出来极值的 
     * 然后一个细节就是迭代方向 技巧是画出来二维矩阵 首先base case是对角线 数值是1 接下来要明白我们的answer在dp[0][n-1]
     * 也就是右上角 再看状态转移 要么需要当前ij右下角的i+1 j-1 要么需要正下方的i+1 j 或者左边的j-1 自然 我们就从底向上 从左到右迭代
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0;i < n;i++) {
            dp[i][i] = 1;
        }
        for(int i = n - 2;i >= 0;i--) {
            for(int j = i + 1;j < n;j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
