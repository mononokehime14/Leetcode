package slidingwindow;

public class MinimumWindowSubstring {
    /**
     * 最初的滑动窗口TLE了 发现自己实际上没有用到滑动窗口 因为没有窗口orz
     * 如果每次把区域内的string拿去和t比较 时间成本过高了
     * 所以要保持一个map 然后记录窗口内的string 不断的和新的char比较 保持一个正确的character的数量
    */
    public String minWindow(String s, String t) {
        int left = 0, tlen = t.length(), right = 0, slen = s.length();
        if(tlen > slen) return "";
        HashMap<Character, Integer> mem = new HashMap<>();
        for(char c: t.toCharArray()) {
            mem.put(c, mem.getOrDefault(c, 0) + 1);
        }
        int anw_left = 0, anw_right = right + 1, min = slen + 1;
        int valid = 0;
        while(right < slen) {
            char c = s.charAt(right);
            if(mem.containsKey(c)) {
                mem.put(c, mem.get(c) - 1);
                if(mem.get(c) >= 0) valid++;
            }
            right++;
            //System.out.println(s.substring(left, right));
            while(valid == tlen) {
                if(right - left < min) {
                    min = right - left;
                    anw_left = left;
                    anw_right = right;
                    //System.out.println(anw_left + " " + anw_right);
                    //System.out.println(s.substring(anw_left, anw_right));
                }
                char l = s.charAt(left);
                if(mem.containsKey(l)) {
                    mem.put(l, mem.get(l)+1);
                    if(mem.get(l) > 0) valid--;
                }
                left++;
            }
        }
        if(min == slen + 1) return "";
        return s.substring(anw_left, anw_right);
    }
    // public String minWindow(String s, String t) {
    //     int left = 0, tlen = t.length(), right = tlen - 1, slen = s.length();
    //     // if(tlen > slen) return "";
    //     int anw_left = 0, anw_right = right, min = slen + 1;
    //     while(right < slen) {
    //         right++;
    //         //System.out.println(s.substring(left, right));
    //         while(isMatch(s.substring(left, right), t)) {
    //             if(right - left < min) {
    //                 min = right - left;
    //                 anw_left = left;
    //                 anw_right = right;
    //                 //System.out.println(anw_left + " " + anw_right);
    //             }
    //             left++;
    //         }
    //     }
    //     if(min == slen + 1) return "";
    //     return s.substring(anw_left, anw_right);
    // }
    // private boolean isMatch(String a, String b) {
    //     HashMap<Character, Integer> mem = new HashMap<>();
    //     for(char c: a.toCharArray()) {
    //         if(mem.containsKey(c)) {
    //             mem.put(c, mem.get(c) + 1);
    //         }else{
    //             mem.put(c, 1);
    //         }
    //     }
    //     for(char c: b.toCharArray()) {
    //         if(!mem.containsKey(c)) return false;
    //         if(mem.get(c) < 1) return false;
    //         mem.put(c, mem.get(c) - 1);
    //     }
    //     return true;
    // }
}
