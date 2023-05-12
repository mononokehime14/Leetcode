package array;

public class StringToIntegerAoi {
    /* 这道题非常烦 主要是题目不能理解错 首先是如果string里只看第一个开头到下一个不是number的char 第一段数字 不然就是0
     * 剩下的唯一细节在于overflow detection 我本来是想 加上去overflow 结果肯定是比原数字小 但是java好像不能这么检测
     * 这样我们只能在加之前检测
     */
    public int myAtoi(String s) {
        s = s.stripLeading();
        if(s.equals("")) return 0;
        char[] sa = s.toCharArray();
        int answer = 0, start = 0;
        boolean positive = true;
        if(sa[0] == '-') positive = false;
        if(sa[0] == '-' || sa[0] == '+') start = 1;
        for(int i = start;i < sa.length;i++) {
            int current = sa[i] - '0';
            if(current > 9 || current < 0) {
                break;
            }
            // System.out.println("char " + sa[i] + " " + current);
            // System.out.println(i + " " + numberflag);
            if(answer > Integer.MAX_VALUE / 10 || 
              (answer == Integer.MAX_VALUE / 10 && current > Integer.MAX_VALUE % 10)) { //overflow
                return !positive ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            answer = answer * 10 + current;
            //System.out.println(answer + " "+ sa[i]);
        }
        return positive? answer : answer * -1;
    }
}
