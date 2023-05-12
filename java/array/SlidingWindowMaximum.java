package array;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        /** 239
         * 这道题的解法是单调队列 保持一个窗口 那么 如何才能记录窗口内的最大值呢 我们入队列的时候 把小于自己的全部抛弃掉 这样队列首必然是最大值
         * 那么 由如何保证删除后 能够重新计算最大值呢 删除的时候 除非数字就是最大值 不然不必删除 因为加入的时候已经删除掉了 因为加入队列的特性 即便删掉了最大值
         * 下一个也一定是第二大的
         * 注意这里只有在入队列的时候做while循环才行
         */
        int n = nums.length, index = 0;
        LinkedList<Integer> q = new LinkedList<>();
        int[] answer = new int[n - k + 1];
        for(int i = 0;i < n;i++) {
            while(!q.isEmpty() && q.getLast() < nums[i]) q.removeLast();
            q.addLast(nums[i]);
            if(i >= k) {
                if(nums[i - k] == q.getFirst()) {
                    q.removeFirst();
                }
            }
            if(i >= k - 1) answer[index++] = q.getFirst();
        } 
        return answer;
    }
}
