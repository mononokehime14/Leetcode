public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /* 最初想用DFS是为什么呢 想不开啊 这里题目要求的是最短的方法 而且每一次选择其实想错了
         * 一开始是设想先换第一个字母 然后第二个 但是这样是不对的 有可能从beginWord开始 第一个字母换不了 不在wordlist里 但是其他字母可以
         * 这里不能假设有某种顺序
         * 所以每一次选择 就是全部换一遍 只要在wordList并且没有visit过那我们就继续加入 这样自然只要套用BFS的模版就可以了
         * 这里单向BFS并不是最快的方法 而是双向BFS 代码类似 用两个set轮流 我没有写 拷贝了双向BFS的代码在下
         */
        HashSet<String> wordMap = new HashSet<>(wordList);
        if(!wordMap.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<String>();
        q.add(beginWord);
        int count = 1, n = beginWord.length();
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0;i < size;i++) {
                char[] current = q.poll().toCharArray();
                for(int j = 0;j < n;j++) {
                    char backUp = current[j];
                    for(int k = 0;k < 26;k++) {
                        current[j] = (char) ('a' + k);
                        String newS = String.valueOf(current);
                        if(newS.equals(endWord)) return count + 1;
                        if(!visited.contains(newS) && wordMap.contains(newS)) {
                            // System.out.println(count + " " + newS);
                            visited.add(newS);
                            q.add(newS);
                        }
                    }
                    current[j] = backUp;
                }
            }
            count++;
        }
        return 0;
    }
    // public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        // 双向BFS通常需要两个Set 用来快速搜索element是否在那个set里
    //     Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();
    
    //     int len = 1;
    //     int strLen = beginWord.length();
    //     HashSet<String> visited = new HashSet<String>();
        
    //     beginSet.add(beginWord);
    //     endSet.add(endWord);
    //     while (!beginSet.isEmpty() && !endSet.isEmpty()) {
    //         if (beginSet.size() > endSet.size()) {
    //             Set<String> set = beginSet;
    //             beginSet = endSet;
    //             endSet = set;
    //         }
    
    //         Set<String> temp = new HashSet<String>();
    //         for (String word : beginSet) {
    //             char[] chs = word.toCharArray();
    //             for (int i = 0; i < chs.length; i++) {
    //                 for (char c = 'a'; c <= 'z'; c++) {
    //                     char old = chs[i];
    //                     chs[i] = c;
    //                     String target = String.valueOf(chs);
    
    //                     if (endSet.contains(target)) {
    //                         return len + 1;
    //                     }
    
    //                     if (!visited.contains(target) && wordList.contains(target)) {
    //                         temp.add(target);
    //                         visited.add(target);
    //                     }
    //                     chs[i] = old;
    //                 }
    //             }
    //         }
    
    //         beginSet = temp;
    //         len++;
    //     }
        
    //     return 0;
    // }
}
