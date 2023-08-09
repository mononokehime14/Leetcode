#include <vector>
using namespace std;

/**
 * 48. Rotate Image
 * 通常来说我碰到旋转的题目是选水平再对角线, 走两次这种操作, 这样写起来是方便一点
 * 但是1 pass的方法就是四个点互换. n = 4, [0][1] - > [1][3], [1][3] -> [3][2]...这样转
 * 这个loop不是很好写, ij是头一个点的坐标, 比如上个例子就是01, 这里ij最好用offset来理解
 * 我们可以发现随着我们向右向下推进, 有一些点是已经换完了的, 实际上这是一种罗圈向内的循环方式
 * i实际上代表着对角线上的一个点, 从ii开始, 到n-i-1, 然后向内
*/
class Solution {
public:
    void rotate(vector<vector<int>>& matrix) {
        int temp = 0, n = matrix.size()-1;
        for (int i = 0; i <= n / 2; ++i) {
            for (int j = i; j < n - i; ++j) {
                temp = matrix[j][n-i];
                matrix[j][n-i] = matrix[i][j];
                matrix[i][j] = matrix[n-j][i];
                matrix[n-j][i] = matrix[n-i][n-j];
                matrix[n-i][n-j] = temp;
            } 
        }
    }
};