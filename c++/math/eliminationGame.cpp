/**
 * 390. Elimination Game
 * 这道题的recursion推理不看答案怎么知道
 * 首先是发现从left开始推left(n), 等于2 * right(n/2), 原理未知, n奇偶都是成立
 * 然后这样还不能递归, 要right转化成left
 * right(n) = n - left(n) + 1 这一步可以理解为对称的另一边
 * 故此, left(n) = 2 * (n/2 - left(n/2) + 1)
*/
class Solution {
public:
    int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 - lastRemaining(n/2) + 1);
    }
};