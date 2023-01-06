package design;

public class FindMedianFromDataStream {
    /* 295
     * 牛逼！！！！不看答案手撕的hard
     * 好像也没有多hard 思路就是要找到median 通常要一个有序数组 用索引找 但是这个数组是动态变化的 数字不断的在加进来
     * 肯定不能每次加进来排序 动态保持有序的话 Priority Queue可以 但是无法快速找到中间的索引
     * 解决办法就是两个PQ 自己保持平衡 奇数的话右边多一个 加入的时候 和右边第一个比较 小于就加到左边的PQ里 出现不平衡的情况调整一下
     * 注意两个PQ的排序方法要反过来 这样才能保证拿到median的左右手
     */
    private Queue<Integer> leftQ;
    private Queue<Integer> rightQ;
    public MedianFinder() {
        leftQ = new PriorityQueue<>((a, b) -> b - a);
        rightQ = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(rightQ.size() == 0) {
            rightQ.add(num);
            return;
        }
        if(num >= rightQ.peek()) {
            if(rightQ.size() > leftQ.size()) {
                rightQ.add(num);
                leftQ.add(rightQ.poll());
            }else{
                rightQ.add(num);
            }
        }else{
            if(rightQ.size() > leftQ.size()) {
                leftQ.add(num);
            }else{
                leftQ.add(num);
                rightQ.add(leftQ.poll());
            }
        }
    }
    
    public double findMedian() {
        if((leftQ.size() + rightQ.size()) % 2 == 1) return rightQ.peek() * 1.0;
        return (leftQ.peek() + rightQ.peek()) / 2.0;
    }
}
