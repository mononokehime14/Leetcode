package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinationsPhoneNumber {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> output = new LinkedList<>();
        if(digits.length()==0){
            return output;
        }
        HashMap<String, List<String>> mem = new HashMap<>();
        mem.put("2", List.of("a","b","c"));
        mem.put("3", List.of("d","e","f"));
        mem.put("4", List.of("g","h","i"));
        mem.put("5", List.of("j","k","l"));
        mem.put("6", List.of("m","n","o"));
        mem.put("7", List.of("p","q","r", "s"));
        mem.put("8", List.of("t","u","v"));
        mem.put("9", List.of("w","x","y", "z"));
        output.addAll(mem.get(digits.split("")[0]));
        for(int i = 1;i<digits.length();i++){
            List<String> temp = mem.get(digits.split("")[i]);
            int size = output.size();
            for(int j = 0;j < size;j++){
                String t = output.remove();
                for(int k = 0; k < temp.size();k++){
                    output.add(t+temp.get(k));
                }
            }
        }
        return output;
    }
}
