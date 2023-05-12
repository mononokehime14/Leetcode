package linkedlist;

public class IntersectionOfTwoLinkedList {
    /* 这道题快慢指针是不行的 要用双指针 双指针的一个问题就是两边不一定是一样长 intersection point
     * 的位置不一定一样 这样的话双指针有可能遇不到
     * 有一个很机智的解决方法 就是将B拼接到A后面 将A拼接到B后面 这样两个指针必然在第二个部分的intersection相遇
     * 我有一个证明 A size M, B size N, intersection at K1 in A, K2 in B
     * second intersection point in A+B: M + K2, second intersection point in B+A: N + K1
     * 基于一个给定的特点 intersection后面 两边就都是一样的 也就是说 M - K1 = N - K2 那么也就是M + K2 = N + K1
     * 注意一个细节 先拼接有一点麻烦 因为互相拼接容易造成环 我是在loop里判断 然后直接更改node改成另一边的head
     * 
     * 原来的方法写的不是很好 这里可以利用两边一定会碰头的特点 将循环停止条件设置为ha != hb 当ha或者hb变成null的时候更改成
     * 另一边的head loop完变成都变成null停止循环 return null
     * 如果两个list同等长度那不是等不到拼接就return了吗 没问题 因为一样长度的list这一轮没有intersection也就必然没有了
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // ListNode ha = headA, hb = headB;
        // boolean af = false, bf = false;
        // while(ha != null) {
        //     if(ha == hb) return ha;
        //     ha = ha.next;
        //     hb = hb.next;
        //     if(ha == null && !af) {
        //         af = true;
        //         ha = headB;
        //     }
        //     if(hb == null && !bf) {
        //         bf = true;
        //         hb = headA;
        //     }
        // }
        // return null;
        ListNode ha = headA, hb = headB;
        while(ha != hb){
            if(ha == null) {
                ha = headB;
            }else{
                ha = ha.next;
            }
            if(hb == null) {
                hb = headA;
            }else{
                hb = hb.next;
            }
        }
        return ha;
    }
}
