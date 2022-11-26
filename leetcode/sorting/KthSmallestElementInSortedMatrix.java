package sorting;

public class KthSmallestElementInSortedMatrix {
    /* 378
     * 牛！这道题我写到一半不自信去看了答案 但是发现我和labladong的答案一摸一样 一摸一样
     * 一样用的pq 连存的int[]里面的内容和顺序都一样
     * 这里用的不再是quick select 而是用的定长priority queue的方法 空间复杂度自然是O(N) 时间复杂度是O(NlogN)
     * 这里其实有一个疏漏没有解决 就是如果一行拿空了怎么办 现在拿空了之后queue size就会少掉1 如果k是N*N
     * 哦也不影响 因为停止条件是k用完 没有那一行的node之后 影响只是我们不会再管那一行而已
     */
    public int kthSmallest(int[][] matrix, int k) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = matrix.length;
        for(int i = 0;i < n;i++) pq.offer(new int[]{matrix[i][0], i, 0});
        int answer = -1;
        while(k > 0) {
            int[] current = pq.poll();
            answer = current[0];
            // System.out.println(answer);
            if(current[2]+1 < n) pq.offer(new int[]{matrix[current[1]][current[2]+1], current[1], current[2]+1});
            k--;
        }
        return answer;
    }
}
