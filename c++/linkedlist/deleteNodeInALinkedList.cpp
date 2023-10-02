/**
 * 237. Delete Node in a Linked List
 * mind blown. 我觉得这是不可能完成的任务啊, 没有办法得到prev
 * 事实上答案是直接对地址上存的值动手, 把当前的node换成下一个, prev还是跳到当前的地址
 * 只不过值换掉
*/
class Solution {
public:
    void deleteNode(ListNode* node) {
        *node = *node->next;
    }
};