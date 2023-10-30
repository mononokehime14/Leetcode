/**
 * 525. Contiguous Array
 * 严格来说这不算前缀数组, 但是思路是类似且巧妙的, 比较类似subarraySumEqualsK (以及TTOA里XOR为0的subarray)里
 * 使用hashmap记录前面和为x的subarray, 然后用差值就能找到正确的subarray, 这里我们也用hashmap
 * 我们维持一个balance, 1就+1, 0就-1, 并且用hashmap记录出现的位置
 * 在1 1 1这个例子中, balance是3, 如果后面有0 0 0 1 1 1, balance又回到了3
 * 这时候, 中间必然有一个balanced subarray! 这就是差值找到正确的subarray
 * 而且我们可以只记录该balance一次, 这样我们就维持着最左边的出现位置, 也就能找到最远的subarray.
 * 
 * 这道题我很快发现滑动窗口不行, 在思考DP的过程中也发现状态转移特别难推而且是n**2的, 这个时候是可以考虑前缀数组的这种思考方式
*/
class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        int n = nums.size();
        if(n < 2) return 0;
        unordered_map<int, int> mem;
        int balance = 0, cur, answer = 0;
        mem[0] = -1;
        for(int i = 0;i < n;++i) {
            cur = nums[i];
            balance += cur ? 1 : -1;
            if(mem.find(balance) != mem.end()) answer = max(answer, i - mem[balance]);
            else mem[balance] = i;
        }
        return answer;
    }
};