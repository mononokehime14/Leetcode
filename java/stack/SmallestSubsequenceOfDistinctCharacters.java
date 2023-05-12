package stack;

public class SmallestSubsequenceOfDistinctCharacters {
    /* 1081
     * 316. Remove Duplicate Letters同样的代码
     * 这道题拖了很长的时间 我一开始觉得 要用滑动窗口 保持一个对窗口内character的count 向右扩张 左边收缩则是收缩到不符合全部的distinct character为止
     * 这样 我们应该能够找到所有的包含distinct characters的subsequence 但是实际上这个思路到这里就已经错了 subsequence就不能用滑动窗口 会错过正确答案的
     * 左边收缩完 有可能左边的一些character加上右边未来的实际上能够有正确答案 这样就漏掉了
     * 这里的正确思路 是用单调栈 做了某种程度上的选择 对于一个char 我要不要它在最后的sub sequence里呢
     * 如果重复 当然不要 所以要一个当前characters的count
     * 如果比之前的字母序小 那么我们扔掉前面大于的 从当前的character接着继续 这样的话整个subsequence必然字母序更小
     * 这里需要一个补丁 如果前面的是唯一的了 那么我们不能扔掉 不然的话后面也没地方补上来了 所以需要一个所有characters的count 妙哉妙哉
     */
    public String smallestSubsequence(String s) {
        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c-'a']++;
        }
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[26];
        for(char c : s.toCharArray()) {
            count[c-'a']--;
            if(inStack[c-'a']) continue; // duplicate
            while(!stack.empty() && stack.peek() > c) {
                if(count[stack.peek()-'a'] == 0) break;
                inStack[stack.pop()-'a'] = false;
            }
            stack.push(c);
            inStack[c-'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.empty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }
}
