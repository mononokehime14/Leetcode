package dfs;

public class SurroundedRegions {
    /*
     * 报告！自己做出来的这道题 首先是考虑用flood fill 问题是这里 无法直接DFS flood fill 因为到了后面有可能连接边界从而实际上不用flip
     * 所以我的思路是先把边上的全部dfs淹掉 剩下的就肯定是要淹掉的 然后 再把边上的restore回来 所以这样其实就是对边上element的dfs 记录其路径
     * 草 但是看到了更快的解决方法 我们在淹掉边上的element的时候 可以不把它变成X 而变成* 这样不用额外的数组记录路径了 可以直接一会儿restore回来 妙哉妙哉
     */
    public void solve(char[][] board) {
        List<List<Pair<Integer,Integer>>> mem = new ArrayList<>();
        int m = board.length, n = board[0].length;
        for(int i = 0;i < m;i++) {
            if(board[i][0] == 'O'){
                List<Pair<Integer, Integer>> track = new ArrayList<>();
                dfs(board, track, i, 0, m, n);
                mem.add(track);
            }
            if(board[i][n-1] == 'O'){
                List<Pair<Integer, Integer>> track = new ArrayList<>();
                dfs(board, track, i, n-1, m, n);
                mem.add(track); 
            }  
        }
        for(int i = 0;i < n;i++) {
            if(board[0][i] == 'O'){
                List<Pair<Integer, Integer>> track = new ArrayList<>();
                dfs(board, track, 0, i, m, n);
                mem.add(track);
            }
            if(board[m-1][i] == 'O'){
                List<Pair<Integer, Integer>> track = new ArrayList<>();
                dfs(board, track, m-1, i, m, n);
                mem.add(track); 
            }  
        }
        for(int i = 1;i < m-1;i++) {
            for(int j = 1;j < n-1;j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }
        for(List<Pair<Integer, Integer>> track: mem) {
            for(Pair<Integer, Integer> x: track) {
                board[x.getKey()][x.getValue()] = 'O';
            }
        }
    }
    private void dfs(char[][] board, List<Pair<Integer, Integer>> track, int x, int y, int m, int n) {
        if(x < 0 || x >= m || y < 0 || y >= n || board[x][y] == 'X') return;
        board[x][y] = 'X';
        track.add(new Pair<>(x, y));
        dfs(board, track, x+1, y, m, n);
        dfs(board, track, x, y+1, m, n);
        dfs(board, track, x, y-1, m, n);
        dfs(board, track, x-1, y, m, n);
    } 
}
