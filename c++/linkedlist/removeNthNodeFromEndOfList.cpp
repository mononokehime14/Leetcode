#include <include/listnode.h>
/**
 * 19. Remove Nth Node From End of List
 * Arrive at the same solution as Java version
 * 这里并不实际上是快慢指针 而是有一个指针先跑n步 然后一起跑 第二个指针就停在了我们想要删除的地方
 * 这里需要一些细致的推算 为什么一定要从dummy开始是因为如果我们要删除第一个element 那么无论如何没法从前面删掉head
 * 只能直接return 但是这样的方法会在后面的testcase遇到问题 所以还是dummy起步 first多跑一步就行了
*/
class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ListNode *first = new ListNode(0), *second = new ListNode(0), *dummy=second;
        first->next = head;
        second->next = head;
        for(int i = 0;i < n+1;i++) first = first->next;
        // if(!first) return nullptr;
        while(first != nullptr) {
            first = first->next;
            second = second->next;
        }
        // cout << second->val << endl;
        second->next = second->next->next;
        return dummy->next;
    }
};