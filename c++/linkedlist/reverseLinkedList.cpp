#include <include/listnode.h>
/**
 * 206. Reverse Linked List
 * C++版本 思路非常简单 细节上牢记有四步 并且利用递归特质 不要设想在一个节点上
 * 翻转完自己、前者和后者, 每次只动自己的next, 剩下的不过是为这个目的服务罢了
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
    ListNode* reverseList(ListNode* head) {
        ListNode* prev = nullptr, *next;
        while(head) {
            next = head->next;
            head->next = prev;
            prev = head;
            head = next;
        }
        return prev; // head would be null
    }
};