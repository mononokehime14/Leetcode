#include <include/listnode.h>
using namespace std;
/**
 * 894. All Possible Full Binary Trees
 * 没看清题目在那里瞎做, 准备用备忘录的DP数Tree的数量
 * 但是题目要求构建所有的tree, 故此这里直接递归就可以了
 * 对于n, 可以想象1-n中间任何一个做root, tree的数量就是递归i-1和递归n-i两边
 * 注意这里要深拷贝树. 这种递归拷贝法也非常有意思
*/
class Solution {
public:
    TreeNode* copyroot(TreeNode* root) {
        if (!root) return NULL;
        TreeNode* ret = new TreeNode(0);
        ret->left = copyroot(root->left);
        ret->right = copyroot(root->right);
        return ret;
    }
    vector<TreeNode*> allPossibleFBT(int n) {
        if (n % 2 == 0) return vector<TreeNode*>(); // must has 0 or 2 children, meaning n must be odd
        TreeNode* root = new TreeNode(0);
        if (n == 1) return vector<TreeNode*>{root};
        vector<TreeNode*> answer;
        --n;
        for (int i = 1; i <= n; i += 2) {
            vector<TreeNode*> left = allPossibleFBT(i);
            vector<TreeNode*> right = allPossibleFBT(n-i);
            for (auto l: left) {
                for (auto r: right) {
                    root->left = l;
                    root->right = r;
                    answer.push_back(copyroot(root));
                }
            }
        }
        return answer;
    }
};