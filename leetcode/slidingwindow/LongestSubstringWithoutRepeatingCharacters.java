package slidingwindow;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        /* 最初的想法是好想不是很好用DP 感觉从每一个char开始向后扩散 找到每一个char开头的最大substring
         * 要达到这个目标就需要每一个char用一个新的set 然后向后loop 这个方法过了leetcode 但是显然不是最优解 是O(N ** 2)
         * 更好的方法应该是滑动窗口 如何滑动呢 两层while嵌套应该是一种保底思路 往右扩张一格相当于新的一个char 对于这个char
         * 左边收缩window 找到一个不重复的window 在这个过程中 时间是loop O(N) 空间也不用每次都新建一个set
         */
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0, m = s.length(), max = 0;
        while(right < m) {
            char r = s.charAt(right);
            right++;
            window.put(r, window.getOrDefault(r, 0) + 1);
            while(window.get(r) > 1) {
                char l = s.charAt(left);
                left++;
                window.put(l, window.get(l) - 1);
            }
            max = Math.max(right - left, max);
        }
        return max;
        // int max = 0;
        // int slen = s.length();
        // for(int i = 0;i < slen;i++) {
        //     Set<Character> mem = new HashSet<>();
        //     mem.add(s.charAt(i));
        //     int c = 1;
        //     for(int j = i + 1;j < slen;j++) {
        //         if(mem.contains(s.charAt(j))) break;
        //         mem.add(s.charAt(j));
        //         c++;
        //     }
        //     if(c > max) max = c;
        // }
        // return max;
    }
}
