#include <include/listnode.h>
/**
 * 450. Delete Node in a BST
 * 在删除中比较难想的是左右子树都存在的情况下 我们想要以右子树的最左边的node为新的node
 * 这时候可以one pass寻找+删除吗? 有点难区分第一个就是的情况(root->right->left == null)
 * 故此干脆再call一遍方法 把left most node从右边子树删掉 然后把val换到当前的node鸠占鹊巢
*/
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    TreeNode* deleteNode(TreeNode* root, int key) {
        if(!root) return NULL;
        if(root->val > key) {
            root->left = deleteNode(root->left, key);
            return root;
        }else if(root->val < key) {
            root->right = deleteNode(root->right, key);
            return root;
        }else{
            if(!root->left && !root->right) return NULL;
            else if(!root->left && root->right) return root->right;
            else if(root->left && !root->right) return root->left;
            else{
                TreeNode* successor = findLeftMost(root->right);
                root->val = successor->val;
                root->right = deleteNode(root->right, root->val);
                return root;
            }
        }
    }
    TreeNode* findLeftMost(TreeNode* root) {
        if(!root) return NULL;
        return root->left ? findLeftMost(root->left) : root;
    }

};