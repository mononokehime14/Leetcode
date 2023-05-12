package array.prefixsumarray;

public class TheSkylineProblem {
    /* 218
     * 非常难的一道题 难在用了许多小技巧解决很多问题 
     * 首先可以思考这道题如果没有时间和空间的限制 那么我们就能单纯的存一个大数组 每个存最高的height就行了 但是不能够这样做
     * 所以下一步 由于一个range内height一样 自然想到了差分数组 那我们可以存一个left height和一个right -height
     * 然后就是重要的一步 和后面的所有技巧是搭配起来用的 我一开始用了treemap 这样每次存当前横轴的最大值 比如(4, 5)(4, 8) -> (4, 8)
     * (4, -8)(4, 5) -> (4, 5) 但是事实证明这么存 信息是不够的 比如(3,7,15)(5,12,12) 在7的位置15结束了 但是我们找不到12了 因为之前就被覆盖了
     * 所以这里不能只存一个 而是要全存 用list 然后sort
     * 那么下一个重要的细节就来了 第二个element要反向排序 这是因为比如(0, 2, 3)(2, 5, 3)如果前面的 -height在前 那么我们就会得到一个height0 并加入到output里
     * 但是反向过来 保证3先加入 和prev无区别 然后3去掉一个 我们还剩下一个3 正确性子啊次不受影响
     * 那么剩下一个重点就是 普通的差分数组是用一个height int推的 但是这里就不行了 height如果变低了 也许前面有一个更低一点的block还得要继续 所以得用PQ最大堆 不断的记录height
     * 然后遇到-height的 就把相应的height删掉
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // TreeMap<Integer, Integer> mem = new TreeMap<>();
        List<int[]> mem = new ArrayList<>();
        for(int[] i: buildings) {
            mem.add(new int[]{i[0], i[2]});
            mem.add(new int[]{i[1], -1 * i[2]});
            // int leftMax = mem.getOrDefault(i[0], Integer.MIN_VALUE);
            // if(i[2] > leftMax) mem.put(i[0], i[2]);
            // int rightMax = mem.getOrDefault(i[1] + 1, Integer.MAX_VALUE);
            // if(-1 * i[2] < rightMax) mem.put(i[1] + 1, -1 * i[2]);
        }
        Collections.sort(mem, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0]; // acsending
            return b[1] - a[1]; // acsending
        });
        Queue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
        q.offer(0);
        int prev = 0;
        LinkedList<List<Integer>> output = new LinkedList<>();
        for(int[] i: mem) {
            if(i[1] < 0) {
                q.remove(-1 * i[1]); // PQ的remove是O(N)的 所以这里倒是可以用TreeMap(如下) 但其实是用额外的空间换的
            }else{
                q.offer(i[1]);
            }
            int current = q.peek();
            if(prev != current) {
                output.add(Arrays.asList(i[0], current));
                prev = current;
            }
        }
        // TreeMap<Integer, Integer> t = new TreeMap<>();
        // t.put(0, 1);
        // int prev = 0;
        // LinkedList<List<Integer>> output = new LinkedList<>();
        // for(int[] i: mem) {
        //     if(i[1] < 0) {
        //         if(t.get(-1 * i[1]) - 1 <= 0) t.remove(-1 * i[1]);
        //         else t.put(-1 * i[1], t.get(-1 * i[1]) - 1);
        //     }else{
        //         t.put(i[1], t.getOrDefault(i[1], 0) + 1);
        //     }
        //     int current = t.lastKey();
        //     if(prev != current) {
        //         output.add(Arrays.asList(i[0], current));
        //         prev = current;
        //     }
        // }
        return output;
    } 
}
