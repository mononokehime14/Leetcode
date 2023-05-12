public class NumberOfMatchingSubsequences {
    public int numMatchingSubseq(String s, String[] words) {
        /* 792
         * 又是看答案做medium的一天
         * 这道题和392的区别在于有一个list of words 这样 如果s比较长的话 每一次都要进行一次O(N)的比较 这是比较浪费时间的
         * 能不能把s的某种状态记录下来 然后后面加以利用呢
         * 这里就有一个方法 记录每一个字母 在s中出现的index
         * 然后在比对的时候 我们bs index 找到比我们能容忍的最小位置大于等于的index 找不到就寄
         * 这个是基于subsequence必然是保持顺序的 我们每次会根据找到的index 更新能容忍的最小位置
         * 有一个细节 在于BS找到第一个比j大的值
         * 这是一个边界搜索 特点在于没有找到的情况在于left在最右边
         * 这里有一个已知能work的组合 左闭右开 left == right退出 right = mid 因为right一直不是我们要的 最后return left
         */
        ArrayList<Integer>[] tmem = new ArrayList[26];
        for(int i = 0;i < s.length();i++) {
            char c = s.charAt(i);
            if(tmem[c - 'a'] == null) tmem[c - 'a'] = new ArrayList<>();
            tmem[c - 'a'].add(i);
        }
        int answer = 0;
        for(String ss: words) {
            int j = 0, i = 0;
            for(;i < ss.length();i++) {
                char c = ss.charAt(i);
                if(tmem[c - 'a'] == null) break;
                int index = binarySearch(tmem[c - 'a'], j);
                if(index == -1) break;
                j = index + 1;
            }
            if(i == ss.length()) answer++;
        }
        return answer;
    }
    private int binarySearch(List<Integer> tmem, int j) {
        int left = 0, right = tmem.size();
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(tmem.get(mid) < j) {
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        if(left == tmem.size()) return -1;
        return tmem.get(left);
    }
}
