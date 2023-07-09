#include <vector>
using namespace std;
class matrixBlockSum {
public:
    /**
     * 1314. Matrix Block Sum
     * 题目的思路并不难想, 有一些边界细节则要考虑清楚, 比如左上要减去的block
     * 如果超出届是应该不用减就可以了, 但是右下角要加上的, 以其另外两个要减去的block的下边界
     * 则是能有多少有多少
    */
    vector<vector<int>> solution(vector<vector<int>>& mat, int k) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> prefix_sum(m, vector<int>(n));
        vector<vector<int>> answer(m, vector<int>(n));
        for(int i = 0;i < m;i++) {
            int row_sum = 0;
            for(int j = 0;j < n;j++) {
                row_sum += mat[i][j];
                prefix_sum[i][j] = i > 0 ? prefix_sum[i-1][j] + row_sum : row_sum;
            }
        }
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                int minus1 = i - k - 1 >= 0 ? prefix_sum[i-k-1][min(j+k, n-1)] : 0;
                int minus2 = j - k - 1 >= 0 ? prefix_sum[min(i+k, m-1)][j-k-1] : 0;
                int add1 = i - k - 1 >= 0 && j - k - 1 >= 0 ? prefix_sum[i-k-1][j-k-1] : 0;
                int add2 = prefix_sum[min(i+k, m-1)][min(j+k, n-1)];
                answer[i][j] = add2 - minus1 - minus2 + add1;
            }
        }
        return answer;
    }
};