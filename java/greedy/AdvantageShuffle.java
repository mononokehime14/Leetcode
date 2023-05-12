package greedy;

public class AdvantageShuffle {
    /* 870
     * 田忌赛马 我认为这是用了贪心的 首先我们的比较方式是那最大的相比 比不过就用最小的混一下 比得过就直接比得过
     * 这里为什么不需要节省 比如我一开始的想法是二分查找第一个大于的 是因为最大的已经比掉了 后面不可能有更大的了 所以不必节省
     * 那么这里剩下的就只是代码实现了 注意由于int array本身是primitive 不方便重写comparator 这里还是直接升序排列的 另一边则是降序
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        List<int[]> newNums2 = new ArrayList<>();
        int n = nums2.length;
        for(int i = 0;i < n;i++) newNums2.add(new int[]{nums2[i], i});
        Collections.sort(newNums2, (a, b) -> b[0] - a[0]);
        Arrays.sort(nums1);
        int[] answer = new int[n];
        int left = 0, right = n - 1;
        for(int i = 0;i < n;i++) {
            if(nums1[right] <= newNums2.get(i)[0]) {
                answer[newNums2.get(i)[1]] = nums1[left];
                left++;
            }else{
                answer[newNums2.get(i)[1]] = nums1[right];
                right--;
            }
        }
        return answer;
    }
}
