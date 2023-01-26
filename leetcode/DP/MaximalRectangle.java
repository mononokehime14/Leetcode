public class MaximalRectangle {
    /* 85
     * 这道题一开始的思路错了 我想要通过i-1 j和i j-1的子解来推出i j的答案 这是不对的 最优子解不是独立的
     * 答案是通过left right height三个array right和left可以理解为当前row中 i j往左右扩散能到的最远的地方
     * 和之前那版答案base case的计算是类似的 然后再乘上height可以得到最大的rectangle
     * 这里的细节非常难考虑清楚 首先是如果一列都是1 怎么取最大值呢 要注意每一行最大的情况前面都算过了 后面再算的case只不过是想试试更厚的能不能更大罢了
     * 所以后面left right要受限于最小的
     */
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length, m = matrix[0].length, max = 0;
        int[] left = new int[m];
        int[] right = new int[m];
        Arrays.fill(right, m-1);
        int[] height = new int[m];
        for(int i = 0;i < n;i++) {
            int startLeft = 0, startRight = m-1;
            for(int j = 0;j < m;j++) {
                if(matrix[i][j] == '1') {
                    height[j]++;
                    left[j] = Math.max(left[j], startLeft);
                }else{
                    left[j] = 0;
                    startLeft = j+1;
                    height[j] = 0; // 注意这里清零 这样当前为0的肯定是0
                }
            }
            for(int j = m - 1;j >= 0;j--) {
                if(matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], startRight);
                }else{
                    right[j] = m-1; // 注意这里取最大值 后面再算的时候 由于right是Math min 这个值是不会影响正确性
                    startRight = j-1;;
                }
            }
            for(int j = 0;j < m;j++) {
                max = Math.max(max, (right[j] - left[j] + 1) * height[j]);
            }
        }
        return max;
    }
    /* 错误答案 左边或者上面并不是独立的最优子解 不同的选择会影响当前的dp计算 */
    // public int maximalRectangle(char[][] matrix) {
    //     int n = matrix.length, m = matrix[0].length;
    //     int[][][] dp = new int[n][m][2];
    //     int cur = 0, start = 0, max = 0;;
    //     for(int i = 0;i < n;i++) {
    //         if(matrix[i][0] == '0') {
    //             cur = 0;
    //             dp[i][0][0] = -1;
    //             dp[i][0][1] = -1;
    //         }else{
    //             if(cur == 0) start = i;
    //             cur++;
    //             dp[i][0][0] = start;
    //             dp[i][0][1] = 0;
    //             max = Math.max(max, cur);
    //         } 
    //     }
    //     cur = 0;
    //     start = 0;
    //     for(int i = 0;i < m;i++) {
    //         if(matrix[0][i] == '0') {
    //             cur = 0;
    //             dp[0][i][0] = -1;
    //             dp[0][i][1] = -1;
    //         }else{
    //             if(cur == 0) start = i;
    //             cur++;
    //             dp[0][i][0] = 0;
    //             dp[0][i][1] = start;
    //             max = Math.max(max, cur);
    //         } 
    //     }
        
    //     for(int i = 1;i < n;i++) {
    //         for(int j = 1;j < m;j++) {
    //             if(matrix[i][j] == '1') {
    //                 int leftX = i, leftY = j;
    //                 if(dp[i-1][j][0] != -1 && dp[i][j-1][0] != -1) {
    //                     int leftX2 = Math.max(dp[i-1][j][0], dp[i][j-1][0]);
    //                     int leftY2 = Math.max(dp[i-1][j][1], dp[i][j-1][1]);
    //                     if((i - leftX2 + 1) * (j - leftY2 + 1) > 1) {
    //                         leftX = leftX2;
    //                         leftY = leftY2;
    //                     } 
    //                 }
    //                 if(dp[i-1][j][0] != -1) {
    //                     if(i - dp[i-1][j][0] + 1 > (i - leftX + 1) * (j - leftY + 1)) {
    //                         leftX = dp[i-1][j][0];
    //                         leftY = j;
    //                     }
    //                 }
    //                 if(dp[i][j-1][0] != -1) {
    //                     if(j - dp[i][j-1][1] + 1 > (i - leftX + 1) * (j - leftY + 1)) {
    //                         leftX = i;
    //                         leftY = dp[i][j-1][1];
    //                     }
    //                 }
    //                 dp[i][j][0] = leftX;
    //                 dp[i][j][1] = leftY;
    //                 System.out.println(i + " " + j + " " + ((i - leftX + 1) * (j - leftY + 1)));
    //                 max = Math.max(max, (i - leftX + 1) * (j - leftY + 1));
    //             }else{
    //                 dp[i][j][0] = -1;
    //                 dp[i][j][1] = -1;
    //             }
    //         }
    //     }
    //     return max;
    // }
}
