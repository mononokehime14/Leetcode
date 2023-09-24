#include <include/listnode.h>
/**
 * 124. Binary Tree Maximum Path Sum
 * 很快就能反应过来 答案不是直接return上去的 而是可能留在树中 这就需要answer作为reference传下去
 * 然后就是return只return带着root的path 还有一些细节就是left/right < 0抛弃掉 以及path带更大的一边
 * 这道题是多种思路的综合 入手还是那个经典的 带不带上root的角度
*/
class Solution {
public:
    int maxPathSum(TreeNode* root) {
        int answer = INT_MIN;
        helper(root, answer);
        return answer;
    }

    int helper(TreeNode* root, int & answer) {
        if(!root) return 0;
        int leftSum = max(0, helper(root->left, answer));
        int rightSum = max(0, helper(root->right, answer));
        //这里不需要用left或者right更新answer因为子树做过了
        answer = max(leftSum + rightSum + root->val, answer);
        return max(leftSum, rightSum) + root->val;
    }
};