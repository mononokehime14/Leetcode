/**
 * 386. Lexicographical Numbers
 * 这道题的思路只能说太难想了 只能大概的猜测到 可以计算出每个数字应该在的位置
 * 实际上的思考方式是 1 11 111 这就证明 给定一个数字 要先一条路到底 这就是为什么只要cur * 10 <= n要一直乘
 * 然后是10 11 12, 这里是++, 但是有个问题就是有可能加超 那这里很巧妙 13 + 1 > 13, 这时候就/=10, 变成1再++
 * 相当于新开branch了
 * 如果不加超, 19 20也是不对的, 必须要19 2 20, 那么这里的处理也是/=10
 * 这种思路到底如何当场想出来, 尚未有头绪
*/
class Solution {
public:
    vector<int> lexicalOrder(int n) {
        vector<int> answer(n);
        int cur = 1;
        for(int i = 0;i < n;++i) {
            answer[i] = cur;
            if(cur * 10 <= n) cur *= 10;
            else{
                if(cur >= n) cur /= 10;
                ++cur;
                while (!(cur % 10)) cur /= 10;
            }
        }
        return answer;
    }
};