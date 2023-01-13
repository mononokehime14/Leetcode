package greedy;

public class VideoStitiching {
    /* 1024
     * 果不其然 这道题是用了不同的interval greedy思路
     * 在之前求overlap相关的时候我们用的方法是按照end排序
     * 这道题目可以转化为 用最小的区间数量 覆盖制定的大区间 完全不在意是不是overlap
     * 我的思路是对的 细节没有细想 就是应该按照start排序 因为start前没有则就是无法覆盖到
     * 而后end要反向排序 因为我们要选择能够覆盖更多的一个区间
     * 在实现上 没有想清楚的一个点是 我们其实只需要看end就行了 当前的区间决定了一个end 在这个end前start的所有区间 找到end最大的
     * 记录这个新的end就可以了
     */
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (a, b) -> {
            if(a[0] == b[0]) return Integer.compare(b[1], a[1]);
            return Integer.compare(a[0], b[0]);
        });
        int curEnd = 0, n = clips.length, i = 0, count = 0;
        while(i < n && clips[i][0] <= curEnd) {
            int nextEnd = 0;
            while(i < n && clips[i][0] <= curEnd) {
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            curEnd = nextEnd;
            count++;
            if(curEnd >= time) return count;
        }
        return -1;
    }
}
