/**
 * 456. 132 Pattern
 * 这道题已经想到了是单调栈, 我的想法是从左往右常规, 栈顶比自己小就踢下来(pattern3), 更新min val也就是pattern1
 * 然后呢, 如果当前的值在两者的区间, 就满足pattern 2, 就是正确解
 * 但是这里有一个问题, 3 5 3 4, 栈顶就是3, 4本来可以正确解但是无法判定正确
 * 这个问题的根源在于这个栈只记录了min和一个降序序列, 我们要求证的是pattern 2, 在1 3中间, 这不是很好搞
 * 正确的解法是反过来遍历, 去记录3 2的pattern, 这个方法成功的关键在于我们现在求证的不是2, 而是1,
 * 这本来就是132 pattern最小的那个, 所以我们当前值一旦小于更新过的min, 立刻就能正确判断
*/
class Solution {
public:
    bool find132pattern(vector<int>& nums) {
        int min_val = INT_MIN;
        stack<int> s;
        for(int i = nums.size() - 1; i >= 0;--i) {
            if(nums[i] < min_val) return true;
            while(!s.empty() && s.top() < nums[i]) {
                min_val = s.top();
                s.pop();
            }
            s.push(nums[i]);
        }
        return false;
    }
};