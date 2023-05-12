package oa;
import java.util.Arrays;

public class TikTokOA2022 {
    private String q1a = "(a | b) + (a & b)";
    private String q1b = "(a ^ b) + (a & b) * 2";
    private String q2 = "N - 1";
    // public static void main(String[] args) {
    //     int[][] nums = new int[][]{{1,2,3},{2,1,2},{3,1,1}};
    //     System.out.println("hello");
    //     System.out.println(dividingForest(nums, 3));
    // }
    public static int dividingForest(int[][] nums, int k) {
        int M = (int) 1e9 + 7, m = nums.length, n = nums[0].length;
        int[][][] dp = new int[m+1][n+1][k+1];
        int[][] preSum = new int[m+1][n+1];
        for(int i = m - 1;i >= 0;i--) {
            for(int j = n - 1;j >= 0;j--) {
                dp[i][j][1] = (nums[i][j] == 2 || dp[i+1][j][1] == 1 || dp[i][j+1][1] == 1) ? 1 : 0;
                preSum[i][j] = preSum[i+1][j] + preSum[i][j+1] - preSum[i+1][j+1];
                if(nums[i][j] == 2) preSum[i][j]++;
            } 
        }
        System.out.println("hello");
        for(int i = m - 1;i >= 0;i--) {
            for(int j = n - 1;j >= 0;j--) {
                int ans = 0;
                for(int ii = i + 1;ii < m && preSum[i][j] - preSum[ii][j] > 0;ii++) {
                    for(int c = 2;c <= k;c++) {
                        dp[i][j][k] = (dp[i][j][k] + dp[ii][j][c-1]) % M;
                    }    
                }
                for(int jj = j + 1;jj < n && preSum[i][j] - preSum[i][jj] > 0;jj++) {
                    for(int c = 2;c <= k;c++) {
                        dp[i][j][k] = (dp[i][j][k] + dp[i][jj][c-1]) % M;
                    }
                }
            }
        }
        return dp[0][0][k];
    }
    public int monsoonUmbrellas(int[] nums, int k) {
        // int m = nums.length;
        // int[][] dp = new int[m+1][k+1];
        // Arrays.fill(dp, Integer.MAX_VALUE);
        // for(int i = 1;i < m;i++) {
        //     for(int j = 1;j < k + 1;j++) {
        //         if(j - nums[i] < 0) {
        //             dp[i][j] = dp[i-1][j];
        //         }else{
        //             dp[i][j] = Math.min(dp[i-1][j], dp[i][j - nums[i]]);
        //         }
        //     }
        // }
        // return dp[m][k]; 
        int m = nums.length;
        int[] dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 1;i < m;i++) {
            for(int j = 1;j < k + 1;j++) {
                if(j - nums[i] >= 0 && dp[j - nums[i] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - nums[i]] + 1);
                }
            }
        }
        return dp[k] == Integer.MAX_VALUE ? -1 : dp[k];   
    }
    public boolean transformArray(String a, String b) {
        // transfer string to int array
        String[] as = a.split(" ");
        String[] bs = b.split(" ");
        int size = as.length;
        int[] ai = new int[size];
        for(int i = 0; i < size;i++) {
            ai[i] = Integer.parseInt(as[i]);
        }
        int[] bi = new int[size];
        for(int i = 0; i < size;i++) {
            bi[i] = Integer.parseInt(bs[i]);
        }
        Arrays.sort(ai);
        Arrays.sort(bi);
        for(int i = 0;i < size;i++) {
            if(ai[i] != bi[i] && ai[i] + 1 != bi[i]) return false;
        }
        return true;
    }
}
