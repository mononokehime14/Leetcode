public class DecodeWays {
    public int numDecodings(String s) {
        /* 91
         * 思路也太好想了 唯一的困难之处是细节上的判断 首先是base case只能是将0和1位的数字判断一下
         * 不能在前面空置一个0 因为后面的number不是前面的+1 而是=前面的 number的增加来自两种可能相加
         * 另一个重要的地方是用两位数字的时候 判断方法完全不同(最后一位能不能为0 也就是10算不算) 且一开头和二开头不一样(19, 29)
         */
        char[] sc = s.toCharArray();
        int n = sc.length;
        int[] dp = new int[n];
        if(sc[0] > '0' && sc[0] <= '9') dp[0] = 1;
        if(n == 1) return dp[0];
        if(sc[1] > '0' && sc[1] <= '9') dp[1] = dp[0];
        if((sc[0] == '1' && sc[1] >= '0' && sc[1] <= '9')
               || (sc[0] == '2' && sc[1] >= '0' && sc[1] <= '6')) dp[1]++;
        for(int i = 2;i < n;i++) {
            int one = 0;
            if(sc[i] > '0' && sc[i] <= '9') one = dp[i-1];
            int two = 0;
            if((sc[i-1] == '1' && sc[i] >= '0' && sc[i] <= '9')
               || (sc[i-1] == '2' && sc[i] >= '0' && sc[i] <= '6')) {
                two = dp[i-2];
            }
            dp[i] = one + two;
        }
        // for(int d : dp) System.out.print(d + " ");
        // System.out.println();
        return dp[n-1];
    }
}
