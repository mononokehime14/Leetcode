package backtrackdfs;
public class WordSearch { 
    public boolean exist(char[][] board, String word) {
        char[] worda = word.toCharArray();
        int h = board.length;
        int w = board[0].length;
        int[][] visited = new int[h][w];
        for(int i = 0;i < h;i++){
            for(int j = 0;j < w;j++){
                visited[i][j] = 0;
            }
        }
        for(int i = 0;i < h;i++){
            for(int j = 0;j < w;j++){
                if(generate(worda,board,visited,i,j,h,w,0)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean generate(char[] worda, char[][] board, int[][] visited, int i, int j, int h, int w, int pos){
        if(i >= h || j >= w || i < 0 || j < 0 || visited[i][j] == 0 || board[i][j] != worda[pos]){
            return false;
        }
        visited[i][j] = 1;
        if(generate(worda, board, visited, i+1, j, h, w, pos+1) || 
        generate(worda, board, visited, i, j+1, h, w, pos+1) ||
        generate(worda, board, visited, i-1, j, h, w, pos+1) ||
        generate(worda, board, visited, i, j-1, h, w, pos+1)){
            visited[i][j] = 0;
            return true;
        }
        visited[i][j] = 0;
        return false;
    }
}
