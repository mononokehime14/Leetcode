/**
 * 203. Remove Linked List Elements
 * 哦我的上帝 精妙的代码
 * 首先head可能被删除 那么我们保持一个dummy
 * 其次删除的情况下prev不动 就是这样我的老伙计
*/
class Solution {
public:
    ListNode* removeElements(ListNode* head, int val) {
        ListNode* dummy = new ListNode(-1), *prev = dummy;
        dummy->next = head;
        while(head) {
            if(head->val == val) {
                prev->next = head->next;
                head = head->next;
            }else{
                prev = head;
                head = head->next;
            }
        }
        return dummy->next;
    }
};