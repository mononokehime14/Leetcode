class uniquePaths{
    public int uniquePaths(int m, int n) {
        if(m == 1 || n == 1) return 1;
        // int[][] mem = new int[m][n];
        // for(int i = 0;i < m;i++){
        //     mem[i][0] = 1;
        // }
        // for(int i = 0;i < n;i++){
        //     mem[0][i] = 1;
        // }
        // for(int i = 1;i < m;i++){
        //     for(int j = 1;j < n;j++){
        //         mem[i][j] += (mem[i-1][j] + mem[i][j-1]);
        //     }
        // }
        // return mem[m-1][n-1];
        /* 实际上不用DP而可以用permutation 
        * permutations = (m+n)! / (m! * n!) 
        */
        m--; // steps = elements - 1
        n--;
        if(m < n) {              // Swap, so that m is the bigger number
            m = m + n;
            n = m - n;
            m = m - n;
        }
        long res = 1;
        int j = 1;
        for(int i = m+1; i <= m+n; i++, j++){       // Instead of taking factorial, keep on multiply & divide
            res *= i;
            res /= j;
        }
        return (int)res;
    }
}