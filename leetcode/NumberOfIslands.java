package leetcode;

import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class NumberOfIslands {
    //这道题用BFS结果仍然正确，但是BFS与Queue似乎会超时。另外，不需要另外维持一个visited 2d array
    //grid本身可以替代visited的功能，查过的可以直接改成‘0’，下次同样不会再看。
    public int numIslands(char[][] grid) {
        int count = 0;
        int width = grid[0].length;
        int depth = grid.length;
        if(depth == 0){
            return 0;
        }
        for(int i = 0;i < depth;i++){
            for (int j = 0;j < width;j++){
                if(grid[i][j] == '0'){
                    continue;
                }
                DFS(i,j,depth,width,grid);
                count++;
            }
        }
        return count;
    }
    private void DFS(int x, int y, int depth, int width, char[][] grid){
        if(x>=depth || y>=width || x < 0 || y < 0 || grid[x][y] != '1'){
            return;
        }
        grid[x][y] = '0';
        DFS(x-1,y,depth,width,grid);
        DFS(x,y-1,depth,width,grid);
        DFS(x+1,y,depth,width,grid);
        DFS(x,y+1,depth,width,grid);
    }
}
