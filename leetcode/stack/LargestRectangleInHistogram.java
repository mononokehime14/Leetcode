package stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        /*
         * 这是一个单调栈能解决的问题 也就是石知一栈 实质上是某种滑动窗口的思路
         * 单调栈是O(N)的 每一个元素只会入栈和出栈一次 模版是一个for循环套while循环 for循环循环每一个元素 入栈
         * 在入栈之前 要进行一些检查 然后用while循环抛弃掉不要的
         * 之所以单调栈能够解决问题 是因为问题的某种特性 比如在next greater element里面 遇到一个比当前peek高的 我们可以抛弃掉之前所有比这个元素矮的
         * 这个元素一定是他们的next greater element
         * 在这道题目里 这个特性就是如果当前的element a比前一个b的height小 那么之后包含b的任何长方形的高度都只能取决于a 那么b可以抛弃掉了 height用a的就可以了
         * 在抛弃掉b的时候 同理要把b之前所有同样比a高的抛弃掉 这一定是一个递增的序列 那么抛弃的时候 每一个长方形可以计算面积 和max进行比较
         * 抛弃掉所有比a高比b低或者一样的element 因为之后也不会有任何增长了 只会取决于a的高度
         * 
         * 这里代码有几个比较复杂的细节 首先是如果 5 6 2 当前是2的时候要抛弃5 6
         * 我们需要计算的面积是6 * 1和5 * 2 height没什么好说的 主要是横坐标间距 可以用2的index减去5前element的+1 这里看起来没什么特别的 好像直接减5的index也可以
         * 如果考虑完全2 1 5 6 2 3 就会发现 5 6滚了之后 最后stack里还有1 2 3 这个时候我们比如pop到2
         * 长方形并不是从2到结尾！ 而是从1的index+1到结尾 这是因为我们把5 6从概念上砍成了2 就必须考虑这些elements 直到上一个比2小的element
         * 
         * 一开始加入-1是为了计算方便的 不用检查empty 同时在横坐标的计算时天然的代表了那一个比当前element小的元素
         */
        int n = heights.length, max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i = 0;i < n;i++) {
            while(stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1)); 
            }
            stack.push(i);
        }
        while(stack.peek() != -1) {
            max = Math.max(max, heights[stack.pop()] * (n - stack.peek() - 1)); 
        }
        return max;
    }
}
