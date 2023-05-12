package array;

public class GameOfLife {
    public void gameOfLife(int[][] board) {
        /** 289
         * 这道题的优化不在于计算 而在于空间 能否inplace change
         * 注意GOL的next state是simultaneous的 我们不能按照顺序更新
         * 所以可以用两个bit next + current 这样发生变化的 就是00 -> 10, reproduction;01 -> 11 stay alive
         * 其实也可以用parallelism对吗 分一个submatrix给thread 然后再收上来答案
         */
        int m = board.length, n = board[0].length;
        // int[][] newBoard = new int[m][n];
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                int count = 0;
                count += i > 0 ? board[i-1][j] & 1 : 0;
                count += i > 0 && j > 0 ? board[i-1][j-1] & 1 : 0;
                count += i > 0 && j < n - 1 ? board[i-1][j+1] & 1 : 0;
                count += j > 0 ? board[i][j-1] & 1 : 0;
                count += j < n - 1 ? board[i][j+1] & 1 : 0;
                count += i < m - 1 ? board[i+1][j] & 1 : 0;
                count += i < m - 1 && j > 0 ? board[i+1][j-1] & 1 : 0;
                count += i < m - 1 && j < n - 1 ? board[i+1][j+1] & 1 : 0;
                if(count == 3 && board[i][j] == 0) board[i][j] = 2;
                else if((count == 2 || count == 3) && board[i][j] == 1) board[i][j] = 3;
                // if(count < 2) newBoard[i][j] = 0;
                // else if(count > 3) newBoard[i][j] = 0;
                // else if(count == 3 && board[i][j] == 0) newBoard[i][j] = 1;
                // else newBoard[i][j] = board[i][j];
            }
        }
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                board[i][j] >>= 1;
            }
        }
    }
}
