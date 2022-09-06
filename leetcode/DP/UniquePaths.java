public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] mem = new int[m][n];
        if(m > 1) mem[1][0] = 1;
        if(n > 1) mem[0][1] = 1;
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(i > 0){
                    mem[i][j] += mem[i-1][j];
                }
                if(j > 0){
                    mem[i][j] += mem[i][j-1];
                }
            }
        }
        mem[0][0] = 1; //this is required because testcase 1,1 should give 1...
        return mem[m-1][n-1];
        /*
         * 以上做法是正宗DP但是是愚蠢的，因为可以直接用数学
         * 利用题目必然从左上出发到右下，每次右或者下，则必然用m - 1 + n - 1步。
         * 下和右内部的顺序没有分别，所以是一个Combination问题
         * C(n, k) = n! / (n - k)! k!
         * 可以直接带入，得(m + n)!/m! * n!
         */
    }
}
