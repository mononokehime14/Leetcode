#include <include/listnode.h>
using namespace std;
/**
 * 543. Diameter of Binary Tree
 * C++重写 注意在return和最终答案不同的情况下 可以用reference更新最大值
 * 在java中我一般用global variable C++ reference很好用 比pointer方便很多 可以直接赋值修改
*/
class Solution {
public:
    int diameterOfBinaryTree(TreeNode* root) {
        int answer = 0;
        traverse(root, answer);
        return answer;
    }
    int traverse(TreeNode* root, int& answer) {
        if(!root) return 0;
        int left_depth = traverse(root->left, answer);
        int right_depth = traverse(root->right, answer);
        answer = max(answer, left_depth + right_depth);
        return 1 + max(left_depth, right_depth);
    }
};