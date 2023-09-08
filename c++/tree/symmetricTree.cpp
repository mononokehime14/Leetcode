#include <include/listnode.h>
/**
 * 101. Symmetric Tree
 * 草了啊easy做不出来 我想用inorder 然后检查palindrome 但是有一个test case [1,2,2,2,null,2]
 * in order即便null也打印仍然是一样的 要想搞对就要加入左右或者高度信息 这就太复杂了 这个test case证明这个方法是取巧 不是标准解
 * 实际上还是可以仍然可以沿用分而治之的思路 需要一些修改 对称的比较实际上是left right 层数往下 变成left->left和right->right
 * 经过推算这个比较是完全正确的 这样我们的递归思路也就有了
*/
class Solution {
public:
    bool isSymmetric(TreeNode* root) {
        if(!root) return true;
        return helper(root->left, root->right);
    }
    bool helper(TreeNode* left, TreeNode* right) {
        if(!left && !right) return true;
        if(!left || !right) return false;
        if(left->val != right->val) return false;
        return helper(left->left, right->right) && helper(left->right, right->left);
    }
};