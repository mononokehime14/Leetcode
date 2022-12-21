package array.prefixsumarray;

public class CarPooling {
    /* 1094
     * 差分数组模版题 没有什么好说的 这里我发现了差分数组的一个改动 就是如果能够用一个完整的数组存下
     * 比如这道题to的最大值也就是1000 那么可以直接用数组存 不然的话其实可以用PQ存 就像天际线问题一样
     * 差分数组的end要不要加1是要看题目的 这里就不用 
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // Queue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // for(int[] trip: trips) {
        //     q.offer(new int[]{trip[1], trip[0]});
        //     q.offer(new int[]{trip[2], -1 * trip[0]});
        // }
        int maxTo = 0;
        for(int[] i: trips) maxTo = Math.max(maxTo, i[2]);
        int[] marks = new int[maxTo+1];
        for(int[] i: trips) {
            marks[i[1]] += i[0];
            marks[i[2]] -= i[0];
        }
        int cap = 0;
        for(int i : marks) {
            cap += i;
            if(cap > capacity) return false;
        }
        return true;
    }
}
