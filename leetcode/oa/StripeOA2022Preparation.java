package oa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class StripeOA2022Preparation {
    // Permissions
    public static Set<String> permissions(String[] roles) {
        Set<String> output = new HashSet<>();
        if(roles.length == 0) return output;
        String[] readP = new String[]{"read_settings", "read_intent"};
        String[] writeP = new String[]{"write_settings", "create_intent", "confirm_intent"};
        String[] pamentS = new String[]{"update_dispute", "create_dispute", "read_dispute", "create_refund", "cancel_refund", "read_refund"};
        String[] products = new String[]{"payments", "banking", "identity", "climate", "capital"};
        Arrays.sort(roles);
        if(roles[0].equals("admin")) {
            for(String product: products) {
                if(product.equals("payments")){
                    for(String s: pamentS) output.add(product+"."+s);
                    for(String r: readP) output.add(product+"."+r);
                    for(String w: writeP) output.add(product+"."+w);  
                }else{    
                    for(String r: readP) output.add(product+"."+r);
                    for(String w: writeP) output.add(product+"."+w);      
                }
            }
            return output;
        }
        for(String role: roles) {
            if(role.equals("admin_readonly")) {
                for(String product: products) {
                    for(String r: readP) output.add(product+"."+r);
                }
            }else{
                if(role.equals("payments")){
                    for(String s: pamentS) output.add(role+"."+s);
                    for(String r: readP) output.add(role+"."+r);
                    for(String w: writeP) output.add(role+"."+w);    
                }else{
                    for(String r: readP) output.add(role+"."+r);
                    for(String w: writeP) output.add(role+"."+w);
                }              
            }
        }
        return output;
    } 

    public static String encodingPermissions(String[] roles) {
        if(roles.length == 0) return String.format("%31s", Integer.toBinaryString(0)).replace(' ', '0');;
        String[] products = new String[]{"payments", "banking", "identity", "climate", "capital"};
        HashMap<String, Integer> offsetMap = new HashMap<>();
        offsetMap.put("payments", 0);
        offsetMap.put("identity", 11);
        offsetMap.put("climate", 16);
        offsetMap.put("capital", 21);
        offsetMap.put("banking", 26);
        Arrays.sort(roles);
        if(roles[0].equals("admin")) return String.format("%31s", Integer.toBinaryString(Integer.MAX_VALUE)).replace(' ', '0');;
        int answer = 0;
        for(String role: roles) {
            if(role.equals("admin_readonly")) {
                for(String product: products) {
                    if(product.equals("payments")) { // 5 4 2
                        answer |= (1 << (offsetMap.get(product) + 2));
                        answer |= (1 << (offsetMap.get(product) + 3));
                        answer |= (1 << (offsetMap.get(product) + 4));
                        answer |= (1 << (offsetMap.get(product) + 5));
                    }else{
                        answer |= (1 << (offsetMap.get(product) + 2));
                        answer |= (1 << (offsetMap.get(product) + 1));
                    }   
                }
            }else{
                if(role.equals("payments")){
                    int offset = offsetMap.get(role);
                    for(int i = offset;i < offset + 11;i++) answer |= (1 << i); 
                }else{
                    int offset = offsetMap.get(role);
                    for(int i = offset;i < offset + 5;i++) {
                        System.out.println(1 << i);
                        answer |= (1 << i); 
                    }
                }              
            }
        }
        return String.format("%31s", Integer.toBinaryString(answer)).replace(' ', '0');
    } 

    //Payouts
    public static void partOne(String[] currencies, String[] charges){
        HashMap<String, Map<String,Double>> map = new HashMap<>();
        map.put("CHF", new HashMap<>());
        map.put("EUR", new HashMap<>());
        map.put("GBP", new HashMap<>());
        map.put("USD", new HashMap<>());
        map.get("CHF").put("USD", 0.97);
        map.get("CHF").put("EUR", 0.92);
        map.get("CHF").put("GBP", 0.78);
        map.get("EUR").put("USD", 1.05);
        map.get("EUR").put("CHF", 1.08);
        map.get("EUR").put("GBP", 0.85);
        map.get("GBP").put("USD", 1.24);
        map.get("GBP").put("EUR", 1.18);
        map.get("GBP").put("CHF", 1.28);
        map.get("USD").put("CHF", 1.03);
        map.get("USD").put("EUR", 0.95);
        map.get("USD").put("GBP", 0.81);
        String defaultCur = currencies[0];
        HashSet<String> accounts = new HashSet<>();
        TreeMap<String, Double> output = new TreeMap<>();
        for(String s: currencies) {
            accounts.add(s);
            output.put(s, 0.0);
        }
        for(int i = 0;i < charges.length;i++) {
            double amount = Double.parseDouble(charges[i].split(",")[1]);
            String currency = charges[i].split(",")[2];
            if(accounts.contains(currency)) {
                output.put(currency, output.get(currency) + amount * 0.98);
            }else{
                output.put(defaultCur, output.get(defaultCur) + amount * map.get(currency).get(defaultCur) * 0.98);
            }
        }
        for(Map.Entry<String, Double> e : output.entrySet()) System.out.println(e.getKey() + "," + (int)Math.round(e.getValue()));
    } 

    public static void partTwo(String[] currencies, String[] charges){
        HashMap<String, Map<String,Double>> map = new HashMap<>();
        map.put("CHF", new HashMap<>());
        map.put("EUR", new HashMap<>());
        map.put("GBP", new HashMap<>());
        map.put("USD", new HashMap<>());
        map.get("CHF").put("USD", 0.97);
        map.get("CHF").put("EUR", 0.92);
        map.get("CHF").put("GBP", 0.78);
        map.get("EUR").put("USD", 1.05);
        map.get("EUR").put("CHF", 1.08);
        map.get("EUR").put("GBP", 0.85);
        map.get("GBP").put("USD", 1.24);
        map.get("GBP").put("EUR", 1.18);
        map.get("GBP").put("CHF", 1.28);
        map.get("USD").put("CHF", 1.03);
        map.get("USD").put("EUR", 0.95);
        map.get("USD").put("GBP", 0.81);
        String defaultCur = currencies[0];
        HashSet<String> accounts = new HashSet<>();
        TreeMap<String, Double> output = new TreeMap<>();
        for(String s: currencies) {
            accounts.add(s);
            output.put(s, 0.0);
        }
        for(int i = 0;i < charges.length;i++) {
            double amount = Double.parseDouble(charges[i].split(",")[1]);
            String currency = charges[i].split(",")[2];
            if(accounts.contains(currency)) {
                output.put(currency, output.get(currency) + (int)Math.round(amount * 0.98));
            }else{
                output.put(defaultCur, output.get(defaultCur) + amount * map.get(currency).get(defaultCur) * 0.98);
            }
        }
        for(Map.Entry<String, Double> e : output.entrySet()) System.out.println(e.getKey() + "," + (int)Math.round(e.getValue()));
    }

    public static void partTwo(String[] currencies, String[] charges){
        HashMap<String, Map<String,Double>> map = new HashMap<>();
        map.put("CHF", new HashMap<>());
        map.put("EUR", new HashMap<>());
        map.put("GBP", new HashMap<>());
        map.put("USD", new HashMap<>());
        map.get("CHF").put("USD", 0.97);
        map.get("CHF").put("EUR", 0.92);
        map.get("CHF").put("GBP", 0.78);
        map.get("EUR").put("USD", 1.05);
        map.get("EUR").put("CHF", 1.08);
        map.get("EUR").put("GBP", 0.85);
        map.get("GBP").put("USD", 1.24);
        map.get("GBP").put("EUR", 1.18);
        map.get("GBP").put("CHF", 1.28);
        map.get("USD").put("CHF", 1.03);
        map.get("USD").put("EUR", 0.95);
        map.get("USD").put("GBP", 0.81);
        String defaultCur = currencies[0];
        HashSet<String> accounts = new HashSet<>();
        TreeMap<LocalDate, Double[]> output = new TreeMap<>();
        for(String s: currencies) {
            accounts.add(s);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(int i = 0;i < charges.length;i++) {
            String ctime = charges[i].split(",")[0];
            LocalDate curDate = LocalDate.parse(ctime, formatter); 
            double amount = Double.parseDouble(charges[i].split(",")[1]) * 0.98;
            String currency = charges[i].split(",")[2];
            if(!accounts.contains(currency)) {
                amount *= map.get(currency).get(defaultCur);
                currency = defaultCur;
            }
            switch(currency) {
                case "USD":
                    Double[] temp = output.getOrDefault(curDate, new Double[]{0.0, 0.0, 0.0, 0.0});
                    temp[0] += amount;
                    output.put(curDate, temp);
                    break;
                case "CHF":
                    Double[] temp3 = output.getOrDefault(curDate.plusDays(3), new Double[]{0.0, 0.0, 0.0, 0.0});
                    temp3[3] += amount;
                    output.put(curDate.plusDays(3), temp3);
                    break;
                case "EUR":
                    Double[] temp2 = output.getOrDefault(curDate.plusDays(2), new Double[]{0.0, 0.0, 0.0, 0.0});
                    temp2[2] += amount;
                    output.put(curDate.plusDays(2), temp2);
                    break;
                case "GBP":
                    Double[] temp1 = output.getOrDefault(curDate.plusDays(1), new Double[]{0.0, 0.0, 0.0, 0.0});
                    temp1[1] += amount;
                    output.put(curDate.plusDays(1), temp1);
                    break;
            }
        }
        for(Map.Entry<LocalDate, Double[]> e : output.entrySet()) {
            if(e.getValue()[3] > 0.0) System.out.println(e.getKey().format(formatter) + ",CHF," + (int)Math.round(e.getValue()[3]));
            if(e.getValue()[2] > 0.0) System.out.println(e.getKey().format(formatter) + ",EUR," + (int)Math.round(e.getValue()[2]));
            if(e.getValue()[1] > 0.0) System.out.println(e.getKey().format(formatter) + ",GBP," + (int)Math.round(e.getValue()[1]));
            if(e.getValue()[0] > 0.0) System.out.println(e.getKey().format(formatter) + ",USD," + (int)Math.round(e.getValue()[0]));
        }
    }
    
    //Atlas Company Name Check
    public static void nameCheck(String[] requests) {
        HashSet<String> mem = new HashSet<>();
        for(String request: requests) {
            String name = request.split("\\|")[0];
            String candidate = request.split("\\|")[1];
            if(checkValid(mem, candidate)) {
                System.out.println(name + "|Name Available");
            }else{
                System.out.println(name + "|Name Not Available");
            }
        }
    }
    private static boolean checkValid(HashSet<String> mem, String current) {
        current = current.toLowerCase();
        String[] parts = current.split("[&, ]");
        for(String s: parts) System.out.println(s);
        StringBuilder sb = new StringBuilder();
        if(!parts[0].equals("a") && !parts[0].equals("an") && !parts[0].equals("the")) sb.append(parts[0]);
        for(int i = 1;i < parts.length - 1;i++) {
            if(parts[i].length() == 0) continue;
            if(parts[i].equals("and")) continue;
            sb.append(parts[i]);
        }
        sb.append(parts[parts.length - 1].split(",")[0]);
        String output = sb.toString();
        if(mem.contains(output)) return false;
        mem.add(output);
        return true;
    }
}
