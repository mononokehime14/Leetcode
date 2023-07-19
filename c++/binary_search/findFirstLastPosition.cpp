#include <vector>
using namespace std;
/**
 * 34. Find First and Last Position of Element in Sorted Array
 * 又重新理解了模版(参考java BinarySearchTemplate). 这里要注意, 第一个position实际上是要找first greater or equal
 * 还有一点就是, 我们是区间查找的(没办法这里是要找第一个符合条件的target), 所以return回来后还要检查一下是greater还是equal的情况
 * 必须要equal target才符合我们的条件
*/
class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        int first = greater_equal(nums, target);
        int second = smaller_equal(nums, target);
        if(first==-1 || second==-1 || nums[first] != target || nums[second] != target) {
            return vector<int>{-1, -1};
        }
        return vector<int>{first, second};
    }
    int smaller_equal(vector<int>& nums, int target) {
        int left = 0, right = nums.size()-1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > target) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return right < 0 ? -1 : right;
    }
    int greater_equal(vector<int>& nums, int target) {
        int left = 0, right = nums.size()-1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < target) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left >= nums.size() ? -1 : left;
    }
};