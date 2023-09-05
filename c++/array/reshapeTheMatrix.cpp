#include <vector>
using namespace std;
/**
 * 566. Reshape the Matrix
 * 简单的vector遍历问题
*/
class Solution {
public:
    vector<vector<int>> matrixReshape(vector<vector<int>>& mat, int r, int c) {
        int m = mat.size(), n = mat[0].size();
        if(m * n != r * c) return mat;
        vector<vector<int>> answer(r, vector<int>(c));
        int o_i = 0, o_j = 0;
        for(int i = 0;i < r;i++) {
            for(int j = 0;j < c;j++) {
                if(o_j >= n) {
                    o_j = 0;
                    o_i++;
                }
                answer[i][j] = mat[o_i][o_j++];
            }
        }
        return answer;
    }
};