/**
 * 857. Minimum Cost to Hire K Workers
 * 非常巧妙的方法, sort + sliding window + priority queue
 * 首先是大思路, 大思路是计算wage / quality的ratio, 按照这个ratio排升序, 然后滑动窗口
 * 为什么要计算这个ratio? 为什么每一组的wage sum = quality sum * 最大的ratio?
 * 设一个人得到的wage为w, 期待的wage是w_e, quality是q, 最后整组需要的wage是wage_s, quality sum是q_s
 * 首先是w >= w_e, 然后是 w / w_s = q / q_s
 * 后者可以推出w_s = (w * q_s) / q, 也就是 (w/q) * q_s, 这里我们要思考, 要满足一个group里每个人w_e的要求
 * 这个w/q只能是受组里这个ratio最大的影响, 也就是说, 最后的w_s是group中最大的w_e/q * quality sum
 * 推出这个公式之后, 方法就很合理了, 滑动窗口检查, 按照ratio sort可以保证每次新加入窗口的都是该group的最大ratio
 * priority queue则是因为不需要substring一样的窗口, 前面要pop掉的不是窗口第一个, 而是quality最大的那个
 * 这样可以尝试得到min w_s, 因为max ratio肯定就是新加入的嘛, 那么剩下一个取决因素就是quality sum, pop quality最大的能够尽量将其变小
*/
class Solution {
public:
    double mincostToHireWorkers(vector<int>& quality, vector<int>& wage, int k) {
        int n = quality.size();
        vector<pair<double, int>> ratio_quality(n);
        for(int i = 0;i < n;++i) ratio_quality[i] = make_pair(static_cast<double>(wage[i]) / quality[i], quality[i]);
        sort(ratio_quality.begin(), ratio_quality.end(), [](pair<double, int>& a, pair<double, int>& b){
            return a.first < b.first; //升序
        });
        priority_queue<int> pq;
        int quality_sum = 0;
        for(int i = 0;i < k;++i) {
            quality_sum += ratio_quality[i].second;
            pq.push(ratio_quality[i].second);
        }
        double answer = quality_sum * ratio_quality[k-1].first;
        for(int i = k;i < n;++i) {
            quality_sum += ratio_quality[i].second;
            pq.push(ratio_quality[i].second);
            quality_sum -= pq.top();
            pq.pop();
            answer = min(answer, quality_sum * ratio_quality[i].first);
        }
        return answer;
    }
};