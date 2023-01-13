package math;

public class StoneGame {
    /* 877
     * 我仍然想要从dp来推 然而这道题即便从base case 也就是只有1 2 3堆的时候往上推 也不大好发现怎么做
     * 实际上 这道题的条件决定了先手必胜 这首先是因为堆的数量是偶数 这意味着我们可以控制拿一套偶数index组或者一套奇数index组
     * 比如2 1 9 5 index 1 2 3 4 如果我想要偶数组 我就先拿index 4 这样对手剩下1 3 无论取什么我下一轮都可以拿2 这样我就去到了index全部是偶数的group
     * 同理我也可以拿到index全奇数的group 
     * 而这个时候题目的第二个条件是没有平局 和是奇数 也就是偶数组和奇数组必然有一个更大 所以先手者可以直接选择哪个组
     */
    public boolean stoneGame(int[] piles) {
        return true;
    }
}
