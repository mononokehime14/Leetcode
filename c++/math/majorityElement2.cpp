/**
 * 229. Majority Element II
 * 我认为这是一个数学问题, 解法名为Boyer-Moore Majority Vote algorithm, 也叫摩尔投票法
 * 和1相比 这里主要是n/3 我们可以推的 最多只能有两个这样的数 就像n/2只能有一个获得大多数票一样
 * 我们完全沿用之前的思路 只是用两套count和candidate
 * 然后之后要再次检查一遍candidate究竟是不是正确答案 这在1中是不需要的因为保证了有一个答案
 * 具体的数学推断并不是特别明白 我觉得能大概理解1中的大多数投票就可以了
 * 这里用反证法会比较方便一点 有没有可能正确解没有成为candidate呢 这是不可能的 因为必然存在一个节点 正确解的数量
 * 压倒那个伪candidate 重新赢得count
 * 有没有可能正确解成为candidate之后被消掉呢? 不可能 again, 没有别的数字有这样的实力
*/
class Solution {
public:
    vector<int> majorityElement(vector<int>& nums) {
        vector<int> answer;
        int count1 = 0, count2 = 0, candidate1 = INT_MAX, candidate2 = INT_MAX;
        for(int i : nums) {
            if (count1 != 0 && candidate1 == i) {
                count1++;
            }else if (count2 != 0 && candidate2 == i) {
                count2++;
            }else if (count1 == 0) {
                candidate1 = i;
                count1 = 1;
            }else if (count2 == 0) {
                candidate2 = i;
                count2 = 1;
            }else {
                count1--; count2--;
            }
        }
        count1 = 0, count2 = 0;
        for (int i : nums) {
            if (candidate1 == i) count1++;
            if (candidate2 == i) count2++;
        }
        if (count1 > nums.size() / 3)  answer.push_back(candidate1);
        if (candidate1 != candidate2 && count2 > nums.size() / 3)  answer.push_back(candidate2);
        return answer;
    }
};