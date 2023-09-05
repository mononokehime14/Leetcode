#include <vector>
using namepsace std;
/**
 * 240. Search a 2D Matrix II
 * 这道题的思路非常的巧妙 和前一题的区别是每一行的头一个不是承接上一行的末尾 而只是保证每行每列是递增的
 * 这样正常的二分查找就会漏过正确解了 思路是从右上开始 小于则往左 大于则往下
 * 这是不会漏过正确解的 target如果比当前小 那么是再也不可能回到这一列的 因为每列递增
 * 同理如果比当前大 是再也不可能回到这一行的 故此这个算法是正确的
*/
class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int m = matrix.size(), n = matrix[0].size();
        int i = 0, j = n - 1;
        while(i < m && j >= 0) {
            if(matrix[i][j] < target) {
                i++;
            }else if(matrix[i][j] > target){
                j--;
            }else{
                return true;
            }
        }
        return false;
    }
};