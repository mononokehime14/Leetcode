#include <include/listnode.h>
/**
 * 897. Increasing Order Search Tree
 * 捣鼓了一种奇妙的return重新construct过的tree的start end的方法
 * 还是向最intuitive的方法低头 复杂度是一样的 为什么要搞这么麻烦呢?
*/
// struct returnT {
//     TreeNode *start;
//     TreeNode *end;
//     returnT(TreeNode *start, TreeNode *end) : start(start), end(end) {}
// };
class Solution {
public:
    TreeNode* increasingBST(TreeNode* root) {
        vector<TreeNode*> order;
        inorder(root, order);
        int size = order.size();
        for(int i = 0;i < size - 1;++i) {
            order[i]->left = nullptr;
            order[i]->right = order[i+1];
        }
        order[size - 1]->left = nullptr;
        order[size - 1]->right = nullptr;
        return order[0];
    }

    void inorder(TreeNode* root, vector<TreeNode*> &order) {
        if(!root) return;
        inorder(root->left, order);
        order.push_back(root);
        inorder(root->right, order);
    }

    // returnT* helper(TreeNode* root) {
    //     if(!root) return new return(nullptr, nullptr);
    //     returnT* leftT = helper(root->left);
    //     returnT* rightT = helper(root->right);
    //     if(leftT->end) leftT->end->right = root;
    //     root->left = nullptr;
    //     root->right = rightT->start;
    //     TreeNode* start = !leftT->start ? root : leftT->start;
    //     TreeNode* end = rightT->end;
    //     return new returnT(start, end);
    // }
};