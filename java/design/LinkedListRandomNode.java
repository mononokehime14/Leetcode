package design;

public class LinkedListRandomNode {
    /* 382
     * 最开始用了非常朴素的方法 保持一个list getRandom就是随机挑选一个index就可以了
     * 这个方法应该是最快速的 但是代价是要保持了一个list 如果太大怎么办
     * 有一个不用额外的空间的方法 对于第i个的node 保证其被选择的概率是1/i 保持原来选择的概率是1 - 1/i
     * 这样第i+1个node不replace我们选择的第i个node的可能性是1 - 1/(i+1) 以此类推
     * 这样我们选择第i个node的可能性就是全部乘起来 可以得到1/n 也就是我们想要保证的随机性
     * 至于在实现中 我们只能保留最后一个中了1/i可能性的
     * 这种方法叫做水塘抽样算法Reservoir sampling 专门解决如何在长度未知的序列（数据流）中随机选择一个元素的数学技巧
     */
    // private List<ListNode> arr;
    private Random rand = new Random();
    private ListNode head;
    public Solution(ListNode head) {
        // arr = new ArrayList<>();
        // ListNode t = head;
        // while(t != null) {
        //     arr.add(t);
        //     t = t.next;
        // }
        this.head = head;
    }
    
    public int getRandom() {
        // return arr.get(rand.nextInt(arr.size())).val;
        ListNode t = head;
        int i = 0, res = 0;
        while(t != null) {
            i++;
            if(rand.nextInt(i) == 0) { // [0, i), 1/i probablity
                res = t.val;
            }
            t = t.next;
        }
        return res;
    }
}
