import java.util.Arrays;

public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        /* DP+空间优化, 注意只用到了前一个row所以不用保持和matrix同样的dp table, 有一定的提升 
         * 进一步空间优化,第一行dp就是matrix原值, 草,可以在原matrix上直接开搞!
        */
        int n = matrix.length;
        int m = matrix[0].length;
        /* DP loop */
        for(int i = 1;i < n;i++){
            for(int j = 0;j < m;j++){
                int up = matrix[i-1][j] + matrix[i][j];
                int lup = j > 0 ? matrix[i-1][j-1] + matrix[i][j] : 2147483647;
                int rup = j < m - 1 ? matrix[i-1][j+1] + matrix[i][j] : 2147483647;
                matrix[i][j] = Math.min(Math.min(lup, rup), up);
            }
        }
        return Arrays.stream(matrix[n - 1]).min().getAsInt();
    }
}
