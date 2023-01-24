public class Pascal'sTriangleII {
    /* 119
     * 还挺有意思的easy题目 我思考了一下好像没有办法只能n平方
     * 实现上出现了分别 我的实现非常白痴 就是list 类似模拟的方法 为了空间O(N) 我改成了一个linkedlist 避免用temp拷贝
     * 结果巨慢 答案用的才是正儿八经的dp 注意它的更新方向 如果正向 那么要往第一个地方加1 如果反向 最后一个自然而然就是1
     * 而且不用动最前面的1 实在是比较好的解法
     */
    public List<Integer> getRow(int rowIndex) {
        int[] answer = new int[rowIndex + 1];
        answer[0] = 1;
        for(int i = 1;i <= rowIndex;i++) {
            for(int j = i;j > 0;j--) {
                answer[j] = answer[j-1] + answer[j];
            }
        }
        return Arrays.stream(answer).boxed().collect(Collectors.toList());
    }
    // public List<Integer> getRow(int rowIndex) {
    //     List<Integer> answer = new ArrayList<Integer>();
    //     answer.add(1);
    //     if(rowIndex == 0) return answer;
    //     for(int i = 1;i <= rowIndex;i++) {
    //         List<Integer> temp = new ArrayList<>();
    //         temp.add(1);
    //         for(int j = 0;j < answer.size() - 1;j++) {
    //             temp.add(answer.get(j) + answer.get(j+1));
    //         }
    //         temp.add(1);
    //         answer = temp;
    //     }
    //     return answer;
    // }
}
