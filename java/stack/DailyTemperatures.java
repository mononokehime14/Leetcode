package stack;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        /*
         * 套用的单调栈模版 和next greater是一摸一样的 出现一个大的 抛弃前面所有比它小的
         * 这里唯一的区别在于需要返回横坐标间距
         */
        int n = temperatures.length;
        Stack<Integer> stack = new Stack<Integer>();
        int[] answer = new int[n];
        for(int i = 0;i < n;i++) {
            while(!stack.empty() && temperatures[stack.peek()] < temperatures[i]) {
                int prev = stack.pop();
                answer[prev] = i - prev;
            }
            stack.push(i);
        }
        return answer;
    }
}
