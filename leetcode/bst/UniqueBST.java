package bst;

public class UniqueBST {
    /* 96
     * 这道题一开始的思路就是以每一个数字为root 其可能存在的BST数量就是分而治之 左边 * 右边的数量
     * 因为有重复出现的情况 我们可以用动态规划 然后这里的话循环的动规不是特别直观 所以我们还是选择了mem + 递归的
    */
    int[][] mem;
    public int numTrees(int n) {
        mem = new int[n+1][n+1];
        return find(1, n);
    }
    private int find(int left, int right) {
        if(left >= right) return 1;
        if(mem[left][right] != 0) return mem[left][right];
        int sum = 0;
        for(int i = left;i <= right;i++) {
            sum += find(left, i-1) * find(i+1, right);
        }
        mem[left][right] = sum;
        return sum;
    }
}
