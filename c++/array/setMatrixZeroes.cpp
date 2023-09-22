/**
 * 73. Set Matrix Zeroes
 * 这道题能够想到要用两个vector一行一列 mark一下
 * 但是怎么不用额外的数组 还是缺乏想象力 这里可以直接用第一行第一列来Mark
 * 而这就带来了第二个赋值循环必须反向的要求 因为如果正向 前面的mark就会被覆盖
 * 这时候还有一个致命的问题 发生在0,0的位置上 如果第一列或者第一行有0 那么这个0,0会被更新成0
 * 问题就在于如果行有列没有 或者列有行没有 这不好记录 会互相干扰/覆盖
 * 解决方法是第一列单独令出来 0,0记录如常记录第一行 后面更新的时候也相应区分 这样就可以分清楚了
*/
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size(), col0 = 1;
        for(int i = 0;i < m;++i) {
            if(!matrix[i][0]) col0 = 0;
            for(int j = 1;j < n;++j) {
                if(!matrix[i][j]) matrix[i][0] = matrix[0][j] = 0; //连等就是帅
            }
        }
        for(int i = m - 1;i >= 0;--i) {
            for(int j = n-1;j > 0;--j) {
                if(!matrix[0][j] || !matrix[i][0]) matrix[i][j] = 0;
            }
            if(!col0) matrix[i][0] = 0;
        }
    }
};