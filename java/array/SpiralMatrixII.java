package array;

public class SpiralMatrixII {
    /* 是SpiralMatrix的思路没有变化 */
    public int[][] generateMatrix(int n) {
        int left = 0, right = n - 1, up = 0, down = n-1, direction = 0, answer = 1;
        int[][] output = new int[n][n];
        while(left <= right && up <= down) {
            if(direction == 0) { // up
                for(int i = left;i <= right;i++) {
                    output[up][i] = answer++;;
                }
                direction = 1;
                up++;
            }else if(direction == 1) { // right
                for(int i = up;i <= down;i++) output[i][right] = answer++;
                direction = 2;
                right--;
            }else if(direction == 2) { // bottom
                for(int i = right;i >= left;i--) output[down][i] = answer++;
                direction = 3;
                down--;
            }else { // left
                for(int i = down;i >= up;i--) output[i][left] = answer++;
                direction = 0;
                left++;
            }
        }
        return output;
    }
}
