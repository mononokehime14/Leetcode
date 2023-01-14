package backtrackdfs;

public class DifferentWaysToAddParentheses {
    /* 241
     * 自己做出来的 思路很简单 中间卡了一下 其实不是左边右边拎出来 而是中间每一个operator都应该断一下 左边右边答案递归下去
     * 这是一个简单的递归 我本想用备忘录 但是其实这个递归树有点像分而治之 是没有重叠子问题的 所以把memo去掉之后 速度反而到了80%+
     * 注意length是小于20的 这也是2 ** n方法能行的原因 每次左右两边两个sub tree 所以是2 ** n
     */
    // HashMap<String, List<Integer>> memo;
    public List<Integer> diffWaysToCompute(String expression) {
        List<String> exp = new ArrayList<>();
        // memo = new HashMap<>();
        char[] expression2 = expression.toCharArray();
        int n = expression2.length;
        for(int i = 0;i < n;i++) {
            if(expression2[i] == '-' || expression2[i] == '+' || expression2[i] == '*') {
                exp.add(String.valueOf(expression2[i]));
            }else{
                if(i + 1 < n && expression2[i+1] != '-' && expression2[i+1] != '+' && expression2[i+1] != '*') {
                    exp.add(expression.substring(i, i+2));
                    i++;
                }else{
                    exp.add(String.valueOf(expression2[i]));
                }
            }
        }
        return calculate(exp, 0, exp.size() - 1);
    }
    private List<Integer> calculate(List<String> exp, int i, int j) {
        if(i == j) {
            return Collections.singletonList(Integer.parseInt(exp.get(i)));
        }
        // String state = i + "," + j;
        // if(memo.containsKey(state)) return memo.get(state);
        List<Integer> answer = new ArrayList<>();
        for(int mid = i + 1;mid < j;mid += 2) {
            List<Integer> left = calculate(exp, i, mid - 1);
            List<Integer> right = calculate(exp, mid + 1, j);
            for(int num1 : left) {
                for(int num2 : right) {
                    if(exp.get(mid).equals("+")) {
                        answer.add(num1 + num2);
                    }else if(exp.get(mid).equals("-")) {
                        answer.add(num1 - num2);
                    }else{
                        answer.add(num1 * num2);
                    }
                }
            }
        }
        // memo.put(state, answer);
        return answer;
    }
}
