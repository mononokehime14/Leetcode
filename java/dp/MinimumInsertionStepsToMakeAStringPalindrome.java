public class MinimumInsertionStepsToMakeAStringPalindrome {
    /* 1312
     * 这道题要用最长公共子序列 太巧妙了
     * 自己的思路太暴力了 我准备用回溯 每一层在string的每一个位置插入26个字母 停止条件就是最大插入应该是n次
     * 因为最坏情况我们能够直接reverse string放在右边 但是这个方法的时间复杂度应该是n的n次方 用备忘录好像也不能很快 没能算出这里的时间复杂的
     * 反正第一个testcase都跑不过
     * 正确的思路是把string reverse 然后找到这个reverse string和原string的最长公共子序列 答案就是n-最长公共子序列
     * 我认为这个方法是基于 一个string中如果正着反着序列里共有 就是正着读和反着读一样的意思 ab和ba没有公共子序列 就是因为正读反渎是不同的
     * 也就是说 最长公共子序列是最长的一个 我们不用处理就能palindrome的 序列 自然minimum insertion就是n减去其长度
     * 正如之前分析的那样 maximum是n 顶多我们把整个reverse string拼到后面 比如abcde edcba
     */
    // private int min;
    // private HashMap<String, Boolean> memo;
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for(int i = 1;i <= n;i++) {
            for(int j = 1;j <= n;j++) {
                int equal = 0;
                if(s.charAt(i - 1) == s.charAt(n - j)) equal = dp[i-1][j-1] + 1;
                dp[i][j] = Math.max(
                    equal,
                    Math.max(dp[i-1][j], dp[i][j-1])
                );
            }
        }
        return n - dp[n][n];
        // memo = new HashMap<>();
        // min = s.length();
        // backtrack(s, min, 0);
        // return min;
    }
    // private boolean backtrack(String s, int max, int count) {
    //     if(count == max) return false;
    //     if(memo.containsKey(s)) {
    //         if(memo.get(s)) min = Math.min(count, min);
    //         return memo.get(s);
    //     }
    //     if(isPalindrome(s)) {
    //         memo.put(s, true);
    //         return true;
    //     }
    //     boolean result = false;
    //     int n = s.length();
    //     for(int j = 0;j < 26;j++) {
    //         String newS = (char)('a' + j) + s;
    //         if(backtrack(newS, max, count + 1)) result = true;
    //     }
    //     for(int i = 1;i < n;i++) {
    //         for(int j = 0;j < 26;j++) {
    //             String newS = s.substring(0, i) + (char)('a' + j) + s.substring(i, n);
    //             if(backtrack(newS, max, count + 1)) result = true;
    //         }
    //     }
    //     for(int j = 0;j < 26;j++) {
    //         String newS = s + (char)('a' + j);
    //         if(backtrack(newS, max, count + 1)) result = true;
    //     }
    //     memo.put(s, result);
    //     return result;
    // }
    // private boolean isPalindrome(String s) {
    //     int left = 0, right = s.length() - 1;
    //     while(left < right) {
    //         if(s.charAt(left++) != s.charAt(right--)) return false;
    //     }
    //     return true;
    // }
}
