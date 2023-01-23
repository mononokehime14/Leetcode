package math;

public class ApplyBitwiseOperationsToMakeStringsEqual {
    /* 2546
     * 差一点点 这道题我的思考是要分类讨论：
     * 0 0 -> 0 0, 1 0或者0 1 -> 1 1, 1 1 -> 1 0或者0 1
     * 也就是说当我们有至少一个1的时候 我们能把0变成1 如果我们有至少一个额外的1 就能把1个1变成0
     * 据此 我写了一个遍历 动态更新1的数量 但是在0010000 0001000这个testcase的时候就不对了
     * 10可以不可以变成00 那个bit就消不掉了按照我的逻辑 但是实际上 可以变成11 然后变成01 所以不可以一个bit一个bit推算
     * 实际上 还可以进一步总结 能够发现随便一个数字只要有1 就能互相变 有多个1的可以消除 1的位置不对可以通过上面testcase的方法变动
     * 只有一种情况不行 就是没有任何1 因为1的最小数量是1 如果一个1也没有 什么都不能变 所以要么s和t都无1 要么都有1
     */
    public boolean makeStringsEqual(String s, String target) {
        return s.contains("1") == target.contains("1");
        // int n = s.length(), count1 = 0;
        // for(int i = 0;i < n;i++) {
        //     if(s.charAt(i) == '1') count1++;
        // }
        // for(int i = 0;i < n;i++) {
        //     char c1 = s.charAt(i);
        //     char c2 = target.charAt(i);
        //     if(c1 == c2) continue;
        //     if(c1 == '1') {
        //         if(count1 <= 1) return false;
        //         count1--;
        //     }else{
        //         if(count1 < 1) return false;
        //         count1++;
        //     }
        // }
        // return true;
    }
}
