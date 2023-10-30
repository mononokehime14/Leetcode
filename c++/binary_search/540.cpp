/**
 * 540. Single Element in a Sorted Array
 * singleElementInASortedArray
 * 自己做出来的, 有点意思的二分查找, 是通过判断当前的一对左边的那个数的index, 是不是偶数,
 * 是偶数说明single在右边, 反之在左边. 这里的判断比较细节.
 * 同时, 这里采取了left < right, 因为在寻找一个target的时候, 特别是我们确定有一个值
 * 用<=和用<区别是不大的.
*/
class Solution {
public:
    int singleNonDuplicate(vector<int>& nums) {
        int n = nums.size(), left = 0, right = n - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if((mid - 1 < 0 || nums[mid-1] != nums[mid]) &&
                (mid + 1 >= n || nums[mid+1] != nums[mid])) {
                    return nums[mid];
            }else if(mid - 1 >= 0 && nums[mid - 1] == nums[mid]) {
                if((mid - 1) % 2 == 1) {
                    right = mid - 2;
                }else{
                    left = mid + 1;
                }
            }else{
                if(mid % 2 == 1) {
                    right = mid - 1;
                }else{
                    left = mid + 2;
                }
            }
        }
        return nums[left];
    }
};