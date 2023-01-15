package math;
import java.util.HashMap;

import java.util.HashMap;
public class Fraction2RecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";
        String output = ((numerator < 0) ^ (denominator < 0)) ? "-" : "";
        long n = Math.abs(numerator);
        long d = Math.abs(denominator);
        /* 整数部分 */
        long a = n / d;
        long remainder = n - d * a;
        output += String.valueOf(a);
        if(remainder == 0) return output;
        output += ".";
        StringBuilder oo = new StringBuilder();
        HashMap<Long, Integer> mem = new HashMap<>();
        int index = 0;
        mem.put(remainder, index);
        while(remainder != 0){
            remainder *= 10;
            long aa = remainder / d;
            oo.append(String.valueOf(aa));
            remainder = remainder - d * aa;
            if(remainder == 0){
                return output + oo.toString();
            }
            if(mem.containsKey(remainder)){
                break;
            }
            index++;
            mem.put(remainder, index);
        }
        oo.insert(mem.get(remainder), "(");
        oo.append(")");
        return output + oo.toString(); 
    } 
}
