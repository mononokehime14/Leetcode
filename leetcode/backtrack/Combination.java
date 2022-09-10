package backtrack;

import java.util.*;
public class Combination {
    List<List<Integer>> output;
    public List<List<Integer>> combine(int n, int k) {
        output = new ArrayList<>();
        backtrack(0, 0, n, k, new ArrayList<Integer>());
        return output;
    }
    private void backtrack(int start, int index, int n, int k, List<Integer> combination) {
        if(index == k) {
            output.add(new ArrayList<>(combination));
            return;
        }
        for(int i = start;i < n;i++) {
            // real number is i + 1
            combination.add(i + 1);
            backtrack(i+1, index + 1, n, k, combination);
            combination.remove(combination.size() - 1);
        }
    }
}
