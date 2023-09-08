#include <include/listnode.h>
/**
 * 基本思路是用快慢指针找到中点 然后merge sort
 * merge的代码和merge sort list的是一样的 cur = cur->next这一步要等到a = a->next 不然会被覆盖掉
 * 另外快慢指针可以用这套模版 两者都是从head开始 经过推算slow在中点 偶数list会在左边中点 
*/
class Solution {
public:
    ListNode* sortList(ListNode* head) {
        return mergeSort(head);
    }
    ListNode* mergeSort(ListNode* head) {
        if(!head || !head->next) return head;
        ListNode *slow = head, *fast = head;
        while(fast->next && fast->next->next) {
            slow = slow->next;
            fast = fast->next->next;
        }
        fast = slow->next;
        slow->next = nullptr;
        return merge(mergeSort(head), mergeSort(fast));
    }

    ListNode* merge(ListNode* a, ListNode* b) {
        ListNode *dummy = new ListNode(0), *cur = dummy;
        while(a && b) {
            if(a->val <= b->val) {
                cur->next = a;
                a = a->next;
                cur = cur->next;
            }else{
                cur->next = b;
                b = b->next;
                cur = cur->next;
            }
        }
        if(a) cur->next = a;
        else if(b) cur->next = b;
        return dummy->next;
    }
};