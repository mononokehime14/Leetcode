package array.prefixsumarray;

public class RangeSumQuery2DImmutable {
    /* LC304
     * PreSum 没啥多说的挺简单 由于每次做比较的缘故时间成本比较高 可以考虑分块loop应该会快一些
     */
    private int[][] preSum;
    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        preSum = new int[m][n];
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                int temp = matrix[i][j];
                if(i-1>=0) temp += preSum[i-1][j];
                if(j-1>=0) temp += preSum[i][j-1];
                if(i-1>=0 && j-1>=0) temp -= preSum[i-1][j-1];
                preSum[i][j] = temp;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int temp = preSum[row2][col2];
        if(row1-1>=0) temp -= preSum[row1-1][col2];
        if(col1-1>=0) temp -= preSum[row2][col1-1];
        if(row1-1>=0 && col1-1>=0) temp += preSum[row1-1][col1-1];
        return temp;
    }
}
