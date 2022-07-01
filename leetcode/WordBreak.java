import java.util.List;
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] mem = new boolean[s.length()];
        for(int i = 0;i < mem.length;i++){
            mem[i] = false;
        }
        for(int i = 0;i < wordDict.size();i++){
            int wsize = wordDict.get(i).length();
            if(wsize <= s.length() && !mem[wsize-1] && s.substring(0,wsize).equals(wordDict.get(i))){
                mem[wsize-1] = true;
            }
        }
        for(int i = 0;i < s.length();i++){
            if(mem[i]){
                continue;
            }
            for(int j = 0;j < wordDict.size();j++){
                int wsize = wordDict.get(j).length();
                if(i-wsize>-1 && mem[i - wsize] && s.substring(i - wsize + 1,i+1).equals(wordDict.get(j))){
                    mem[i] = true;
                    break;
                } 
            }
        }
        return mem[mem.length - 1];
    }
}
