package design;

public class ExamRoom {
    /* 855
     * 这道题的方向是想对了的 就是要寻找最大间距 然后坐在中间
     * 但是在实现上出现了问题 我用了PriorityQueue 这在seat的没有发生问题 但是在leave的 我要把左右两边两个小的从pq中移掉
     * 这个时候Priority Queue就无法承担这个工作 因为只能头尾操作 所以要用TreeSet TreeSet底层是用红黑树实现的 可以logN的移除
     * 然后对于一开始的1 2个学生 我是打算特殊处理的 这让逻辑变得很奇怪 这里可以像双链表的dummy head tail一样 加入假想的头尾椅子
     * 这样 interval的start和end就是不能坐的椅子 间距就是end - start + 1
     * 选择seat的时候两个特殊的case start是-1可以直接选择第一个 或者end N可以直接选择最后一个 剩下的就坐在中间
     * 然后还有一个细节 就是怎么和局中保证选择index最低的 正常来讲 我们pq的比较顺序可以解决 但是这里有一个特殊的case
     * [0, 4]和[4, 9]算出来的间距是后者大 但是实际坐上去的时候 离最close的一个学生的距离是一样的 都是1 这是因为坐中间的时候除以2 java round down的原因
     * 所以这里就应该要选前者 故此interval的间距不能是end - start + 1 而要算成如果坐上去之后离最close的学生的距离
     */
    private TreeSet<int[]> pq;
    private HashMap<Integer, int[]> startMp;
    private HashMap<Integer, int[]> endMp;
    private int n;
    public ExamRoom(int n) {
        pq = new TreeSet<>((a, b) -> {
            int da = distance(a);
            int db = distance(b);
            if(da == db) return b[0] - a[0]; // index descending
            return da - db; // length ascending, so later we use last()
        });
        startMp = new HashMap<>();
        endMp = new HashMap<>();
        this.n = n;
        addInterval(new int[]{-1, n, n});
    }
    private void removeInterval(int[] a) {
        pq.remove(a); // main reason why we use TreeSet, PriorityQueue can only poll first
        startMp.remove(a[0]);
        endMp.remove(a[1]);
    }
    private void addInterval(int[] a) {
        pq.add(a);
        startMp.put(a[0], a);
        endMp.put(a[1], a);
    }
    
    private int distance(int[] a) {
        if(a[0] == -1) {
            return a[1];
        }else if(a[1] == n) {
            return n - 1 - a[0];
        }else{
            return (a[1] - a[0]) / 2;
        }
    }
    
    public int seat() {
        int[] current = pq.last();
        int seat;
        if(current[0] == -1) { // deal with imaginary first index -> seat at 0
            seat = 0;
        }else if(current[1] == this.n) { // imaginary last index
            seat = this.n - 1;
        }else{
            seat = (current[1] - current[0]) / 2 + current[0];
        }
        int[] left = new int[]{current[0], seat}; // start and end both not valid seats
        int[] right = new int[]{seat, current[1]};
        removeInterval(current);
        addInterval(left);
        addInterval(right);
        return seat;
    }
    
    public void leave(int p) {
        int[] left = endMp.get(p);
        int[] right = startMp.get(p);
        int[] newMerge = new int[]{left[0], right[1]};
        removeInterval(left);
        removeInterval(right);
        addInterval(newMerge);
    }
}
