package array;

public class MinimumInsertionToBalanceAParenthesesString {
    /*
     * 1541
     * 这道题可以看作是进阶版本的minimum insertion 改动在于一个open bracket现在对应两个close bracket
     * 那么自然的 我们还是可以保持一个need 现在碰到一个open bracket加两个嘛 然后就是这道题的难点
     * 当need负数 也就是close bracket多的时候 我们怎么做insertion呢
     * 不能两个close bracket才判断 如果多一个 我们就必然要补上一个open了 这个时候把need清成1
     * 然后发现一个open bracket前面 close bracket的数量必然要是偶数 因为每一个open bracket需要对应连着两个close
     * 这样 我们就在发现其为奇数的时候补上一个close 
     * 这样 我们正好就处理了 前面把need清成1的隐患 要么后面一个close正确清零 要么后面一个open 然后检查到奇数 补上一个
     * 这一套真的是巧妙
     */
    public int minInsertions(String s) {
        int need = 0, output = 0;
        for(char c: s.toCharArray()) {
            if(c == '(') {
                need += 2;
                if(need % 2 == 1) {
                    output++; // need one )
                    need--;
                }
            }else{
                need--;
                if(need < 0) {
                    output++; // need one (
                    need = 1;
                }
            }
        }
        return output + need;
    }
}
