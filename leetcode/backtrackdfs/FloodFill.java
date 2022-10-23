package backtrackdfs;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        /* 
         * 这是一个典型的DFS可以解决的问题 就叫floodfill算法
         * floodfill的特点应该是在于不需要visited 因为可以当场把原来的element改成新的
         * 不过 这道题还是有一个trick 如果新的color和旧的color一摸一样呢 这样就会死循环了
         * 所以还要加入一个检查 如果是和新的color相等就return 这样改过的cell不会再进 没改过的也不用再进 和原图是无区别的
         */
        int m = image.length, n = image[0].length;
        traverse(image, sr, sc, color, n, m,image[sr][sc]);
        return image;
    }
    private void traverse(int[][] image, int sr, int sc, int color, int n, int m, int ori) {
        if(sr < 0 || sr >= m || sc < 0 || sc >= n || image[sr][sc] != ori) return;
        if(image[sr][sc] == color) return;
        image[sr][sc] = color;
        traverse(image, sr + 1, sc, color, n, m, ori);
        traverse(image, sr - 1, sc, color, n, m, ori);
        traverse(image, sr, sc + 1, color, n, m, ori);
        traverse(image, sr, sc - 1, color, n, m, ori);
    }
}
