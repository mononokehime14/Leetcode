package trie;

public class WordSearch2 {
    /* 212
     * 最一开始容易想到的是回溯 和word search 1一样 通过遍历每一个board中的element 每个element出发回溯 检查是否能够match
     * 时间复杂度应该是 M * (4 ** K) M是board size K是word的长度
     * 这道题的不同之处在于有很多word要检查 这样的话 每一个word重复上述操作时间成本就是 N * M * (4 ** K)
     * 一开始想的思路是备忘录 把求过的word和结果存起来 但是这种粗陋的剪枝只能处理多个一样的word的test case
     * 和检查prime divisor multipliers等题目是一个思路 在这种情况下 我们必须考虑怎么消弭每一个word重复计算的浪费
     * 之前那些题目是将全部答案先算好存在数据结构里 这里则是可以把所有的word提前算好到前缀树里 然后一遍遍历board 就能算出所有的结果
     */
    private TreeNode root = new TreeNode();
    public List<String> findWords(char[][] board, String[] words) {
        for(String w: words) {
            TreeNode temp = root;
            for(int i = 0;i < w.length();i++) {
                int idx = w.charAt(i) - 'a';
                if(temp.children[idx] == null) {
                    temp.children[idx] = new TreeNode();
                }
                temp = temp.children[idx];
            }
            temp.word = w; // word存在最后一个字母的treenode里
        }
        
        Set<String> output = new HashSet<>();
        int m = board.length, n = board[0].length;
        for(int i = 0;i < m;i++) { // 遍历board
            for(int j = 0;j < n;j++) {
                TreeNode temp = root; // 每次都要从root开始
                backtrack(output, temp, board, i, j, m, n);
            }
        }
        return new ArrayList<>(output);
    }
    private void backtrack(Set<String> output, TreeNode root, char[][] board, int x, int y, int m, int n) {
        if(x < 0 || x >= m || y < 0 || y >= n || board[x][y] == '*') return; // 这里用改* 代替了track/visited数组
        char c = board[x][y];
        if(root.children[c - 'a'] == null) return;
        root = root.children[c - 'a'];
        if(root.word != null) output.add(root.word); // 存在一个路径 能够match某一个word 改word加入output
        board[x][y] = '*';
        backtrack(output, root, board, x + 1, y, m, n);
        backtrack(output, root, board, x - 1, y, m, n);
        backtrack(output, root, board, x, y + 1, m, n);
        backtrack(output, root, board, x, y - 1, m, n);
        board[x][y] = c;
    }
    // public List<String> findWords(char[][] board, String[] words) {
    //     List<String> output = new ArrayList<>();
    //     int m = board.length, n = board[0].length;
    //     HashMap<String, Boolean> mem = new HashMap<>();
    //     for(String w: words) {
    //         if(mem.containsKey(w)) {
    //             if(mem.get(w)) output.add(w);
    //         }else if(exist(board, w, m, n, mem)) {
    //             output.add(w);
    //         }
    //     }
    //     return output;
    // }
    // private boolean exist(char[][] board, String word, int m, int n, HashMap<String, Boolean> mem) {
    //     boolean[][] track = new boolean[m][n];
    //     for(int i = 0;i < m;i++) {
    //         for(int j = 0;j < n;j++) {
    //             if(dfs(board, track, word, i, j, m, n, 0)) {
    //                 mem.put(word, true);
    //                 return true;
    //             }
    //         }
    //     }
    //     mem.put(word, false);
    //     return false;
    // }
    // private boolean dfs(char[][] board, boolean[][] track, String word, int i, int j, int m, int n, int index) {
    //     if(index >= word.length()) return true;
    //     if(i < 0 || i >= m || j < 0 || j >= n || track[i][j]) return false;
    //     if(word.charAt(index) != board[i][j]) return false;
    //     track[i][j] = true;
    //     boolean up = dfs(board,track, word, i - 1, j, m, n, index+1);
    //     boolean down = dfs(board, track, word, i + 1, j, m, n, index+1);
    //     boolean left = dfs(board, track, word, i, j - 1, m, n, index+1);
    //     boolean right = dfs(board, track, word, i, j + 1, m, n, index+1);
    //     track[i][j] = false;
    //     return up || down || left || right;
    // }
}
class TreeNode {
    public String word;
    public TreeNode[] children = new TreeNode[26];
}
