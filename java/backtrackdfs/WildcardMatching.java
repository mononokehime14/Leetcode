package backtrackdfs;

public class WildcardMatching {
    /* 44
     * 这道题理论上和10 Regular Expression Matching是非常相像的 但是我一下子 突然没办法理解dp的实现里通配符的处理方法
     * 感觉dp好像很难理解 
     * 一个更偷懒的方法就是回溯带备忘录 非常好理解
     */
    private int[][] dp;
    public boolean isMatch(String s, String p) {
        if(p.length() == 0) return s.length() == 0;
        char[] s1 = s.toCharArray();
        char[] p1 = p.toCharArray();
        int m = s1.length, n = p1.length;
        dp = new int[m][n];
        for(int[] i: dp) Arrays.fill(i, -1);
        return backtrack(s1, p1, 0, 0, m, n);
    }
    private boolean backtrack(char[] s, char[] p, int i, int j, int m, int n) {
        if(i == m && j == n) return true;
        if(i == m) {
            for(int k = j;k < n;k++) {
                if(p[k] != '*') return false; // i先结束则p那边剩下的必须全部是*才行
            }
            return true;
        }
        if(j == n) return false;
        if(dp[i][j] != -1) return dp[i][j] == 1;
        boolean temp = false;
        if(s[i] == p[j] || p[j] == '?') {
            temp = backtrack(s, p, i+1, j+1, m, n);
        }else if(p[j] == '*') {
            temp = backtrack(s, p, i+1, j, m, n) || backtrack(s, p, i, j+1, m, n);
        }
        dp[i][j] = temp ? 1 : 0;
        return temp;
    }
}
