#include <include/listnode.h>
/**
 * 669. Trim a Binary Search Tree
 * 是自己做出来哒 使用了独孤九剑中记载的 BST尝试想象展平一个升序数列的思路
 * 这里我们只需要注意一件事情 就是我不行 我的left right可能行
 * 比如我小了 我的right可能满足条件 但是我的right绝对小雨我的parent 故此我可以直接return出去
 * 那么这就需要后序遍历 我的right要先算好才行
*/
class Solution {
public:
    TreeNode* trimBST(TreeNode* root, int low, int high) {
        return traverse(root, low, high);
    }

    TreeNode* traverse(TreeNode* root, int low, int high) {
        if(!root) return nullptr;
        root->left = traverse(root->left, low, high);
        root->right = traverse(root->right, low, high);
        if(root->val < low) return root->right;
        else if(root->val > high) return root->left;
        return root;
    }
};