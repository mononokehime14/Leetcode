#include <vector>
using namespace std;
/**
 * 665 Non-decreasing Array
 * 这道题做的时候其实没想贪心, 数组我们可以波形想象吗, 如果出现下坡, 那么我们有一次机会修正, 再出现下坡就寄
 * 修正的时候, i改成<=i+1 还是i+1改成>=i呢. 贪心的来讲, 应该i改成<=i+1, 这样i+1不用增大, 就提高了后面不出现下坡的几率
 * 但是这里有例外, 就是如果i+1 < i-1, 那么没有办法只能i+1改成i 这是一个贪心策略的例外.
*/
class Solution {
public:
    bool checkPossibility(vector<int>& nums) {
        bool chance = true;
        for(int i = 0;i < nums.size() - 1;i++) {
            if(nums[i] > nums[i+1]) {
                if(chance) {
                    chance = false;
                    if(i-1>=0 && nums[i+1] < nums[i-1]) {
                        nums[i+1] = nums[i];
                    }
                }else{
                    return false;
                }
            }
        }
        return true;
    }
};