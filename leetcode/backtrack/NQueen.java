package backtrack;
import java.util.List;
import java.util.ArrayList;
public class NQueen {
    /*
     * 这是一道经典的回溯. 需要特别注意的是, 每次的选择不用是一整个matrix, 而是一个row
     * 这是因为一行一行放皇后, 选择的正确性不会收到影响. 路径就是一个累积的board, 每次选择是
     * loop row, 判断每一个位置是否可以放皇后? 可以放的话就可以下一层backtrack.
     * 何时停止回溯呢? 两种情况, 第一种是整个row都放不进, 这就是自己结束function call return
     * 第二种是发现我们记录的row层数已经大于n, 那说明前面都成功了, 这便是一个成功的案例
     * 细节: java list copy是深度的, add board to output, 后面值改的时候它也会变, 要用copy方法
     */
    List<List<String>> output;
    public List<List<String>> solveNQueens(int n) {
        String empty_row = "";
        for(int i = 0;i < n;i++) empty_row += ".";
        output = new ArrayList<List<String>>();
        List<String> board = new ArrayList<String>();
        for(int i = 0;i < n;i++) board.add(empty_row);
        backtrack(board, 0, n);
        return output;
    }
    private void backtrack(List<String> board, int row, int n) {
        if(row == n){
            output.add(new ArrayList<String>(board));
            // System.out.println(output);
            return;
        }
        // System.out.println(row);
        // System.out.println(board);
        // System.out.println("");
        for(int j = 0;j < n;j++){
            boolean temp = isValid(board, row, j, n);
            // System.out.println(String.valueOf(row) + " "+ String.valueOf(j)+ " "+ String.valueOf(n));
            // System.out.println(temp);
            if(!temp) continue;
            board.set(row, board.get(row).substring(0, j) +'Q' + board.get(row).substring(j+1));
            // System.out.println(board);
            backtrack(board, row+1, n); //利用一行只能放一个Q的特点
            board.set(row, board.get(row).substring(0, j) +'.' + board.get(row).substring(j+1));
        }
    }
    private boolean isValid(List<String> board, int row, int col, int n) {
        if(board.get(row).charAt(col) == 'Q') return false;
        for(int i = 0;i < row;i++){
            if(board.get(i).charAt(col) == 'Q') return false;
        }
        for(int i = 0;i < col;i++){
            if(board.get(row).charAt(i) == 'Q') return false;
        }
        for(int i = row-1,j =col-1;i >= 0 && j >= 0;i--, j--){
            if(board.get(i).charAt(j) == 'Q') return false;
        }
        for(int i = row-1,j =col+1;i >= 0 && j < n;i--, j++){
            if(board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}
