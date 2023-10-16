/**
 * 445. Add Two Numbers II
 * 由于这里的顺序是most significant -> least, 所以我用了笨办法, reverse两个list, 模拟加法, 然后reverse回来
 * 这里可以看到dummy的使用, return 最初的head的时候可以保持dummy, 像reverse这种就不用, 直接当前的prev就行
 * 这道题C++书上使用stack推进去(短的后面推0) 时间复杂度是一样的 但是会稍微高级一些
*/
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        int carrier = 0;
        ListNode* dummy = new ListNode(), *prev = dummy;
        while(l1 && l2) {
            int tmp = l1->val + l2->val + carrier;
            carrier = tmp > 9 ? 1 : 0;
            tmp %= 10;
            ListNode *current = new ListNode(tmp);
            // cout << l1->val << " + " << l2->val << " " << tmp << endl;
            prev->next = current;
            prev = current;
            l1 = l1->next;
            l2 = l2->next;
        } 
        while(l1) {
            int tmp = l1->val + carrier;
            carrier = tmp > 9 ? 1 : 0;
            tmp %= 10;
            ListNode *current = new ListNode(tmp);
            prev->next = current;
            prev = current;
            l1 = l1->next;
        }
        while(l2) {
            int tmp = l2->val + carrier;
            carrier = tmp > 9 ? 1 : 0;
            tmp %= 10;
            ListNode *current = new ListNode(tmp);
            prev->next = current;
            prev = current;
            l2 = l2->next;
        }
        if(carrier) {
            ListNode *current = new ListNode(1);
            prev->next = current;
            prev = current;
        }
        return reverse(dummy->next);
    }
    ListNode* reverse(ListNode* root) {
        ListNode *prev = nullptr, *next;
        while(root) {
            next = root->next;
            root->next = prev;
            prev = root;
            root = next;
        }
        return prev;
    }
};