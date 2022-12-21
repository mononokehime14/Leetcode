package greedy;
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        /*
         * 需要从一个出现次数最高的数字开始思考，其需要idle之可能性最高
         * 最高的数字，如果中间隔开n，则可A...A...A...A...排列
         * 任何和A出现次数一样的数字，可以安排在每个A后面，这样中间需求就少一个
         * 任何少于A出现次数的数字，拆散塞到每个间隔里，他们之间则必然隔开n
         * 这样arrange，我们便只要算出来，需要多少idle就可以了
         * 考虑所有和A一样的数字之后，中间的需求就出来了，减去其他所有数字的出现次数，就是idle数
         */
        int max = 0,max_count = 0;
        int[] count = new int[26];
        for(int i = 0;i < tasks.length;i++){
            int temp = tasks[i] - 'A';
            count[temp]++;
            if(count[temp] == max){
                max_count++;
            }else if(count[temp] > max){
                max_count = 1;
                max = count[temp];
            }
        }
        return tasks.length + Math.max(0, (max - 1) * (n - max_count + 1) - (tasks.length - max_count * max));
    }
}
