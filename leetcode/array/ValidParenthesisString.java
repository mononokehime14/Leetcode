package array;

public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        /*
         * 678
         * 这道题的思路有点难想的很全 照顾到所有细节
         * 首先是基本思路在于 检查只有一种() 可以使用一个(的count 后面每次加减之后检查是否小于0 也就是)比(多 这种情况就已经寄了
         * 最后检查是否等于0
         * 加入*的考量之后 我们可以保持两个count 一个每次都把*当成(用 一个每次都把*当成)用 前一个如果用上了)还是比(多 那么这种情况已经寄了
         * 需要注意的是第二种用法 如果count已经为0 也就是说平衡 那就不用添乱 *可以当作“”用
         */
        int cmin = 0, cmax = 0;
        for(char c: s.toCharArray()) {
            if(c == '(') {
                cmin++;
                cmax++;
            }else if(c == ')') {
                cmin--;
                cmax--;
            }else if(c == '*') {
                cmax++; // if * is (
                cmin--; // if * is )
            }
            if(cmax < 0) return false; // use * as ( still cannot match number of )
            cmin = Math.max(0, cmin); // enter here means we have enough ( to match ), we can also use * as ) to clear number of (,
            // but if alreay match, we dont need to decrease it further, we can skip 
            // 如果没有* 一直有）cmin仍然会为0 但是这种情况不会通过cmax的检查
        }
        return cmin == 0;
    }
}
