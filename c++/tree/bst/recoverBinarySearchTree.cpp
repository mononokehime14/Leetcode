#include <include/listnode.h>
/**
 * 99. Recover Binary Search Tree
 * 这道题的思路一下子还不好想 我们可以从中序遍历的角度思考 BST用中序遍历展平其实就是升序数列
 * 如何找到一个本来升序的数列中调换的两个数字呢 第一个会比后一个数字大 第二个会比前一个数字小
 * 如果我们将找到第一个作为目标 则第二个又要另一套
 * 这里选择的方法是记录升序数列的前一个数字 一旦发现它比当前大 那么当前在两个位置 一个是第一个错误的后一个 第二个就是在第二个错误
 * 这样我们就更新两个mistake 出来换一下就行了
 * 
 * 比起java版本 这里不用额外的vector来展平BST 
*/
class Solution {
public:
    void recoverTree(TreeNode* root) {
        TreeNode *firstMis = nullptr, *secondMis = nullptr, *prev =nullptr;
        traverse(root, firstMis, secondMis, prev);
        int temp = firstMis->val;
        firstMis->val = secondMis->val;
        secondMis->val = temp;
    }

    void traverse(TreeNode* root, TreeNode*& firstMis, TreeNode*& secondMis, TreeNode*& prev) {
        if(!root) return;
        traverse(root->left, firstMis, secondMis, prev);
        if(prev && root->val < prev->val) {
            if(!firstMis) {
                firstMis = prev;
                secondMis = root;
            }else{
                secondMis = root;
            }
        }
        prev = root;
        traverse(root->right, firstMis, secondMis, prev);
    }
};