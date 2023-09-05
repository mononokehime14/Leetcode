using namespace std;

/**
 * 23. Merge k Sorted Lists
 * C++的PQ需要基于一个sequence container实现 也就是constructor里的第二个参数
 * 这里用vector实现的原因是PQ是一个平衡二叉树(并不能算完全 最后一层可能不完全的) 特殊的是父节点一定比子节点大
 * 这样对于在i的节点子节点的位置一定在2i+1和2i+2, 父节点一定在(i-1)/2
 * PQ有两种操作 上浮和下沉 上浮就是和父节点比较然后swap 下沉同理(如果下沉选择子节点中较大的swap)
 * insert就把值先加到最后(size+1) 然后上浮 同理pop就把top换到最后 然后最新的top执行下沉
 * 
 * 这道题用了c++的PQ, 我们想要最小堆 这里的comparator看起来比较反直觉 这个比较不是说a比b大放在前面 难道是降序
 * 事实上这可能C++ PQ实现的独特 PQ默认是最大堆 默认的比较a < b 因此要改成最小堆就要吧comparator反过来
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
    struct comp{
        bool operator()(ListNode* a, ListNode* b) {
            return a->val > b->val;
        }
    };
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if(lists.empty()) return nullptr;
        priority_queue<ListNode*, vector<ListNode*>, comp> pq;
        for(ListNode* l : lists) {
            if(l) pq.push(l);
        }
        ListNode* dummy = new ListNode(), *cur = dummy;
        while(!pq.empty()) {
            cur->next = pq.top();
            pq.pop();
            cur = cur->next;
            if(cur->next) pq.push(cur->next);
        }
        return dummy->next;
    }
};