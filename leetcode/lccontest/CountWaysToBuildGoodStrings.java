package lccontest;

public class CountWaysToBuildGoodStrings {
    /*
     * 这道题属于经典DP 一开始用dfs穷举 还自以为没有重复子问题 因为从头开始组装string就是不同的
     * 殊不知根本不需要组装string 应该是直接保持size维度的dp 这样就有重叠子问题了 
     * 一些细节在于开始可以从最小的开始 然后dp[i] += 是因为直接等于的话 base case就加不进去了 痛了好久
     */
    public int countGoodStrings(int low, int high, int zero, int one) {
        int M = (int) 1e9 + 7;
        int[] dp = new int[high+1];
        dp[zero] = 1;
        dp[one] += 1;
        for(int i = Math.min(zero, one);i <= high;i++) {
            int o = i - one >= 0 ? dp[i - one] : 0;
            int z = i - zero >= 0 ? dp[i - zero] : 0;
            dp[i] += (o + z) % M;
        }
        //for(int i: dp) System.out.println(i);
        int count = 0;
        for(int i = low;i <= high;i++) {
            count += dp[i];
            count %= M;
        }
        return count;
    }
    // private int count;
    // public int countGoodStrings(int low, int high, int zero, int one) {
    //     count = 0;
    //     String zeros = new String(new char[zero]).replace('\0', '0');
    //     String ones = new String(new char[one]).replace('\0', '1');
    //     dfs(low, high, zeros, ones, new StringBuilder());
    //     return count;
    // }
    // private void dfs(int low, int high, String zero, String one, StringBuilder sb) {
    //     int size = sb.length();
    //     if(size > high) return;
    //     if(size <= high && size >= low) {
    //         count++;
    //         count %= ((int) 1e9 + 7);
    //     }
    //     sb.append(zero);
    //     dfs(low, high, zero, one, sb);
    //     sb.delete(size, sb.length());
    //     sb.append(one);
    //     dfs(low, high, zero, one, sb);
    //     sb.delete(size, sb.length());
    // }
}
