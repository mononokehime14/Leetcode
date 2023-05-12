package array.prefixsumarray;

public class IncrementSubmatricesByOne {
    /* 2536
     * 2d矩阵版本的差分数组 成列的+1和-1 如果纯在2d matrix上操作有点麻烦 主要是-1如果超出列的限制 不知道要不要
     * 延续放到下一行里 所以干脆把2d矩阵展平操作
     */
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[] matrix = new int[n * n];
        for(int[] i : queries) {
            for(int x = i[0];x <= i[2];x++) {
                int start = x * n + i[1];
                int end = x * n + i[3] + 1;
                matrix[start]++;
                if(end < n * n) matrix[end]--;
            }
        }
        int[][] answer = new int[n][n];
        int current = 0;
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                current += matrix[i * n + j];
                answer[i][j] = current;
            }
        }
        return answer;
    }
}
