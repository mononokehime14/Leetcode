import java.util.ArrayList;
import java.util.List;
public class WordBreak2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        /*
         * 这里亦有更快的方法，注意到题目里s的长度要小于worddict的size，故此，第二个for循环可以
         * 不loop worddict，而loop s的所有subset（从i开始），每个subset查一下wordDict（变成一个hashset）
         * 题目假设wordDict里无重复元素，所以没问题。
         */
        List<List<String>> mem = new ArrayList<>();
        for(int i = 0;i < s.length();i++){
            mem.add(new ArrayList<>());
        }
        for(int i = 0;i < wordDict.size();i++){
            int wsize = wordDict.get(i).length();
            if(wsize <= s.length() && s.substring(0,wsize).equals(wordDict.get(i))){
                if(wsize == s.length()){
                    mem.get(wsize-1).add(wordDict.get(i));
                }else{
                    mem.get(wsize-1).add(wordDict.get(i)+" ");
                }
            }
        } 
        for(int i = 0;i < s.length();i++){
            for(int j = 0;j < wordDict.size();j++){
                int wsize = wordDict.get(j).length();
                int start = i - wsize;
                if(start>-1 && s.substring(start + 1,i+1).equals(wordDict.get(j)) && mem.get(start).size() > 0){
                    for(int k = 0;k < mem.get(start).size();k++){
                        if(i == s.length() - 1){
                            mem.get(i).add(mem.get(start).get(k) + wordDict.get(j));
                        }else{
                            mem.get(i).add(mem.get(start).get(k) + wordDict.get(j) + " ");
                        }    
                    }
                } 
            }
        }
        return mem.get(s.length() - 1);
    }
}
