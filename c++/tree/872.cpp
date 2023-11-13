#include <include/listnode.h>
/**
 * 872. Leaf-Similar Trees
 * 采用了最粗暴的方法, 第一遍将roo1的leaves按顺序记录, 第二遍将roo2的leaves按顺序检查
*/
class Solution {
public:
    bool leafSimilar(TreeNode* root1, TreeNode* root2) {
        vector<int> leaves;
        bool answer = true;
        int index = 0;
        firstTraverse(root1, leaves);
        for(int i : leaves) cout << i << " ";
        cout << endl;
        secondTraverse(root2, leaves, index, answer);
        return answer && index == leaves.size(); // 可能root2 leaves数量不一致
    }

    void firstTraverse(TreeNode* root, vector<int>& leaves) {
        if(!root) return;
        if(!root->left && !root->right) leaves.push_back(root->val);
        firstTraverse(root->left, leaves);
        firstTraverse(root->right, leaves);
    }

    void secondTraverse(TreeNode* root, vector<int>& leaves, int& index, bool& answer) {
        if(!root) return;
        if(!answer) return;
        if(!root->left && !root->right) {
            if(index >= leaves.size() || leaves[index] != root->val) answer = false;
            index++;
            return;
        }
        secondTraverse(root->left, leaves, index, answer);
        secondTraverse(root->right, leaves, index, answer);
    }
};