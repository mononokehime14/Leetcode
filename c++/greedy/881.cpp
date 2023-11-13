/**
 * 881. Boats to Save People
 * 轻松搞定了! 这道题和孙膑赛马那道题的思路是差不多的, 已知每个船最多载两人的情况下
 * 最重的只能考虑和最轻的搭配, 不行就肯定只能一个人, 局部的最优解可以导致全局最优, 实现上sort + 双指针就好了
*/
class Solution {
public:
    int numRescueBoats(vector<int>& people, int limit) {
        sort(people.begin(), people.end(), greater<int>()); //降序
        int n = people.size(), left = 0, right = n - 1, answer = 0;
        while(left < right) {
            if(people[left] + people[right] > limit) {
                left++;
            }else{
                left++;
                right--;
            }
            answer++;
        }
        return left == right ? answer + 1 : answer;
    }
};