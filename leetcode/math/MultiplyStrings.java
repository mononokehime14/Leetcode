package math;

public class MultiplyStrings {
    /* 43
     * 并咩有什么好的算法 就是模拟手算 现乘再加
     * 注意carrier在最后要处理一下别忘了
     */
    public String multiply(String num1, String num2) {
        String answer = "";
        int m = num1.length(), n = num2.length();
        for(int i = m - 1;i >= 0;i--) {
            int carrier = 0;
            StringBuilder sb = new StringBuilder();
            int a = num1.charAt(i) - '0';
            for(int j = n - 1;j >= 0;j--) {
                int b = num2.charAt(j) - '0';
                int c = a * b + carrier;
                if(c > 9) {
                    carrier = c / 10;
                    c %= 10;
                }else{
                    carrier = 0;
                }
                sb.append((char)(c + '0'));
            }
            if(carrier != 0) sb.append(carrier);
            sb = sb.reverse();
            for(int k = m - 1;k > i;k--) sb.append("0");
            answer = add(answer, sb.toString());
            System.out.println(sb.toString() + " " + answer);
        }
        if(allZero(answer)) return "0";
        return answer;
    }
    private String add(String a, String b) {
        int carrier = 0;
        int left = a.length() - 1, right = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        while(left >= 0 && right >= 0) {
            int l = a.charAt(left--) - '0', r = b.charAt(right--) - '0';
            int c = l + r + carrier;
            if(c > 9) {
                carrier = 1;
                c %= 10;
            }else{
                carrier = 0;
            }
            sb.append((char)(c + '0'));
        }
        while(left >= 0) {
            int l = (a.charAt(left--) - '0') + carrier;
            if(l > 9) {
                carrier = 1;
                l %= 10;
            }else{
                carrier = 0;
            }
            sb.append((char)(l + '0'));
        }
        while(right >= 0) {
            int r = (b.charAt(right--) - '0') + carrier;
            if(r > 9) {
                carrier = 1;
                r %= 10;
            }else{
                carrier = 0;
            }
            sb.append((char)(r + '0'));
        }
        if(carrier == 1) sb.append("1");
        return sb.reverse().toString();
    }
    private boolean allZero(String a) {
        for(char c : a.toCharArray()) {
            if(c != '0') return false;
        }
        return true;
    }
}
