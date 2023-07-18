#include <vector>
#include <unordered_map>
#include <string>

using namespace std;
/**
 * 763 Partition Labels
 * 这道题也是贪心, 思路也是先转个弯: 分出最多的部分, 那就相当于每一个部分要尽量的小
 * 如果说, 现在有一个a, 最后一个a出现在end处, 那么局部最优就不可能小于end了. 这个时候我们再慢慢loop过去, 看下
 * end需不需要扩张, 不扩张就不动, 直到iterator过了end, 我们就可以算一个解. 这个解法需要提前一个循环记录每个字符最大出现的位置.
 * "在处理数组前，统计一遍信息(如频率、个数、第一次出现位置、最后一次出现位置等)可以使题目难度大幅降低"
*/
class Solution {
public:
    vector<int> partitionLabels(string s) {
        unordered_map<char, int> group;
        vector<int> output;
        int size = s.length();
        for(int i = 0;i < size;i++) {
            group[s[i]] = i;
        }
        int start=0, end=0;
        for(int i = 0;i < size;i++) {
            if(i > end) {
                output.push_back(end - start + 1);
                start = i;
                end = start;
            }
            if(group[s[i]] > i && group[s[i]] > end) {
                end = group[s[i]];
            }
        }
        output.push_back(end - start + 1);
        return output;
    }
};