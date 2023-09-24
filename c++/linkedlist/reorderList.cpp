/**
 * 143. Reorder List
 * 思路是找到中点 然后翻转后半部分 然后合并
 * 一次写过了 说明链表找中点 链表翻转的代码是非常有适用性的 很合格的模版
*/
class Solution {
public:
    void reorderList(ListNode* head) {
        // 找中点
        if(!head || !head->next) return;
        ListNode *slow = head, *fast = head;
        while(fast->next && fast->next->next) {
            slow = slow->next;
            fast = fast->next->next;
        }
        ListNode *current = slow->next, *prev = nullptr, *temp = nullptr, *temp2 = nullptr;
        slow->next = nullptr; //断生
        while(current) { //后半部分翻转
            temp = current->next;
            current->next = prev;
            prev = current;
            current = temp;
        }
        slow = head;
        fast = prev; //后半部分的head
        while(fast && slow) {
            temp = slow->next;
            temp2 = fast->next;
            slow->next = fast;
            fast->next = temp;
            slow = temp;
            fast = temp2;
        }
    }
};