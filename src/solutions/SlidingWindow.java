package src.solutions;

import java.util.HashMap;

public class SlidingWindow implements Solution {
    @Override
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character,Integer> charCounts = new HashMap<>();
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();

        int left = 0;
        int right = 0;

        if(s1Chars.length < 1 || s1Chars.length > s2Chars.length){
            return false;
        }

        for( char c : s1Chars ) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        int uniqueCharCount = charCounts.size();

        while(right < s2Chars.length) {
            char r = s2Chars[right];
            if(charCounts.containsKey(r)){
                charCounts.put(r, charCounts.get(r) - 1);
                if (charCounts.get(r) == 0) {
                    uniqueCharCount--;
                }
            }
            while(uniqueCharCount == 0){
                if( right-left == s1Chars.length - 1){
                    return true;
                }
                char l = s2Chars[left];
                if(charCounts.containsKey(l)){
                    charCounts.put(l, charCounts.get(l) + 1);
                    if (charCounts.get(l) > 0) {
                        uniqueCharCount++;
                    }
                }
                left++;
            }
            right++;
        }
        return false;
    }
}
