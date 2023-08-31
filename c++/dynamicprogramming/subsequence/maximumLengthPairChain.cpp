#include <vector>
using namespace std;

/**
 * 646. Maximum Length of Pair Chain
 * 毕竟还是给我搞出来了, 通过这道题, 完全的理解了LIS最快的nlogn算法的考虑
 * 我们知道, 我们需要保持一个一个的堆, 对于LIS来说, 遍历一遍数组, 找到堆中第一个能够大于等于它的
 * 这样我们就能把当前数字更新堆顶. 可是这一步和LIS有什么关系呢, 为什么最后堆的数量就是LIS呢?
 * 我们可以理解最后的subsequence是堆顶连起来的, 这就是为什么堆的数量就是LIS的长度 
 * 所以实际上这样更新是为了增大之后能够构成序列的可能性! 将更小的数字放到堆顶, 那么后面的数字就更有可能开新堆
 * 
 * 在这道题目中, 题目出现了两个变种. 第一个是不再是只有一个数字, 而是一个区间. 但是只要把握这个逻辑, 那么仍然是
 * 不变的: 当前区间完全大于堆顶, 则需要开新堆, 不然就可以尝试往堆顶上放; 这里不同的是, 我们不一定就得到了一个更合适的
 * 区间, 而是要比较右边界, 如果是更小, 那么我们才更新, 这样才能增大后面构成序列的可能性
 * 第二个是不必follow原数组的order, 这就导致我们必须先sort一下原数组才能算出正确的答案
 * 
 * 当然, 搞得这么帅, 还是nlogn, 实际上直接sort, 遍历一遍组成subsequence就可以了
*/

class Solution {
public:
    int findLongestChain(vector<vector<int>>& pairs) {
        int n = pairs.size();
        sort(pairs.begin(), pairs.end());
        vector<vector<int>> piles(n, vector<int>(2, 0));
        piles[0] = pairs[0];
        int npiles = 1;
        for(int i = 1;i < n;i++) {
            auto p = pairs[i];
            // search first greater right bouding, so that put on top, increase the chance
            // that later interval can chain
            int left = 0, right = npiles - 1;
            while(left <= right) {
                int mid = left + (right - left) / 2;
                if(piles[mid][1] < p[0]) {
                    left = mid + 1;
                    // cout << "compare pile (" << p[0] << "," << p[1] <<") with (" << piles[mid][0] << "," << piles[mid][1] <<") fail" << endl;
                }else{
                    right = mid - 1;
                    // cout << "compare pile (" << p[0] << "," << p[1] <<") with (" << piles[mid][0] << "," << piles[mid][1] <<") success" << endl;
                }
            }
            if(left == npiles) { // new pile
                piles[npiles] = p;
                npiles++;
                // cout << "new pile " << p[0] << " " << p[1] << endl;
            }else if(p[1] < piles[left][1]){
                // cout << "update pile " << piles[left][0] << " " << piles[left][1] << " to " << p[0] << " " << p[1] << endl;
                piles[left] = p;
            }
        }
        return npiles;
    }
};