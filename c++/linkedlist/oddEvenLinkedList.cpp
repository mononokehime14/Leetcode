#include <include/listnode.h>
using namespace std;
/**
 * 328. Odd Even Linked List
 * 很难说清用了什么方法 要注意的主要是答案的node在更新中会更改next
 * 因此一定要确保reference的node在那之前已经顺利的到了下一跳
*/
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* oddEvenList(ListNode* head) {
        if(!head) return head;
        ListNode *dummy = new ListNode(0), *cur=dummy;
        ListNode *dummy2 = new ListNode(0), *cur_odd=dummy2;
        ListNode *even = head, *odd = head->next;
        while(odd && even) {
            cur->next = even;
            even = even->next? even->next->next : nullptr;
            cur = cur->next;

            cur_odd->next = odd;
            odd = odd->next ? odd->next->next : nullptr;
            cur_odd = cur_odd->next;
        }
        cur_odd->next = nullptr;
        if(even) {
            cur->next = even;
            cur = cur->next;
        }
        cur->next = dummy2->next;
        return dummy->next;
    }
};