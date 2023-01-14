package greedy;

public class IntervalListIntersections {
    /* 986
     * 这道题并非贪心 放到这里只是为了和其他interval得题目放在一起
     * 只要细致一点分类讨论就可以了
     * 这里注意 理出intersection的4种情况之后 可以归类 发现intersection只有在两个右边分别大于另外的左边时有
     * 然后要保留哪个肯定是end大的那一个 小的滚 下一个 这样我们可以写出更加简单的循环
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int left = 0, right = 0, m = firstList.length, n = secondList.length;
        List<int[]> answer = new ArrayList<>();
        while(left < m && right < n) {
            if(firstList[left][1] >= secondList[right][0] && secondList[right][1] >= firstList[left][0]) {
                // has intersection
                answer.add(new int[]{Math.max(firstList[left][0], secondList[right][0]), Math.min(firstList[left][1], secondList[right][1])});
            }
            if(firstList[left][1] >= secondList[right][1]) {
                right++;
            }else{
                left++;
            }
        }
        // while(left < m && right < n) {
        //     if(firstList[left][0] > secondList[right][1]) {
        //         right++;
        //     }else if(secondList[right][0] > firstList[left][1]) {
        //         left++;
        //     }else if(firstList[left][0] >= secondList[right][0]) {
        //         if(firstList[left][1] >= secondList[right][1]) {
        //             answer.add(new int[]{firstList[left][0], secondList[right][1]});
        //             right++;
        //         }else{
        //             answer.add(firstList[left]);
        //             left++;
        //         }
        //     }else if(secondList[right][0] > firstList[left][0]) {
        //         if(secondList[right][1] >= firstList[left][1]) {
        //             answer.add(new int[]{secondList[right][0], firstList[left][1]});
        //             left++;
        //         }else{
        //             answer.add(secondList[right]);
        //             right++;
        //         }
        //     }
        // }
        return answer.stream().toArray(int[][] ::new);
    }
}
