#include <include/listnode.h>
/**
 * 513. Find Bottom Left Tree Value
 * 帅 一遍过 思路实际上类似dfs 但是每次都要先左边 这样就能最左边的先更新
 * 然后就可以放心的发现深度更新就更新答案就可以了
*/
class Solution {
public:
    int findBottomLeftValue(TreeNode* root) {
        int answer = root->val, max_depth = 0;
        traverse(root, 0, answer, max_depth);
        return answer;
    }

    void traverse(TreeNode* root, int depth, int & answer, int & max_depth) {
        if(!root) return;
        traverse(root->left, depth+1, answer, max_depth);
        if(depth > max_depth) {
            max_depth = depth;
            answer = root->val;
        }
        traverse(root->right, depth+1, answer, max_depth);
    }
};