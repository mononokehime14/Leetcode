#include <include/listnode.h>
/**
 * 2583. Kth Largest Sum in a Binary Tree
 * 就是用BFS遍历层 然后统计K最小的
 * 这里是用的是logn*logk的方法 直接用PQ维持大小为k的最小堆 这样在k小的情况下时间成本不高
 * 统共push logn次 这是balance二叉树的情况下
*/
class Solution {
public:
    long long kthLargestLevelSum(TreeNode* root, int k) {
        if(!root) return -1;
        priority_queue<long long int,vector<long long int>,greater<long long int>> pq; //最小堆
        queue<TreeNode*> q;
        q.push(root);
        while(!q.empty()) {
            int size = q.size();
            long long int level_sum = 0;
            while(size--) {
                TreeNode* current = q.front();
                q.pop();
                level_sum += current->val;
                if(current->left) q.push(current->left);
                if(current->right) q.push(current->right);
            }
            pq.push(level_sum);
            if(pq.size() > k) pq.pop();
        }
        if(pq.size()<k)return -1;
        return pq.top();
    }
};