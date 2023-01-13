package backtrackdfs;
import java.util.ArrayList;
class GenerateParenthesis {
    //Well formed/valid的检查方式是balance，在任何一个position，)的数量都不能多于(的数量，反之则是
    //可以的，因为反正最后总数一样，后面再加)完全OK。另外一个重要的思路就是所有存在的可能性，可以用recursion实现
    ArrayList<String> mem;
    public List<String> generateParenthesis(int n) {
        mem = new ArrayList<>();
        generate("", 0, 0, n);
        return mem;
    }
    private void generate(String s, int left, int right, int max){
        if(right == max && left == max){
            mem.add(s);
            return;
        }
        if(left < max){
            String stemp = s + "(";//s will be used again later
            generate(stemp, left+1, right, max);
        }
        if(right < left){
            s += ")";
            generate(s, left, right+1, max);
        }
        return;
    }
}