#include <vector>
using namespace std;
/**
 * 287. Find the Duplicate Number
 * 解释可以参照java版本 经过推算 快慢指针相遇之后slow要多退一步 原理未解 只能是通过例子推定的
 * 新注:这可能是因为快慢指针do while 因为slow fast一开始不能都是0这样进不去loop 所以都走了一步
 * 如此实际上的起点是0
*/
class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        int slow = nums[0];
        int fast = nums[slow];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
};