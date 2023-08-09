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
/**
 * 83. Remove Duplicates from Sorted List
 * easy题, 不过一遍自然做出来还是挺有成就感, 这里沿用了快慢指针的思路
 * 一旦是duplicate, 可以让快指针继续, 将中间的全部跳过
 * 进一步的话, 其实可以不用两个指针, 只需要当前指针和当前指针的next就行了
*/
class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
        if(head == NULL) return head;
        ListNode* left = head;
        ListNode* right = head->next;
        while(right != NULL) {
            if(left->val == right->val) {
                left->next = right->next;
                right = right->next;
            }else{
                left = left->next;
                right = right->next;
            }
        }
        return head;
    }
};