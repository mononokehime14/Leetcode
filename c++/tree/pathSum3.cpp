#include <include/listnode.h>
/**
 * 437. Path Sum III
 * 一下子思路没有想出来, 整体上仍然是binary tree普遍的分而治之, 但是这里我们需要考虑所有的path
 * 对于当前node, 可以选择加入或者不加入path 我本来考虑维持一个hashmap 记录不同的path sum 但是后面递归下去是没办法知道要在哪个key上运算的
 * 正确答案是要发现 如果我们当前的node加入path 之后的递归就要一直带着它(path要连续) 那么这里分类讨论
 * 如果不带 就直接递归left和right 带就单独用traverse helper计算
*/
class Solution {
public:
    int ans=0;
    long long pathSumWithRoot(TreeNode* root, long long sum){
        if(!root) return 0;
        long long count = root->val == sum ? 1 : 0;
        return count + pathSumWithRoot(root->left, sum - root->val) + pathSumWithRoot(root->right, sum - root->val);
    }
    int pathSum(TreeNode* root, int targetSum) {
        if(!root) return 0;
        return pathSumWithRoot(root,targetSum) + pathSum(root->left,targetSum) + pathSum(root->right,targetSum);
    }
};