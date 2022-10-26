package array;

public class AddBinary {
    public String addBinary(String a, String b) {
        /* 
         * 从尾巴到头 计算了一遍 有一些细节 比如当前bit加起来1或者3 留下都是1 0或者2 留下都是0
         * 速度不算很快 感觉是处理string的问题 附上了1ms的答案 利用了buff array 直接用array构建了string
         */
        char[] al = a.toCharArray();
        char[] bl = b.toCharArray();
        int righta = al.length - 1, rightb = bl.length - 1, carrier = 0;
        List<Character> result = new ArrayList<>();
        while(righta >= 0 || rightb >= 0) {
            int temp = carrier;
            if(righta >= 0) temp += (al[righta] - '0');
            if(rightb >= 0) temp += (bl[rightb] - '0');
            carrier = temp >= 2 ? 1 : 0;
            char bit = temp % 2 == 1 ? '1' : '0';
            result.add(bit);
            righta--;
            rightb--;
        }
        if(carrier == 1) result.add('1');
        StringBuilder sb = new StringBuilder();
        for(int i = result.size() - 1;i >= 0;i--) {
            sb.append(result.get(i));
        }
        return sb.toString();
        // char[] buff = new char[n + 1];
        // int size = 0;
        
        // //Arrays.fill(buff, '0');
        
        // int rem = 0;
       
        // for (int i = 0; i<=n; i++) {
                      
        //     int ai = a.length() - 1 - i;
                        
        //     if (ai >= 0)
        //         rem += a.charAt(ai) - '0';         
            
        //     int bi = b.length() - 1 - i;
            
        //     if (bi >= 0)
        //         rem += b.charAt(bi) - '0';
                
        //     if (rem == 3) {
        //         rem = 1;
        //         buff[n - size++] = '1';
        //     } else if (rem == 2) {
        //         rem = 1;
        //         buff[n - size++] = '0';
        //     } else if (rem == 1) {
        //         rem = 0;
        //         buff[n - size++] = '1';
        //     } else {
        //         buff[n - size++] = '0';
        //     }
        // }
        
        // return buff[0] != '0' ? String.valueOf(buff) : String.valueOf(buff, 1, size - 1);
    }
}
