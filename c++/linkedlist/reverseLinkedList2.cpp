/**
 * 92. Reverse Linked List II
 * 还是debug出来了 思路是很简单的 先到left 再到right 然后进行相应的转换
 * 问题在于left是1right不是1的情况 原先我只用left=head left_prev=null 这样left_prev是无法对next赋值的
 * 如果left_prev写成一个dummy 这个问题会解决 但是return的时候 不能return head 因为已经改掉了
 * 所以还是额外的需要一个dummy 用dummy->next来保持list的head
*/
class Solution {
public:
    ListNode* reverseBetween(ListNode* head, int l, int r) {
        if(!head) return head;
        ListNode *left = head, *dummy = new ListNode(), *left_prev = dummy;
        left_prev->next = head;
        r -= l;
        if(!r) return head;
        while(l > 1) {
            left_prev = left;
            left = left->next;
            --l;
        }
        cout << "left " << left->val << endl;
        ListNode* current = left->next, *prev = left;
        while(r > 0) {
            ListNode* temp = current->next;
            current->next = prev;
            prev = current;
            current = temp;
            --r;
        }
        cout << "right " << prev->val << endl;
        left_prev->next = prev;
        left->next = current;
        return dummy->next;
    }
};