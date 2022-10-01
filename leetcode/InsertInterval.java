public class InsertInterval {
    /* 这道题目也没有用算法 我觉得是一种解题模式 碰到interval类题目的时候 是不是考虑loop加入
     * 边加入边比较 比起先加入再插入 应该不知道要好到哪里去了
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> newIntervals = new LinkedList<int[]>();
        boolean modified = false;
        for(int i = 0;i < intervals.length;i++) {
            int[] current = intervals[i];
            if(modified || current[1] < newInterval[0]) {// front
                newIntervals.add(current);
            }else if(newInterval[1]< current[0]) { //no overlap
                newIntervals.add(newInterval);
                newIntervals.add(current);
                modified = true;
            }else{
                newInterval[0] = Math.min(newInterval[0], current[0]);
                newInterval[1] = Math.max(newInterval[1], current[1]);
            }
        }
        if(!modified) newIntervals.add(newInterval);
        return newIntervals.toArray(new int[0][0]);
    }
}