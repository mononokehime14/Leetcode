package backtrackdfs;

public class SudokuSolver {
    /* 37
     * 这道题我的大概思路是对的 就是每一个格子9种选择 在细节上有一些问题导致过不了
     * 首先是停止条件 一开始时i==8 && j==8 这样最后一个格子实际上没有操作到
     * 其次是停止的时候的正确性判断 我用了一个isSolved函数 但是实际上 填不进去数字的话
     * 我们就会停止backtrack 故此 如果我们能够到达停止条件 那么一个解已经完成了
     * 然后是选择前后的状态更新和消除 之前用了if(!answer) board[i][j] = '.'并在循环中附加!answer的条件
     * 这样一旦answer为true就直接return 这种写法其实应该也是对的 不过最后用了答案的 直接return
     * 最后是isValid 我用了counting array 实际上 只需要比较当前加入的char是否有重复出现就可以了
     * 还有一个细节是检查小方块的时候 ij不能直接除3 要除3之后乘3才是正确的左上角开始位置
     */
    private boolean answer = false;
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }
    private void backtrack(char[][] board, int i, int j) {
        // if(answer) return;
        if(i == 9) {
            answer = true;
            // if(isSolved(board)) {
            //     answer = true;
            // }
            return;
        }
        int nextJ = j + 1;
        int nextI = i;
        if(nextJ > 8) {
            nextI++;
            nextJ = 0;
        }
        if(board[i][j] == '.') {
            for(char c = '1';c <= '9';c++) {
                if(!isValid(board, i, j, c)) continue;
                board[i][j] = c;
                // System.out.println(board[i][j] + " can continue");
                backtrack(board, nextI, nextJ);
                if(answer) return;
                board[i][j] = '.';
            }
        }else {
            backtrack(board, nextI, nextJ);
        }
    }
    // private boolean isSolved(char[][] board) {
    //     int[] counting = new int[9];
    //     // check rows
    //     for(int i = 0;i < 9;i++) {
    //         for(int j = 0;j < 9;j++) {
    //             counting[Character.getNumericValue(board[i][j]) - 1]++;  
    //         }
    //         for(int j = 0;j < 9;j++) {
    //             if(counting[j] == 0 || counting[j] > 1) return false;
    //             counting[j] = 0; 
    //         }
    //     }
    //     // check column
    //     for(int j = 0;j < 9;j++) {
    //         for(int i = 0;i < 9;i++) {
    //             counting[Character.getNumericValue(board[i][j]) - 1]++;  
    //         }
    //         for(int i = 0;i < 9;i++) {
    //             if(counting[i] == 0 || counting[i] > 1) return false;
    //             counting[i] = 0; 
    //         }
    //     }
    //     // check sub-box
    //     for(int i = 0;i < 9;i += 3) {
    //         for(int j = 0;j < 9;j += 3) {
    //             for(int k = 0;k < 3;k++) {
    //                 for(int l = 0;l < 3;l++) {
    //                     counting[Character.getNumericValue(board[i + k][j + l]) - 1]++; 
    //                 }
    //             }
    //             for(int k = 0;k < 9;k++) {
    //                 if(counting[k] == 0 || counting[k] > 1) return false;
    //                 counting[k] = 0; 
    //             }
    //         }
    //     }
    //     return true;
    // }
    private boolean isValid(char[][] board, int i, int j, char c) {
        // check row
        for(int k = 0;k < 9;k++) {
            if(board[i][k] == c) return false;
            if(board[k][j] == c) return false;
        }
        // sub-box
        i = (i / 3) * 3;
        j = (j / 3) * 3;
        for(int k = 0;k < 3;k++) {
            for(int l = 0;l < 3;l++) {
                if(board[i + k][j + l] == c) return false;
            }
        }
        return true;
    }
}
