import java.util.TreeSet;

/**
 * 804. 唯一摩尔斯密码词
 */

public class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        TreeSet<String> set = new TreeSet<>();

        for(String word : words) {
            StringBuilder ret = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                ret.append(codes[word.charAt(i) - 'a']);
            }

            set.add(ret.toString());
        }

        return set.size();
    }
}
