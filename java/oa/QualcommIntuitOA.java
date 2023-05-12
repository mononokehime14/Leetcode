package oa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class QualcommIntuitOA {
    public static int[] countSentences(String[] wordSet, String[] sentences) {
        HashMap<String, Integer> mem = new HashMap<>();
        HashMap<String, String> anagram = new HashMap<>();
        for(String w: wordSet) {
            char[] temp = w.toCharArray();
            Arrays.sort(temp);
            String ws = temp.toString();
            mem.put(w, ws);
            mem.put(ws, mem.getOrDefault(ws, 0) + 1);
        }
        int[] answer = new int[sentences.length];
        for(int i = 0;i < sentences.length;i++) {
            int ans = 1; 
            for(String w: sentences[i].split(" ")) {
                ans *= mem.get(anagram.get(w));
            }
            answer[i] = ans;
        }
        return answer;
    }

    public static int disappear(int[][] grid, int row, int col) {
        // Pair不确定是不是能work 依稀记得java是没有自带pair structure的
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        Pair<Integer, Integer> root = new Pair<>{row, col};
        q.add(root);
        visited.add(root);
        int count = 1, m = grid.length, n = grid[0].length;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0;i < size;i++) {
                Pair<Integer, Integer> current = q.poll();
                if(visited.contains(current) || current.getKey() < 0 || current.getKey() >= m || current.getValue() > n || current.getValue() < 0) continue;
                if(grid[current.getKey()][current.getValue()] == grid[row][col]) {
                    count++;
                    visited.add(current);
                    q.add(new int[]{current.getKey() + 1, current.getValue()});
                    q.add(new int[]{current.getKey(), current.getValue() + 1});
                    q.add(new int[]{current.getKey() - 1, current.getValue()});
                    q.add(new int[]{current.getKey(), current.getValue() - 1});
                }
            }
        }
        return count;
    }
    boolean[][] visited;
    public static int[] find_exit(char[][] board, int row, int col) {
        /* 不要用DFS 这个DFS是用来练手的 请参考其他长老们的BFS解 */
        int min = Integer.MAX_VALUE, minRow = -1, minCol = -1, m = board.length, n = board[0].length;
        visited = new boolean[m][n];
        traverse(board, row, col, m, n, 0);
        return new int[]{minRow, minCol};
    }

    private void traverse(char[][] board, int row, int col, int m, int n, int count) {
        if(row == 0 || row == m - 1 || col == 0 || col == n - 1) {
            if(count < min){
                min = count;
                minRow = row;
                minCount = col
            }else if(count == min && minRow > row) {
                min = count;
                minRow = row;
                minCount = col;
            }
            return;
        }
        if(visited[row][col]) return;
        visited[row][col] = true;
        if(board[row + 1][col] == '0'){
            traverse(board, row + 1, col, m, n, ++count);
            count--;
        }
        if(board[row - 1][col] == '0'){
            traverse(board, row - 1, col, m, n, ++count);
            count--;
        }
        if(board[row][col + 1] == '0'){
            traverse(board, row, col + 1, m, n, ++count);
            count--;
        }
        if(board[row][col - 1] == '0'){
            traverse(board, row, col - 1, m, n, ++count);
            count--;
        }
    }
}
