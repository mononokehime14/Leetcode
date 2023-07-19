#include <vector>
using namespace std;
/**
 * 1351. Count Negative Numbers in a Sorted Matrix
 * 套用了区间搜索, 由于题目matrix的特殊性只能每一行单独bs, 但是这样的话时间复杂度就是O(nlogn)
 * 但是实际上, 这道题目有比较特殊的做法能够到m+n, 就是利用sort both column and row的性质
 * 负数的数量从右到左, 只可能越来越多, 所以这个数字实际上可以累积 就做到了只走一遍n
*/
class Solution {
public:
    int countNegatives(vector<vector<int>>& grid) {
        int i,n=grid.size(),j=grid[0].size()-1,m=grid[0].size(),ans=0;
        for(i = 0; i < n; i++){
            while(j>= 0 && grid[i][j]<0)j--;
            if(j<0)ans += m;
            else if(j<m-1)ans += m-j-1;
        }
        return ans;
        // int size = grid[0].size(), count = 0;
        // for(int i = 0;i < grid.size();i++) {
        //     int left = 0, right = size - 1;
        //     while(left <= right) {
        //         int mid = left + (right - left) / 2;
        //         if(grid[i][mid] >= 0) {
        //             left = mid + 1;
        //         }else{
        //             right = mid - 1;
        //         }
        //     }
        //     count += left >= size ? 0 : size - left;
        // }
        // return count;
    }
};