package sorting;

public class PutMarblesInBags {
    /* 2551
     * 这道题没做出来 我用的方法是带备忘录的dfs 也就是dp 但是可惜没做对
     * 实际上 这道题目是sorting 或者说quick select 注意要先找规律 我们实际上 把array切了k-1刀
     * 最终的结果就是 A[0] + A[x] + A[x+1] + A[y] + A[y+1] +..+A[n-1]
     * 这里我们发现了一刀之后 当前和下一个都在result里 我们又发现A[0] A[n-1]是必带的 也就是说min和max毫无区别
     * 这样 问题就被转换成了 取最大的k-1个A[x] + A[x+1]组合 和最小的比较
     * 我们把A[x] + A[x+1]全部算好 这就变成了sort 或者说quick select
     * 他妈的我的快速选择到底有什么问题 为什么就是过不了 然后每次提交结果还不一样 我感觉不能用random 还得用三选一
     * 最后用了pq的方法 注意这里lq要用正序 因为我们要把当前的和lq里最小的比较
     */
    public long putMarbles(int[] a, int k) {
        PriorityQueue<Integer> lq = new PriorityQueue<>();
        PriorityQueue<Integer> sq = new PriorityQueue<>(Collections.reverseOrder());
        int n = a.length - 1;
        int[] b = new int[n];
        for(int i = 0;i < n;i++) {
            b[i] = a[i] + a[i+1];
        }
        int size = Math.min(k-1, n);
        if(size == 0) return 0;
        for(int i = 0;i < size;i++) {
            sq.offer(b[i]);
            lq.offer(b[i]);
        }
        for(int i = size;i < n;i++) {
            if(b[i] > lq.peek()) {
                lq.poll();
                lq.offer(b[i]);
            }
            if(b[i] < sq.peek()) {
                sq.poll();
                sq.offer(b[i]);
            }
        }
        long sum = 0;
        while(!sq.isEmpty()) {
            sum += lq.poll() - sq.poll(); 
        }
        return sum;
    }
    // public long putMarbles(int[] a, int k) {
    //     int n = a.length - 1;
    //     int[] b = new int[n];
    //     int[] c = new int[n];
    //     for(int i = 0;i < n;i++) {
    //         b[i] = a[i] + a[i+1];
    //         c[i] = a[i] + a[i+1];
    //     }
    //     for(int i = 0;i < n;i++) {
    //         System.out.print(c[i] + " ");
    //     }
    //     quickSelect(b, 0, n-1, k-1);
    //     quickSelect2(c, 0, n-1, k-1);
    //     long sum = 0;
    //     for(int i = 0;i < n;i++) {
    //         System.out.print(c[i] + " ");
    //     }
    //     System.out.println();
    //     for(int i = 0;i < k - 1;i++) {
    //         sum += c[i] - b[i];
    //     }
    //     return sum;
    // }
    // private void quickSelect(int[] nums, int lo, int hi, int k) {
    //     if(lo >= hi) return;
    //     int pivot = lo + (int)(Math.random() * (hi - lo + 1));
    //     swap(nums, lo, pivot);
    //     int index = lo + 1;
    //     for(int i = lo + 1;i <= hi;i++) {
    //         if(nums[i] < nums[lo]) {
    //             swap(nums, i, index++);
    //         }
    //     }
    //     swap(nums, lo, index - 1); // swap with last smaller
    //     if(k == index + 1) {
    //         return;
    //     }else if(k > index + 1) {
    //         quickSelect(nums, index + 1, hi, k);
    //     }else {
    //         quickSelect(nums, lo, index - 1, k);
    //     }
    // }
    // private void quickSelect2(int[] nums, int lo, int hi, int k) {
    //     System.out.println("current: " + lo + " " + hi);
    //     if(lo >= hi) return;
    //     int pivot = lo + (int)(Math.random() * (hi - lo + 1));
    //     swap(nums, lo, pivot);
    //     int index = lo + 1;
    //     for(int i = lo + 1;i <= hi;i++) {
    //         if(nums[i] >= nums[lo]) {
    //             swap(nums, i, index++);
    //         }
    //     }
    //     swap(nums, lo, index - 1); // swap with last smaller
    //     System.out.println("pivot: " + nums[index]);
    //     for(int i = lo;i <= hi;i++) {
    //         System.out.print(nums[i] + " ");
    //     }
    //     System.out.println();
    //     if(k == index + 1) {
    //         return;
    //     }else if(k > index + 1) {
    //         quickSelect(nums, index + 1, hi, k);
    //     }else {
    //         quickSelect(nums, lo, index - 1, k);
    //     }
    // }
    // private void swap(int[] nums, int a, int b) {
    //     int temp = nums[a];
    //     nums[a] = nums[b];
    //     nums[b] = temp;
    // }
}
