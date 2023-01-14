package array;

public class SplitArrayIntoConsecutiveSubsequences {
    /* 659
     * 这道题的思路不是很好想 首先是要考虑 要把数字放入堆里 堆里是连续上升的数字 不行就新开一堆 和快速LIS差不多
     * 但是这道题有一些条件可以利用 比如我是知道这个数字能不能新开的 因为我能够知道连着三个上升数字的freq 看看后面是不是有这些数字
     * 另一个条件是数组是升序的 这样我们就根本不用考虑前面成型的堆 所以不想LIS我们要保持所有的堆还要二分搜索
     * 这样的话 我们可以抽象出这样的概念 如果可以拼接到现在的堆上 拼 不行的话看看能不能新开 不行的话就寄
     * 为什么拼接的优先级更高呢 因为1 2 3和4 5 6 4即可以新开堆又可以拼接 所以不用担心我们漏掉解 而先新开堆 就会造成1 2 3 4 5 5 6 7
     * 分成1 2 3, 4 5 6, 5 & 7? 就不对了
     * 实现的思路也很有意思 用了两个counting 一个记录拥有的数字 一个记录需要的
     */
    public boolean isPossible(int[] nums) {
        HashMap<Integer, Integer> need = new HashMap<>();
        HashMap<Integer, Integer> freq = new HashMap<>();
        for(int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
            need.put(i, 0);
        }
        for(int i : nums) {
            if(freq.get(i) == 0) continue; //之前用过了
            if(need.get(i) != 0) {
                need.put(i, need.get(i) - 1);
                need.put(i+1, need.getOrDefault(i+1, 0) + 1);
                freq.put(i, freq.get(i) - 1);
            }else if(freq.getOrDefault(i + 1, 0) > 0 && freq.getOrDefault(i + 2, 0) > 0) {
                freq.put(i, freq.get(i) - 1);
                freq.put(i+1, freq.get(i+1) - 1);
                freq.put(i+2, freq.get(i+2) - 1);
                need.put(i+3, need.getOrDefault(i+3, 0) + 1);
            }else{
                return false;
            }
        }
        return true;
    }
}
