/**
 * 687. Longest Univalue Path
 * 这是经典的include root还是不include分类讨论的类型
 * 这里稍有变动, 首先在return的值中, 必然要include root, 不然就连不起来了...
 * 所以思路是这个思路, 但是变通还是很大的
 * 同时经典的path从左到右, 不继续往上, 那这样就需要一个path来记录递归中这种路径出现的最大值
 * 最后要注意的细节在于三个node连在一起return 2, 要的是edge的数量, 故此注意这里的处理, 先对left right path进行了+1处理
 * 然后从左到右往下的path相当于新加了两个edge
*/
class Solution {
public:
    int longestUnivaluePath(TreeNode* root) {
        int path = 0;
        int path2 = traverse(root, path);
        return max(path, path2);
    }

    int traverse(TreeNode* root, int& path) {
        if(!root) return 0;
        int left_path = traverse(root->left, path);
        int right_path = traverse(root->right, path);
        left_path = root->left && root->val == root->left->val ? left_path + 1 : 0;
        right_path = root->right && root->val == root->right->val ? right_path + 1 : 0;
        path = max(path, left_path + right_path);
        return max(left_path, right_path);
    }
};