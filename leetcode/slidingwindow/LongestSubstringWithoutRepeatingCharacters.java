package slidingwindow;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        /* 最初的想法是好想不是很好用DP 感觉从每一个char开始向后扩散 找到每一个char开头的最大substring
         * 要达到这个目标就需要每一个char用一个新的set 然后向后loop 这个方法过了leetcode 但是显然不是最优解 是O(N ** 2)
         * 
         */
        int max = 0;
        int slen = s.length();
        for(int i = 0;i < slen;i++) {
            Set<Character> mem = new HashSet<>();
            mem.add(s.charAt(i));
            int c = 1;
            for(int j = i + 1;j < slen;j++) {
                if(mem.contains(s.charAt(j))) break;
                mem.add(s.charAt(j));
                c++;
            }
            if(c > max) max = c;
        }
        return max;
    }
}
