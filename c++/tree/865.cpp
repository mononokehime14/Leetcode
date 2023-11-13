#include <include/listnode.h>
/**
 * 865. Smallest Subtree with all the Deepest Nodes
 * 一边求depth, 一边判断. 如果左子树的depth更大, 那么不需要include自己和右子树, 左子树自己就是答案
 * 同理depth相同, 得return root, root可能是答案, 要上边再判断
*/
class Solution {
public:
    TreeNode* subtreeWithAllDeepest(TreeNode* root) {
        int depth = 0;
        return traverse(root, depth);
    }
    TreeNode* traverse(TreeNode* root, int& depth) {
        if(!root) return root;
        int left_depth = 0;
        TreeNode* left_sub = traverse(root->left, left_depth);
        int right_depth = 0;
        TreeNode* right_sub = traverse(root->right, right_depth);
        if(left_depth > right_depth) {
            depth = left_depth + 1;
            return left_sub;
        }else if(left_depth < right_depth) {
            depth = right_depth + 1;
            return right_sub;
        }else{
            depth = left_depth + 1;
            return root;
        }
    }
};