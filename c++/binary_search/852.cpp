/**
 * 852. Peak Index in a Mountain Array
 * 套用了自己的框架, 把问题转化成找到第一个前一个比自己大的, 也就是说山峰右边的那一个值
 * 按照我的写法, mid是正确解的情况下, 也会right = mid - 1再试一下, 所以要等到left再试回来
 * 故此left上的是正确解, 这里不用检查left是不是到底, 因为给的arr是确定mountain array
*/
class Solution {
public:
    int peakIndexInMountainArray(vector<int>& arr) {
        int left = 1, right = arr.size() - 2;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(arr[mid-1] <= arr[mid]) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left - 1;
    }
};