/**
 * 397. Integer Replacement
 * 这道题是用dp这个思路是好想的, 但是这里如果bottom up有一个问题
 * 就是dp vector会超出memory limit, 因为n很大, 空间压缩比较麻烦, 因为需要dp[i/2]
 * 这里实际上可以top down用memo递归
 * 然后有一个trick,如果min(recursion(n-1), recursion(n+1)), 这应该是会infinite loop的
 * 因为总是新开+1的branch, 所以这里的处理是由于n-1 n+1都是偶数, 我帮它多走一步, 把偶数的处理算了
*/
class Solution {
public:
    int integerReplacement(int n) {
        unordered_map<int, int> memo;
        return recursion(n, memo);
    }
    int recursion(int n, unordered_map<int, int> & memo) {
        if(n == 1) return 0;
        if(memo.find(n) != memo.end()) return memo[n];
        memo[n] = n % 2 == 0 ? recursion(n/2, memo) + 1 : 
            2 + min(recursion(n/2, memo), recursion(n/2 + 1, memo));
        return memo[n];
    } 
};