#include "../../include/listnode.h"

/**
 * 142 Linked List Cycle II
 * 对于链表找环路的问题，有一个通用的解法——快慢指针(Floyd 判圈法)。
 * 给定两个指针， 分别命名为 slow和fast，起始位置在链表的开头。
 * 每次fast前进两步，slow前进一步。如果fast可以走到尽头，那么说明没有环路;
 * 如果fast可以无限走下去，那么说明一定有环路，且一定存 在一个时刻slow和fast相遇,这一步的数学证明我不知道.
 * 当slow和fast第一次相遇时，我们将fast重新移动到链表开头，并让slow和fast每次都前进一步。
 * 当slow和fast第二次相遇时，相遇的节点即为环路的开始点。
 * 这一部分是可以证明的 fast比slow多走一倍, 调整前fast走了2d, slow走了1d, 调整后同走k步
 * 这样就看出一个是2d+k一个是1d+k,但是处在同一个位置. 那么已知有环的情况下, 这个环就肯定是k+d, k+2d, k+3d这样下去
*/
class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
        ListNode *slow = head, *fast = head;
        do {
            if(!fast || !fast->next) return nullptr;
            fast = fast->next->next;
            slow=slow->next;
        }while(fast != slow);
        // if we enter here, then there is a loop
        fast = head;
        while(fast != slow) {
            fast = fast->next;
            slow = slow->next;
        }
        return fast;
    }
};