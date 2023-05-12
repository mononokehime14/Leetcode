public class LongestValidParentheses {
    /* 32
     * 非常独特的一道dp 这个思路很难想到
     * 首先是如果按照palindrome subsequence的思路 假设ij我们知道最大值 那么判断两边的就可以了
     * 但是这里是substring  知道ij的最大valid parentheses substring的长度 是没办法叠加上去的 因为不确定是不是挨着边
     * 所以 这道题的dp思路是dp[i]是以i-1结尾的最长的valid parentheses substring 至于为什么是i-1
     * 这是为了方便之后的状态转移方程 d[i+1] = dp[leftIndex] + (i - leftIndex + 1)
     * 而至于怎么推算以i-1结尾的最长的valid parentheses substring 就是基础的valid parentheses判断加上stack
     * stack用来记录配对的左括号的index 这样我们可以一遍就完成
     * 
     * 实际上 stack是基于对parentheses正确性一遍检查的extend 如果是比较general的substring 有无dp思路呢
     * 也是有的 dp[i]的定义是以i为结尾的最长parentheses 事实上 我们发现如果当前是(可以直接不管
     * 如果是)可以分成两种情况 第一种前一个是( 那么不管怎么样 我385团一定要帮他一帮
     * 如果是）则要检查是否有配对的（ 通过dp[i-1]来推算最前面的一个候选 注意这里dp还得考虑 是不是能和前面的合并
     * 这是为什么还要检查i - dp[i-1] - 2
     */
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int max = 0;
        for(int i = 1;i < n;i++) {
            if(s.charAt(i) == ')') {
                if(s.charAt(i-1) == '(') {
                    dp[i] = 2 + (i - 2 >= 0 ? dp[i-2] : 0);
                }else if(i - dp[i-1] - 1 >= 0 && s.charAt(i - dp[i-1] - 1) == '('){
                    dp[i] = 2 + dp[i-1] + (i - dp[i-1] - 2 >= 0 ? dp[i - dp[i-1] - 2] : 0);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
    // public int longestValidParentheses(String s) {
    //     Stack<Integer> stack = new Stack<>();
    //     int n = s.length();
    //     int[] dp = new int[n+1];
    //     for(int i = 0;i < n;i++) {
    //         if(s.charAt(i) == '(') {
    //             stack.push(i);
    //             dp[i+1] = 0;
    //         }else{
    //             if(!stack.isEmpty()) {
    //                 int leftIndex = stack.pop();
    //                 dp[i+1] = dp[leftIndex] + (i - leftIndex + 1);
    //             }else{
    //                 dp[i+1] = 0;
    //             }
    //         }
    //     }
    //     return Arrays.stream(dp).max().getAsInt();
    // }
}
