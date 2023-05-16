#include <vector>
using namespace std;

class searchInRotateSortedArray2 {
public:
    /**
     * 81. Search in Rotated Sorted Array II
     * 这道题edge case的思考非常复杂有意思, 一开始的做法是沿用search in rotate sorted array 1的做法,
     * 先binary search找到rotate的pivot, 然后再做一次binary search. 但是方向第一次bs寻找pivot在有duplicate的
     * 情况有点难判断: 并不知道这个点一定是属于左半边或者右半边.
     * 实际上, 做法可以是直接在nums上bs, 但是要区分这两个情况, 第一个mid > left, 这意味着mid一定在左边的上升序列中
     * mid < left则意味着mid一定在右边的上升序列中, 我们可以分别在这两个case中再做bs. 注意到之后的bs完全就是正常的bs因为
     * 已经处于一个上升序列中了. 如果两者都不满足, 意味着duplicate, mid == left, 我们可以直接left++, 直到知道一个不一样的.
     * 这里有一个细节要注意, 为什么不能通过mid < right来得知mid处于右边的上升序列呢? 因为我们duplicate的处理方法是left++,
     * 试想如果有一个正确答案在mid前, 其他全部一样, 一开始mid就和left一样, 然后不断++, 这个时候如果第二个条件是和right比较,因为
     * right不变, 两个条件会一直不满足, 会一直++下去, 这样就错过了正确答案.
    */
    bool search(vector<int>& nums, int target) {
        int left = 0, right = nums.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(target == nums[mid]) return true;
            if(nums[mid] > nums[left]) {
                if(target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else if(nums[mid] < nums[left]) {
                if(target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }else{
                left++;
            }
        }
        return false;
    }
};