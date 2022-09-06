import java.util.Arrays;
import java.util.List;
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        /* DP, 可做空间优化因为只用了上一个row 
         * 注意这题可以反向DP因为结果都是一样的递推, 因为每一次都选最小的
         * 而反向DP不用检查edge case
         * 而且不用第二个array因为更改了当前index的dp值不会影响下面的更新, 太妙了
         * 其实反向递推可以是某种greedy algo
         * 实际的提升并不结构性但是是一个很精致的技巧
        */
        // int n = triangle.size();
        // int m = triangle.get(n - 1).size();
        // int[] dp1 = new int[m];
        // int[] dp2 = new int[m];
        // dp1[0] = triangle.get(0).get(0); // first element
        // for(int i = 1;i < n;i++){
        //     int currentl = triangle.get(i).size();
        //     for(int j = 0;j < currentl;j++){
        //         int current = triangle.get(i).get(j);
        //         int ul = j == 0 ? Integer.MAX_VALUE : dp1[j-1] + current;
        //         int u = j == currentl - 1 ? Integer.MAX_VALUE : dp1[j] + current;
        //         dp2[j] = Math.min(u, ul);
        //     }
        //     dp1 = dp2.clone();
        // }
        // return Arrays.stream(dp1).min().getAsInt();
        int n = triangle.size();
        // int m = triangle.get(n - 1).size();
        int[] dp = new int[n];//三角形的高和宽相同
        for(int i = 0;i < n;i++){
            dp[i] = triangle.get(n-1).get(i);
        }
        for(int i = n - 2;i >= 0;i--){
            for(int j = 0;j <= i;j++){ // j < traingle.get(i).size()是没必要的因为高和宽相同
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
