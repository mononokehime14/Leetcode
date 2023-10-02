/**
 * 147. Insertion Sort List
 * 是对insertion的模拟 这里的思路的关键就在于要保证一个dummy prev
 * 由于single linkedlist无法从当前回到prev 那么insert只能是prev和current中间操作
 * 这样要实现当前的node变到最前面 只能在前面保持一个dummy node作为prev
*/
class Solution {
public:
    ListNode* insertionSortList(ListNode* head) {
        if(!head || !head->next) return head;
        ListNode* dummy = new ListNode(-5001), *current = head, *prev = dummy;
        dummy->next = head;
        while(current) {
            if(current->val < prev->val){
                prev->next = current->next;
                ListNode* prev_c = dummy, *head_c = prev_c->next;
                while(head_c) {
                    if(current->val >= prev_c->val && current->val <= head_c->val) {
                        prev_c->next = current;
                        current->next = head_c;
                        // cout << current->val << " -> " << prev_c->val << " , " << head_c->val << endl;
                        break;
                    }else{
                        prev_c = head_c;
                        head_c = head_c->next;
                    }
                }
                current = prev->next;
            }else{
                prev = current;
                current = current->next;
            }
        }
        return dummy->next;
    }
};