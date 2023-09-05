#include <vector>
#include <unordered_map>
using namespace std;

class containDuplicate2 {
public:
    /**
     * 219. Contains Duplicate II
     * 没有什么好说的, 只是注意C++中的unordered_map的使用方法. 这里测试发现find == end()这种判断
     * 空的方法应该要比count(key) > 0这种方法要快一些
     * 还有一个注意的地方是逻辑的合并, 如果两个values相同但是不满足k, 我们还是要更新pair, 寄希望于
     * 后面能够有满足的k的
    */
    bool containsNearbyDuplicate(vector<int>& nums, int k) {
        unordered_map<int, int> mem;
        for(int i = 0;i < nums.size(); i++) {
            if(mem.find(nums[i]) != mem.end() && abs(mem[nums[i]] - i) <= k) return true;
            mem[nums[i]] = i;
        }
        return false;
    }
};