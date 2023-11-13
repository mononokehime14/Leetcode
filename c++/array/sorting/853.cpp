/**
 * 853. Car Fleet
 * 需要先对position排序,降序, 这样我们就能每次处理最右边的car
 * 题目问的是有多少个fleet, 不是fleet最多有多少车 所以我们对于最右边的车, 看看比他更左的车到达target的时间
 * 如果比他多, 说明不会碰到他, 那么他的fleet就结束, 我们算下一个fleet
*/
class Solution {
public:
    int carFleet(int target, vector<int>& position, vector<int>& speed) {
        int n = position.size(), answer = 1;
        vector<pair<int, double>> pos_speed(n);
        for(int i = 0;i < n;++i) pos_speed[i] = make_pair(position[i], static_cast<double>(target - position[i]) / speed[i]);
        sort(pos_speed.begin(), pos_speed.end(), [](pair<int, double>& a, pair<int, double>& b){
            return a.first > b.first; //降序
        });
        double time = pos_speed[0].second;
        for(int i = 1;i < n;++i) {
            if(pos_speed[i].second > time) {
                answer++;
                time = pos_speed[i].second;
            }
        }
        return answer;
    }
};