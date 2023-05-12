package array;

public class SpiralMatrix {
    /* 这里用了递归 一层一层剥下去 空间比较好 但是时间不大行 能过
     * 不要用递归 改成while 应该能够提升速度 这个可能和testcase有关系
     */
    List<Integer> output;
    public List<Integer> spiralOrder(int[][] matrix) {
        output = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        recursion(matrix, 0, n - 1, 0, m - 1, 0);
        return output;
    }
    private void recursion(int[][] matrix, int left, int right, int up, int down, int direction) {
        if(left > right || up > down) return;
        if(direction == 0) { // up
            for(int i = left;i <= right;i++) output.add(matrix[up][i]);
            direction = 1;
            up++;
        }else if(direction == 1) { // right
            for(int i = up;i <= down;i++) output.add(matrix[i][right]);
            direction = 2;
            right--;
        }else if(direction == 2) { // bottom
            for(int i = right;i >= left;i--) output.add(matrix[down][i]);
            direction = 3;
            down--;
        }else { // left
            for(int i = down;i >= up;i--) output.add(matrix[i][left]);
            direction = 0;
            left++;
        }
        recursion(matrix, left, right, up, down, direction);
    }
}
