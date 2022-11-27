package array;

public class AppendCharactersToStringToMakeSubseq {
    /* 2486
     * 无他 双指针罢了
     */
    public int appendCharacters(String s, String t) {
        int si = 0, ti = 0, m = s.length(), n = t.length();
        while(si < m && ti < n) {
            if(s.charAt(si) == t.charAt(ti)) {
                ti++;
            }
            si++;
        }
        return n - ti;
    }
}
