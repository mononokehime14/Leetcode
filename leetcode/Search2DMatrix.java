public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int row = 0;
        int col = matrix[0].length - 1;
        while(row < m && col >= 0){
            if(target > matrix[row][col]){
                row++;
            }else if(target < matrix[row][col]){
                col--;
            }else{
                return true;
            }
        }
        return false;
    }
}
