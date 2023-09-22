/**
 * 61. Rotate List
 * 也是自己做的 达到100%速度
 * 这里用的linkedlist一个比较经典的思路 快慢指针 但是意思是快指针先跑一会儿
 * 然后一起同步跑 这个思路在我们想要从后往前数第k个element的很有用 因为快指针先跑k的情况下
 * 慢指针最后会来到n-k 快指针会来到队尾 这对解题很有优势
 * 这里还要算list总长 因为k可能很大
*/
class Solution {
public:
    ListNode* rotateRight(ListNode* head, int k) {
        if(!head) return head;
        ListNode *slow = head, *fast = head;
        int list_size = 0;
        while(fast) {
            fast = fast->next;
            ++list_size;
        }
        int steps = k % list_size;
        if (!steps) return head;
        fast = head;
        while(steps > 0) {
            fast = fast->next;
            --steps;
        }
        while(fast->next) {
            fast = fast->next;
            slow = slow->next;
        }
        // cout << slow->val << " " << fast->val << endl;
        ListNode* new_head = slow->next;
        fast->next = head;
        slow->next = NULL;
        return new_head;
    }
};