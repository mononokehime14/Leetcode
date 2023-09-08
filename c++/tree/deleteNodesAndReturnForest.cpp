/**
 * 1110. Delete Nodes And Return Forest
 * 非常有意思的一题 主要的问题在于delete高层的node就无法再去delete底层的 
 * 根据我的思路 要不然就每次暴力搜索所有的nodes 复杂度爆炸 要不然就sort nodes所以我们先delete底层
 * 我们可以用后序遍历达成这一点 因为后序遍历天生满足了底层先处理的效果
 * 这里还有一个很有意思的地方 删除怎么删除 我本来是想检查answer vector的尾部 如果有push进去的left或者right
 * 就说明需要删除 但是这里更好的方法是通过return 要delete的直接return null parent那边赋值 这样出来之后要单独判断一下root因为root没有parent来判断了
*/
class Solution {
public:
    vector<TreeNode*> delNodes(TreeNode* root, vector<int>& to_delete) {
        unordered_set<int> to_delete_hash;
        for(const int d : to_delete) to_delete_hash.insert(d);
        vector<TreeNode*> answer;
        root = traverse(root, to_delete_hash, answer);
        if(root) answer.push_back(root);
        return answer;
    }

    TreeNode* traverse(TreeNode* root, unordered_set<int>& to_delete_hash, vector<TreeNode*>& answer) {
        if(!root) return root;
        root->left = traverse(root->left, to_delete_hash, answer);
        root->right = traverse(root->right, to_delete_hash, answer);
        if(to_delete_hash.find(root->val) != to_delete_hash.end()) {
            to_delete_hash.erase(root->val);
            if(root->left) answer.push_back(root->left);
            if(root->right) answer.push_back(root->right);
            return nullptr;
        }
        return root;
    }
};