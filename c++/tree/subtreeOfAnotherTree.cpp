#include <include/listnode.h>
/**
 * 572. Subtree of Another Tree
 * 这道题和pathSum3的思路是一样的 可以理解为node加不加入分开讨论 node加入就单独一条路走到黑
*/
class Solution {
public:
    bool isSubtree(TreeNode* root, TreeNode* subRoot) {
        if(!root && !subRoot) return true;
        if(!root || !subRoot) return false;
        bool include = isSubTreeWithRoot(root, subRoot);
        bool notInclude = isSubtree(root->left, subRoot);
        notInclude |= isSubtree(root->right, subRoot);
        return include || notInclude;
    }
    bool isSubTreeWithRoot(TreeNode* root, TreeNode* subRoot) {
        if(!root && !subRoot) return true;
        if(!root || !subRoot) return false;
        return root->val == subRoot->val ? isSubTreeWithRoot(root->left, subRoot->left) &&
            isSubTreeWithRoot(root->right, subRoot->right) : false;
    }
};