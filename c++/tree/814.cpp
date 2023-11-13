#include <include/listnode.h>
using namespace std;
/**
 * 814. Binary Tree Pruning binaryTreePruning
 * 比较简单, 需要分清两部分的功能, 统计substree是否有1, 以及pruning
 * 这里受到之前经验的影响, 我helper return TreeNode*, 以便parent直接通过assign达成prune的目的
 * 如此一来, 需要一个额外的return值来记录是否有1
 * 不过这里有一点特殊性, 就是它不是对subtree有什么特殊的改动, 完全的prune
 * 故此实际上优化一下, return是否有1, 无1直接改成null, 有1不动, 这样就可以了
*/
class Solution {
public:
    TreeNode* pruneTree(TreeNode* root) {
        // bool has1 = false;
        // TreeNode* answer = traverse(root, has1);
        // return has1 ? answer : NULL;
        bool has1 = traverse2(root);
        return has1 ? root : NULL;
    }

    bool traverse2(TreeNode* root) {
        if(!root) return false;
        bool leftHas1= traverse2(root->left);
        bool rightHas1 = traverse2(root->right);
        if(!leftHas1 && !rightHas1 && root->val != 1) return false;
        if(!leftHas1) root->left = NULL;
        if(!rightHas1) root->right = NULL;
        return root;
    }

    TreeNode* traverse(TreeNode* root, bool& has1) {
        if(!root) {
            has1 = false;
            return root;
        }
        bool leftHas1 = false;
        TreeNode* left_sub = traverse(root->left, leftHas1);
        bool rightHas1 = false;
        TreeNode* right_sub = traverse(root->right, rightHas1);
        if(!leftHas1 && !rightHas1 && root->val != 1) {
            has1 = false;
            return NULL;
        }
        has1 = true;
        if(leftHas1) root->left = left_sub;
        else root->left = NULL;
        if(rightHas1) root->right = right_sub;
        else root->right = NULL;
        return root;
    }
};