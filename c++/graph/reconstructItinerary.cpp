#include <vector>
using namespace std;
/**
 * 332. Reconstruct Itinerary
 * 这道题C++书里是归类到hash和多重集合下面 这是因为build graph在这里用了multiset 由于每对from to可能重复
 * 这也可以从java代码里不用set用linkedlist看出来
 * 另外一个重要的用途就是multiset是sorted (属于set), 这满足了我们一定能得到lexically smallest
 * 主题的思路仍然是某种图的遍历 C++书里用了stack 我还是觉得后序遍历符合我的直觉 意思是一样的 recursive本质上就是stack
 * 这里我们用后序遍历的原因是我们可以通过to list empty确认终点到达 然后基于此反推 如果前序遍历顺序就乱套了 后面的顺序都没定呢
 * 比如a可以到bc绕一圈 然后a -> d到达 这样我们如果先去了d 终点先确认了 推到track里确定 后面再跟上 这个顺序就是正确的 只要后面再reverse回来就可以了
 * 递归前要把next删掉, 避免死循环
*/
class Solution {
public:
    vector<string> findItinerary(vector<vector<string>>& tickets) {
        // build graph
        unordered_map<string, multiset<string>> map;
        for(const vector<string>& ticket : tickets) {
            map[ticket[0]].insert(ticket[1]);
        }
        vector<string> track;
        traverse(map, track, "JFK");
        reverse(track.begin(), track.end());
        return track;
    }
    void traverse(unordered_map<string, multiset<string>>& map, vector<string>& track, string current) {
        if(map.find(current) != map.end()) {
            while(!map[current].empty()) {
                string next = *(map[current].begin());
                map[current].erase(map[current].begin());
                traverse(map, track, next);
            }
        }
        track.push_back(current);
    }
};