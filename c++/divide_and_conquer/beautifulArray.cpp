#include <vector>
#include <numeric>
using namespace std;

/**
 * 932. Beautiful Array
 * 这道题虽然最后的解法是用sorting, 但是实质上思路是分而治之, 当然sorting本身就是和DAQ分不开的
 * 最开始, 我的思考是能够dp, 如果知道n-1或者左右两半的beautiful array, 能不能把n这个数字加进去
 * 但是没能想出来怎么去做, 事实上这个思路并不好像
 * 首先我们要想到, 2*A[k] != A[i] + A[j] 实际上意味着A[i] + A[j]无法整除
 * 那么奇数+偶数=奇数能够满足这个条件, 所以我们可以把奇数和偶数分开
 * 接下来怎么办呢, 要知道偶数分在一起, 2 4 6是不满足beautiful array的条件的
 * 答案是用binary的角度思考 我们可以理解为之前是用最后一个bit 0或者1分开了, 现在用倒数第二个bit
 * 以1-7为例, 奇数1 3 5 6会分成3 7和1 5, 左半边和右半边的数字随便挑加在一起能够整除, 但是整除之后是偶数不在这个队列里
 * 这是为什么呢? 倒数第二个bit先加成1 而这个队列里都是奇数最后一个bit都是1 这就导致两数和必然是4的倍数, which不在队列里
 * 接下去也是一样 3和7一加必然是8的倍数, 不可能在队列里
 * 所以思路就是分而治之的sort sort根据层数选择bit 当然这里可以直接用一步C++ custom sort替代
*/
class Solution {
public:
    static bool comp(const int & a, const int & b) {
        int mask = 1;
        while(true) {
            if((a&mask) == (b&mask)) {
                mask = mask << 1;
            }else{
                return (a&mask) > (b&mask); // parity 1 put at left
            }
        }
    }
    vector<int> beautifulArray(int n) {
        vector<int> answer(n);
        iota(answer.begin(), answer.end(), 1);
        sort(answer.begin(), answer.end(), comp);
        return answer;
    }
};