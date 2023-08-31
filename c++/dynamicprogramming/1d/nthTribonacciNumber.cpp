#include <vector>
using namespace std;
/**
 * 1136. N-th Tribonacci Number
 * 没有任何意义的斐波那契数列变种, 有意思的答案的vector使用, 可以替代复杂的prev变量
*/
class Solution {
public:
    int tribonacci(int n) {
        int first = 0, second = 1, third = 1, fourth = 0;
        if(n < 3) return n > 0 ? 1 : 0;
        for(int i = 3;i <= n;++i) {
            fourth = third + second + first;
            first = second;
            second = third;
            third = fourth;
        }
        return fourth;
        // vector<int> seq(3);
        // seq[1] = seq[2] = 1;

        // for (int i = 3; i <= n; ++i)
        //     seq[i % 3] = seq[(i - 1) % 3] + seq[(i - 2) % 3] + seq[(i - 3) % 3];

        // return seq[n % 3];
    }
};