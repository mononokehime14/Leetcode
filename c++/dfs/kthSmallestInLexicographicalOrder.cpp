/**
 * 440. K-th Smallest in Lexicographical Order
 * 这道题是另外一道lexicographical的进阶 上一题中我们需要生成所有的数字 所以相当于遍历了所有
 * 正如上一图的记录中说的, 这是一个类似dfs的思路 对于一个数字 要不断的*10到底 生成, 然后/10回上一次
 * 1 2 3 ... 9
 * 11 12 13 ..
 * 111 112 ...
 * 那么对于这道题来说, 我们是不用碰到所有的leaf的, 可以通过计算nodes的数量, 如果小于k就可以跳过
 * 故此, 基本的思路是计算当前的n1, 不如11, 下面有多少个nodes.
 * 这一部分需要12的帮助, 可以看到往下一层, 111 ... 119, 数量是120 - 111
 * 这样我们有一个n2, 一开始是n1旁边的那个, 每一层的nodes就是n2 - n1
 * 这里有一个问题, 如果最大值n在子树中, 就会使得n2 > n, 这个时候应该用n + 1 - n1
 * 
 * 回到主函数, 如果steps > k, 说明kth就在当前子树中, 我们向下一层 
*/
class Solution {
public:
    int findKthNumber(int n, int k) {
        long cur = 1;
        k--; // 1 already there
        while(k) {
            int steps = calSteps(n, cur, cur + 1);
            if(steps <= k) {
                k -= steps;
                cur++;
            }else{
                cur *= 10;
                k -= 1;
            }
        }
        return cur;
    }
    int calSteps(int n, long n1, long n2) {
        int steps = 0;
        while(n1 <= n) {
            steps += min(n+1, (int)n2) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        return steps;
    }
};