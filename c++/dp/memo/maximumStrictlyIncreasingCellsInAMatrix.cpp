/**
 * 2713. Maximum Strictly Increasing Cells in a Matrix
 * 这道题首先是memorization top down, 这很好想, 而且除了cell本身不需要别的state memory
 * 这里的问题在于, 下一跳能够访问一整行一整列, 如果不optimize, 这就是O(MN * (M + N))
 * 这里的optimize是一个trick的思路, 配上类似最长增长子序列的方法
 * 这个trick就是我们只能访问strictly greater, 那么我下一跳只要去first greater, 就能maximize后面有更多path的可能性
 * 所以与其每个element都去一下, 我们只去first greater. 这里对每一行每一列形成sorted map, 每个值对应其出现的位置
 * 这就像是patience sort的时候的堆一样 我们在这些堆中找first greater
 * 由于使用了map, 这里使用了c++的lower bound做二分查找
 * 优化完的时间复杂度是O(MN * log(M + N))
*/
class Solution {
public:
    int maxIncreasingCells(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> dp(m, vector<int>(n, -1));
        map<int,vector<int>> row[m];
        map<int,vector<int>> col[n];
        for(int i = 0;i < m;++i) {
            for(int j = 0;j < n;++j) {
                int current = mat[i][j];
                row[i][current].push_back(j);
                col[j][current].push_back(i);
            }
        }
        int answer = 0;
        for(int i = 0;i < m;++i) {
            for(int j = 0;j < n;++j) {
                answer = max(answer, dfs(mat, dp, row, col, i, j));
            }
        }
        return answer;
    }
    
    int dfs(vector<vector<int>>& mat, vector<vector<int>>& dp, map<int,vector<int>> row[], map<int,vector<int>> col[], int x, int y) {
        // 不需要检查边界, 因为必然跳到处理好的index里
        if(dp[x][y] != -1) return dp[x][y];
        int local_answer = 0;
        // lower_bound: Returns an iterator pointing to the first element in the range [first,last) which does not compare less than val. 可以equal, 故此这里strictly greater要手动+1
        auto start_row = row[x].lower_bound(mat[x][y] + 1);
        if(start_row != row[x].end()){
            vector<int> candidates = (*start_row).second; //.second get the value of the map item
            for(auto next : candidates)
                local_answer = max(local_answer, dfs(mat, dp, row, col, x, next));
        }
        auto start_col = col[y].lower_bound(mat[x][y] + 1);
        if(start_col != col[y].end()){
            vector<int> candidates = (*start_col).second; 
            for(auto next : candidates)
                local_answer = max(local_answer, dfs(mat, dp, row, col, next, y));
        }
        dp[x][y] = ++local_answer;
        return local_answer;
    }
};