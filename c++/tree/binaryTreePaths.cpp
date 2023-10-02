/**
 * 257. Binary Tree Paths
 * dfs 然后由于path要记录 要带上回溯 这套已经很熟悉了
 * 这里对撤回的使用更是有点心得的 每一层都负责将自己撤回 这样dfs左右子树的代码比较干净
 * 好了 easy题有什么得瑟的
*/
class Solution {
public:
    vector<string> binaryTreePaths(TreeNode* root) {
        vector<string> answer;
        vector<int> track;
        dfs(root, answer, track);
        return answer;
    }
    void dfs(TreeNode* root, vector<string>& answer, vector<int>& track) {
        if(!root) return;
        if(!root->left && !root->right) {
            string path_s;
            for(int i : track) {
                path_s += to_string(i) + "->";
            }
            path_s += to_string(root->val);
            answer.push_back(path_s);
            return;
        }
        track.push_back(root->val);
        dfs(root->left, answer, track);
        dfs(root->right, answer, track);
        track.pop_back();
    }
};