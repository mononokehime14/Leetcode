/**
 * 600. Non-negative Integers without Consecutive Ones
 * non-negativeIntegersWithoutConsecutiveOnes
 * 这道题实在是精妙至极 首先是, 长度为k的binary number, 不含连续1的integer数量是斐波那契数列
 * 以5为例子, 范围00000 - 11111, 可以分成两部分00000 - 01111, 10000 - 11111
 * 前者就是0000-1111, 也就是f(4) 后者则只需要考虑10000 - 10111, 为什么呢, 因为11xxx肯定不可能, 已经有两个连续的1了
 * 而10000-10111数量等同于000-111, 也就是f(3), 妙哉妙哉.
 * 
 * 到这一步, 我们还是不知道怎么对于n进行计算. 这里我们要用选择树的方式去思考. 假设从左到右(most significant -> least significant)
 * 我们碰到一个1, 前面是一个0, 01. 那我们可以开两个branch, 以00开头, 右边有k位我们就有f(k)个数字, 全部合法, 因为肯定小于n
 * 以01开头, 我们就要继续往右验证了. 如果右边又是一个1, 011. 那我们可以选择010开头, 右边有k位我们就有f(k)个, 全部合法
 * 011开头则是不可能的, 因为已经有两个连续的1了. 到这里, 所有子树的candiate数量都算进去了, 我们完成计算了
 * 最后的一个细节就是如果一直没有连续的11, 那n本身也是合法的, 我们得补上+1, 因为上述的算法都是数小于n的
*/
class Solution {
public:
    int findIntegers(int n) {
        vector<int> dp(32, 0);
        dp[0] = 1;
        dp[1] = 2;
        for(int i = 2;i < 32;++i) dp[i] = dp[i-1] + dp[i-2];
        int answer = 0, prev = 0;
        for(int i = 30;i >= 0;--i) {
            if(n & (1 << i)) {
                answer += dp[i];
                if(prev == 1) return answer;
                prev = 1;
            }else{
                prev = 0;
            }
        }
        return answer + 1;
    }
};