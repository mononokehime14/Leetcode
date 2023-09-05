#include <vector>
using namespace std;
/**
 * 149. Max Points on a Line
 * 这道题一开始的想法竟然是有希望的 我一开始想 直接n平方找出所有的线 然后再判断线上的点
 * 事实上这两步可以并行进行, 一个点不动的情况下, 其余点只要和它算出来的斜率一样, 就必然在一条线上
 * 不用考虑bias 所以如果我们先找出所有的线再去判断点, 计算是很复杂的, 而一个点不动其余点通过斜率判断
 * 这样计算方便. 那么我们用hash map记录对于一个点的所有斜率 这样就得到答案了
 * 实现中我们发现斜率有几种0的可能 同y, 同x, 已经同一个点, 为了分开这三种情况, 我们就对同y和同一个点单独count
*/
class Solution {
public:
    int maxPoints(vector<vector<int>>& points) {
        unordered_map<double, int> affine;
        int size = points.size(), ans = 0;
        for(int i = 0;i < size;i++) {
            int same_y = 1, exact_same = 1;
            for(int j = i + 1;j < size;j++) {
                if(points[i][1] == points[j][1]){
                    same_y++;
                    if(points[i][0] == points[j][0]) {
                        exact_same++;
                    }
                }else{
                    double dx = points[i][0] - points[j][0];
                    double dy = points[i][1] - points[j][1];
                    affine[dx/dy]++; 
                }
            }
            ans = max(same_y, ans);
            for(auto [k, v] : affine) ans = max(ans, exact_same + v);
            affine.clear();
        }
        return ans;
    }
};