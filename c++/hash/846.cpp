#include <unordered_map>
#include <vector>
using namespace std;
/**
 * 846. Hand of Straights
 * 非常有意思的一道题, 我的想法是好像很类似最长增长子序列的patience sorting, 那个不断更新堆顶的方法
 * 如果我们控制每个堆size是groupsize, 是不是可以完成这道题目呢? 但是考虑到一个数字不一定作为一个group的start
 * 后面又可能出现比当前小的排在前面的, 所以我们必须先sort. 想到这里其实就把sort这一步想到了
 * 然后就是patience sorting实际上不合适, 因为那个方法原理是上要增加新开堆的可能, 和这道题的需求不是很契合,
 * 那个方法是要binary search的, 放到这题显然就不大对
 * 
 * 所以这道题实际上要用counting hashmap, sort之后我们能一定先遇到一个group的第一个数字, 然后直接搜后面的, 没有就是false
 * 注意由于一样的数字一次全部处理了, 后面遇到重复要跳过.
*/
class Solution {
public:
    bool isNStraightHand(vector<int>& hand, int groupSize) {
        unordered_map<int, int> count;
        for(int num : hand) count[num]++;
        sort(hand.begin(), hand.end());
        for(int i = 0;i < hand.size();++i) {
            int need = count[hand[i]];
            if(need == 0) continue;
            if(i > 0 && hand[i-1] == hand[i]) continue;
            for(int j = hand[i] + 1;j < hand[i] + groupSize;++j) {
                if(count.find(j) == count.end() || count[j] < need) return false;
                count[j] -= need; 
            }
        }
        return true;
    }
};