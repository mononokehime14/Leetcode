/**
 * 52. N-Queens II
 * 只是把输出改成数量罢了
*/
class Solution {
public:
    int totalNQueens(int n) {
        int answer = 0;
        vector<string> board(n, string(n, '.'));
        vector<bool> visited_col(n, false), visited_dia_l(2*n-1, false), visited_dia_r(2*n-1, false);
        backtrack(answer, board, visited_col, visited_dia_l, visited_dia_r, 0, n);
        return answer;
    }
    void backtrack(int &answer, vector<string> &board, vector<bool> &visited_col, vector<bool> &visited_dia_l, vector<bool> &visited_dia_r, int row, int n) {
        if(row == n) {
            answer += 1;
            return;
        }
        for(int i = 0;i < n;++i) {
            if(visited_col[i] || visited_dia_l[n-row-1 + i] || visited_dia_r[row + i]) continue;
            board[row][i] = 'Q';
            visited_col[i] = visited_dia_l[n-row-1 + i] = visited_dia_r[row + i] = true;
            backtrack(answer, board, visited_col, visited_dia_l, visited_dia_r, row+1, n);
            visited_col[i] = visited_dia_l[n-row-1 + i] = visited_dia_r[row + i] = false;
            board[row][i] = '.';
        }
    }
};