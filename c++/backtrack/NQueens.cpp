/**
 * 51. N-Queens
 * 最朴素的回溯 按照row递归 每个row尝试每个col
 * 这里visited主要是用在快速检查位置是不是合法 纵还是两种斜的
 * 当前cell在斜着的方向上的index并不直观, 但是可以通过画图发现
 * 这里和C++的书上有区别 我在朝向右的斜方向的index算法是row + col
 * 经过推算这个是正确的 而且时间是100%
*/
class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
       vector<vector<string>> answer; 
       vector<string> board(n, string(n, '.'));
       vector<bool> visited_col(n, false), visited_dia_l(2*n-1, false), visited_dia_r(2*n-1, false);
       backtrack(answer, board, visited_col, visited_dia_l, visited_dia_r, 0, n);
       return answer;
    }
    void backtrack(vector<vector<string>> &answer, vector<string> &board, vector<bool> &visited_col, vector<bool> &visited_dia_l, vector<bool> &visited_dia_r, int row, int n) {
        if(row == n) {
            answer.push_back(board);
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