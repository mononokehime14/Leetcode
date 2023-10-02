#include <include/listnode.h>
/**
 * 173. Binary Search Tree Iterator
 * 这道题最暴力的思路很好想 直接inorder展平就好了 但是如果space要求O(h) h为树高呢
 * 答案非常巧妙 很震撼 利用了stack high level的来讲的话 就是用stack存了最左边的一批nodes
 * 这样 它们的右子树暂时不入栈 相当于先封存起来
 * 而当我们next到一个node的时候 我们就把它的右子树展开 将右子树里的左边nodes存入stack
 * 由于stack的特性 这个order完全没有问题 时间成本上 root展开右子树需要O(h) 但是每次展开也意味下h个elements本身可以直接拿到值
 * 这样思考的话 整个树的next全部走完是N 平均每次就是O(1)了 太妙了
*/
class BSTIterator {
    stack<TreeNode*> s;
public:
    BSTIterator(TreeNode* root) {
        while(root) {
            s.push(root);
            root = root->left;
        }
    }
    
    int next() {
        TreeNode* current = s.top();
        s.pop();
        if(current->right) {
            TreeNode* temp = current->right;
            while(temp) {
                s.push(temp);
                temp = temp->left;
            }
        }
        return current->val;
    }
    
    bool hasNext() {
        return !s.empty();
    }
};

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator* obj = new BSTIterator(root);
 * int param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */