/**
 * 561. Array Partition
 * arrayPartition
 * 简单题, 思路就是要得到各对的min之和最大化, 排序后每两个的左边挑出来一定是最大的
 * 以倒数第二个为例, 一定是所有min中最大的了.
 * 放在array里是因为就用了普通的sort, 也不用放到sorting folder里了
*/
class Solution {
public:
    int arrayPairSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int answer = 0;
        for(int i = 0;i < nums.size();i+=2) {
            answer += nums[i];
        }
        return answer;
    }
};