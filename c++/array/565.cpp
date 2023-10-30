/**
 * 565. Array Nesting
 * arrayNesting
 * 我本来的思路是for循环, 只要有头节点, 我们的路径就注定了
 * 然后用快慢指针寻找循环 但是实际上, 快慢指针检测环方法并不能知道环的长短
 * 因为在什么时候相遇是不确定的, 只是确定会相遇
 * 故此这里只能从头节点开始循环一遍直到环出现 这里我们可以引入visited的概念
 * 到过一个节点之后就清成-1, 
*/
class Solution {
public:
    int arrayNesting(vector<int>& nums) {
        int answer = 0, n = nums.size();
        for(int i = 0;i < n;++i) {
            int local_answer = 0, j = i, next;
            while(nums[j] != -1) {
                local_answer++;
                next = nums[j];
                nums[j] = -1;
                j = next;
            }
            answer = max(answer, local_answer);
        }
        return answer;
    }
};