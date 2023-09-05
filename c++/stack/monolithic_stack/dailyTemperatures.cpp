#include <vector>
using namespace std;
/**
 * 739. Daily Temperatures
 * 单调栈 就是栈内保持递增 以目前的理解 这个可以挤压不满足条件的暂时值 并在满足条件的值来的时候一下完成计算
 * 这和stack用于比如括号检查的原理实际上是一致的 只不过挤压的条件是递增罢了
 * 这里stack记录index 如果一直更小就挤压 出现一个更高就清算
*/
class Solution {
public:
    vector<int> dailyTemperatures(vector<int>& temperatures) {
        int n = temperatures.size();
        stack<int> s;
        vector<int> answer(n, 0);
        for(int i = 0;i < temperatures.size();i++) {
            while(!s.empty()) {
                int ref = s.top();
                if(temperatures[i] <= temperatures[ref]) break;
                s.pop();
                answer[ref] = i - ref;
            }
            s.push(i);
        }
        return answer;
    }
};