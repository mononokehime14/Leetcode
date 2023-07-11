#include <vector>
#include <numeric>

using namespace std;

/**
 * 135 Candy
 * 这道题实际上是贪心思路. 一开始我的思路是, 从左到右, 就像走台阶一样, 如果比前面一个数字大就++, 小就降阶--
 * 这样台阶就有可能变成负数, 所以要track一个最小值, 最后扫一遍得到台阶和最小值的差的和
 * 但是这个方法是错误的, 因为题目里一旦i和i-1相同, 那么完全看后面还有什么, 如果后面没有比当前i更小的, i完全
 * 可以是1, 如果后面有比i更小的, 那么i就是后面最小的+1.
 * 所以, 这里就可以看出来, 怎么样都还需要从右边再扫过来一遍. 而这也是最后的答案. 注意答案的写法, 很巧妙的摆脱了count,
 * 纯用steps递增或者直接base值, 最后用accumulate求和(第三个参数是sum的初始值)
 * 
 * 乍一看这是一个array题, 但是贪心思路的定义, 其实并不是利用极值, 而是保证每次操作都是局部最优的，从而使最后得到的结果是全局最优的
 * 比如小明和小王喜欢吃苹果，小明可以吃五个，小王可以吃三个。已知苹果园里有吃不完的苹果，求小明和小王一共最多吃多少个苹果
 * 在这个例子中，我们可以选用的贪心策略为，每个人吃自己能吃的最多数量的苹果，这在每个人身上都是局部最优的
 * 又因为全局结果是局部结果的简单求和，且局部结果互不相干，因此局部最优的策略也同样是全局最优的策略
 * 所以这里的贪心策略就是每扫一遍只取局部最优, 两遍合一起是全局最优 
*/
class Solution {
public:
    int candy(vector<int>& ratings) {
        int current = 0, size = ratings.size();
        vector<int> steps(size, 1);
        for(int i = 1;i < ratings.size();i++) {
            if(ratings[i] > ratings[i-1]) {
                steps[i] = steps[i-1] + 1;
            }
        }
        for(int i = size - 1;i > 0;i--) {
            if(ratings[i] < ratings[i-1]) {
                steps[i-1] = max(steps[i-1], steps[i]+1);
            }
        }
        return accumulate(steps.begin(), steps.end(), 0);
    }
    // int candy(vector<int>& ratings) {
    //     int current = 0, lowest = 0, count = 0;
    //     vector<int> steps(ratings.size(), 0);
    //     for(int i = 1;i < ratings.size();i++) {
    //         if(ratings[i] > ratings[i-1]) {
    //             current += 1;
    //         }else if(ratings[i] < ratings[i-1]) {
    //             current -= 1;
    //         }
    //         lowest = min(lowest, current);
    //         steps[i] = current;
    //     }
    //     for(auto s :  steps) {
    //         count += (s - lowest) + 1;
    //     }
    //     return count;
    // }
};