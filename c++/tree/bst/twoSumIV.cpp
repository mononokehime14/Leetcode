#include <include/listnode.h>
/**
 * 653. Two Sum IV - Input is a BST
 * 这道题笨蛋办法就是展平变成普通的two sum
 * 另外的办法就是直接在BST中search 套用通用思路: include当前node还是不include
 * 但是我认为复杂度有nlogn 对于每个node如果决定尝试都是logn的搜索成本
 * 还有一个地方就是不能在当前subtree搜 而是要手伸到另一边 这样实现不是很方便
 * 所以直接提了一个笨蛋办法滚了
*/
class Solution {
public:
    bool findTarget(TreeNode* root, int k) {
        unordered_set<int> mem;
        return traverse(root, mem, k);
    }
    bool traverse(TreeNode* root, unordered_set<int>& mem, int k) {
        if(!root) return false;
        if(mem.find(k - root->val) != mem.end()) return true;
        mem.insert(root->val);
        return traverse(root->left, mem, k) || traverse(root->right, mem, k);
    }
};