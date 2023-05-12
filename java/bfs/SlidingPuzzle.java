package bfs;

public class SlidingPuzzle {
    /* 这道题目的代码非常长 但是实际上不过是因为在做选择的时候 需要做char交换
     * 同时给的是int[][] 我们因为要用visited 所以需要转换成string的原因
     * 实际上的思路 无非就是BFS 一模一样 重点在于想到这里要用BFS
     * 回溯也可以 停止条件就是找到正确解 加上碰到visited pattern 但是题目问的不是所有解法
     * 而是最快解法 所以要用到BFS最短路径的特性
     */
    public int slidingPuzzle(int[][] board) {
        Queue<String> q = new LinkedList<>();
        Set<String> mem = new HashSet<>();
        String start = array2String(board);
        String target = "123450";
        q.add(start);
        mem.add(start);
        int step = 0;
        int[][] adjs = new int[][]{ //注意这里的细节 不然就需要一个很长的switch 别问为什么我知道orz
            {1,3},
            {2,0,4},
            {1,5},
            {0,4},
            {1,3,5},
            {2,4}
        };
        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i = 0;i < sz;i++) {
                String current = q.poll();
                if(current.equals(target)) return step;
                mem.add(current);
                int index = current.indexOf("0");
                String ns;
                for(int adj: adjs[index]){
                    ns = swap(current.toCharArray(), index, adj);
                    if(!mem.contains(ns)) {
                        q.add(ns);
                        mem.add(ns);
                    }
                }
            }
            step++;
        }
        return -1;
    }
    private String swap(char[] cs, int x, int y) {
        char temp = cs[x];
        cs[x] = cs[y];
        cs[y] = temp;
        return new String(cs);
    }
    private String array2String(int[][] board) {
        StringBuilder output = new StringBuilder();
        for(int i = 0;i < 2;i++) {
            for(int j = 0;j < 3;j++) {
                output.append(String.valueOf(board[i][j]));
            }
        }
        return output.toString();
    }
}
