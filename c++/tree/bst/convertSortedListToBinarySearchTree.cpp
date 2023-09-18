#include <include/listnode.h>
/**
 * 109. Convert Sorted List to Binary Search Tree
 * 自己做出来哒, 有一些debug. 首先是找中点套用了模版 slow会停在靠左边的
 * 这样带来了一个问题 在只有两个nodes的时候slow会不动 对这个情况要特殊处理
 * 然后就是断连接 由于中点用于root 需要slow前一个来断
 * 
*/
class Solution {
public:
    TreeNode* sortedListToBST(ListNode* head) {
        if(!head) return nullptr;
        ListNode* slow = head, *fast = head, *slow_prev = new ListNode();
        while(fast->next && fast->next->next) {
            slow_prev = slow;
            slow = slow->next;
            fast = fast->next->next;
        }
        fast = slow->next;
        slow_prev->next = nullptr;
        TreeNode* root = new TreeNode(slow->val);
        root->left = slow == head ? nullptr : sortedListToBST(head);
        root->right = sortedListToBST(fast);
        return root;
    }
};