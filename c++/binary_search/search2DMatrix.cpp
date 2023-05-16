#include <vector>
using namespace std;

class search2DMatrix {
public:
    /**
     * 74. Search a 2D matrix
     * 一开始的做法是先找row, 如果row第一个>= target, 那么这一行有希望, 再二分查找这一行
     * 但是实际上是没有必要的, 注意到整个矩阵是有序的, 所以可以直接二分查找
     * mid只需要拆分出来变成row col就可以了
     * 之前的方法实际上是logM * logN, 这个方法就是正儿八经的log(MN), 理论上应该快一些
    */
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int M = matrix.size(), N = matrix[0].size();
        int left = 0, right = M * N - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / N;
            int col = mid % N;
            if(matrix[row][col] > target) {
                right = mid - 1;
            }else if(matrix[row][col] < target) {
                left = mid + 1;
            }else{
                return true;
            }
        }
        return false;
    }

    // bool searchRow(vector<int>& row, int target) {
    //     int left = 0, right = row.size() - 1;
    //     while(left <= right) {
    //         int mid = left + (right - left) / 2;
    //         if(row[mid] == target) {
    //             return true;
    //         }else if(row[mid] < target) {
    //             left = mid + 1;
    //         }else {
    //             right = mid - 1;
    //         }
    //     }
    //     return false;
    // }

    // bool searchMatrix(vector<vector<int>>& matrix, int target) {
    //     int left = 0, right = matrix.size() - 1;
    //     while(left <= right) {
    //         int mid = left + (right - left) / 2;
    //         if(matrix[mid][0] > target) {
    //             right = mid - 1;
    //         }else{
    //             if(searchRow(matrix[mid], target)) {
    //                 return true;
    //             }else{
    //                 left = mid + 1;
    //             }
    //         }
    //     }
    //     return false;
    // }
};
