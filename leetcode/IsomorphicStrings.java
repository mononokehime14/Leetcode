public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        // 205
        /*
         * 没什么好说的 easy题
         */
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        for(int i = 0;i < s.length();i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if((map1.containsKey(sc) && map1.get(sc) != tc) || (map2.containsKey(tc) && map2.get(tc) != sc)) return false;
            map1.put(sc, tc);
            map2.put(tc, sc);
        }
        return true;
    }
}
