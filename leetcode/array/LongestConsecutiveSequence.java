package array;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        /** 128
         * 这道题一开始没想出来 但是其实这种longestSubstring/Subsequence的题目 如果是array的做法 可以尝试用中心扩散双指针的方法
         * 上次longest Palindrome substring就是这样 在这里 虽然不是物理上临近的数字 但是仍然用差不多的想法
         * 可以用一个hashset先保存使得我们扩散时候寻找数字是O(1) 然后注意删除掉 因为一次扩散必然能把这一段最长的序列找出来 就不用后面再重复算了
         */
        Set<Integer> map = new HashSet<>();
        int max = 0;
        for(int i: nums) map.add(i);
        for(int i: nums) {
            int pre = i - 1, post = i + 1, count = 1;
            while(map.contains(pre)) {
                map.remove(pre);
                pre--;
                count++;
            }
            while(map.contains(post)) {
                map.remove(post);
                post++;
                count++;
            }
            max = Math.max(count, max);
        }
        return max;
    }
}
