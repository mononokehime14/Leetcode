/**
 * 404. Sum of Left Leaves
 * 虽然是简单题, 但是出于对传递“我是左child还是右child”这种flag我有一些隐忧, 故此想了想别的方法
 * 这里就是这样就可以了, 这个flag每次递归都是固定的, 不随parent变化, 这就没问题的
*/
class Solution {
public:
    int sumOfLeftLeaves(TreeNode* root) {
        int answer = 0;
        traverse(root, false, answer);
        return answer;
    }
    void traverse(TreeNode* root, bool left, int & answer) {
        if(!root) return;
        if(!root->left && !root->right && left) {
            answer += root->val;
        }
        traverse(root->left, true, answer);
        traverse(root->right, false, answer);
    }
};