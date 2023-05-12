public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int[] mem = new int[t.length()];
        int[] temp = new int[t.length()];
        // for(int i = 0;i < mem.length;i++){
        //     mem[i] = -1;
        // }
        for(int i = 0;i < s.length();i++){
            // for(int j = 0;j < t.length();j++){
            //     temp[j] = -1;
            // }
            for(int j = 0;j < t.length();j++){
                if(s.charAt(i) != t.charAt(j)){
                    continue;
                }else{
                    if(j == 0){
                        if(mem[j] == 0){
                            temp[j] = 1;
                        }else{
                            temp[j] += 1;
                        } 
                    }else if(mem[j-1] != 0){
                        if(mem[j] == 0){
                            temp[j] = mem[j-1];
                        }else{
                            temp[j] = mem[j] + mem[j-1];
                        }
                    }
                }
            }
            for(int j = 0;j < t.length();j++){
                mem[j] = temp[j];
            }
        }
        return (mem[mem.length-1] == -1) ? 0 : mem[mem.length-1];
    }
}
