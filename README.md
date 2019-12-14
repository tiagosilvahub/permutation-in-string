# permutation-in-string
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

https://leetcode.com/problems/permutation-in-string/description/

Example 1:
```
Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
```
Example 2:
```
Input: s1= "ab" s2 = "eidboaoo"
Output: False
 ```

Note:

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

Solution in O(n) :

Sliding window technique, use HashMap to keep and access state of window in constant time. Initialized the map with the frequency of each character. Keep track of the characters inside the window. When we have all characters, if the size of the window is the size of the target string, then we know for sure we have found the answer. Otherwise, there must be extra characters in the window that are not in the target string, so try to close the window by moving the left side. If we lose a needed character, then there was no answer in that window, so move the right pointer to keep moving the window. 

```
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
```
