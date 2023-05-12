package backtrackdfs;

public class PalindromePartitioning {
    /* 131
     * 事实上用一开始想到的土方法就能够过 从最左边开始找palindrome 然后把剩下的交给回溯
     * dp的优化在于可以不用每次扫描检测palindrome
     */
    public List<List<String>> partition(String s) {
        List<List<String>> output = new ArrayList<>();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(int i = 0;i < n;i++) dp[i][i] = true;
        dfs(s, output, new LinkedList<String>(), 0, dp);
        return output;
    }
    private void dfs(String s, List<List<String>> output, LinkedList<String> track, int start, boolean[][] dp) {
        if(start >= s.length()) {
            output.add(new ArrayList<String>(track));
            return;
        }
        for(int i = start;i < s.length();i++) {
            if(s.charAt(i) == s.charAt(start) && (i - start <= 2 || dp[start+1][i-1])) {
            //if(isPalindrome(s, start, i)) {
                dp[start][i] = true;
                track.add(s.substring(start, i+1));
                dfs(s, output, track, i+1, dp);
                track.removeLast();
            }
        }
    }
    // private boolean isPalindrome(String s, int left, int right) {
    //     while(left < right) {
    //         if(s.charAt(left++) != s.charAt(right--)) return false;
    //     }
    //     return true;
    // }
}
