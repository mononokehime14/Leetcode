#include <vector>
using namespace std;

class searchInsertPosition {
public:
    /**
     * 35. Search Insert Position
     * 这道题纯套用二分查找的模版 只要找出第一个grreater than target的数的位置即可
     * 如果没有直接return left 因为答案这个时候就是需要nums.size()
    */
    int searchInsert(vector<int>& nums, int target) {
        int left = 0, right = nums.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] < target) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
};