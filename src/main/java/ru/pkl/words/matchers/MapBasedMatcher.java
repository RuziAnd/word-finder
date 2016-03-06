package ru.pkl.words.matchers;

import ru.pkl.words.Matcher;

import java.util.HashMap;
import java.util.Map;

public class MapBasedMatcher extends AbstractMatcher implements Matcher {


    private Map<Character, Integer> letters;


    public MapBasedMatcher(String s) {
        super(s);
        letters = transform(s);
    }

    @Override
    public boolean match(String word) {
        if (word == null) {
            return false;
        }
        Map<Character, Integer> wordLetters = transform(word);
        for (Map.Entry<Character, Integer> wordEntry : wordLetters.entrySet()) {
            Integer lettersCnt = letters.get(wordEntry.getKey());
            if (lettersCnt == null ) {
                return false;
            }
            if (lettersCnt < wordEntry.getValue()) {
                return false;
            }
        }
        return true;

    }

    private Map<Character, Integer> transform(String s){
        Map<Character, Integer> result = new HashMap<>();
        if (s != null) {
            char[] chars = s.toLowerCase().toCharArray();
            for (char c : chars) {
                Integer cnt = result.get(c);
                if (cnt == null) {
                    cnt = new Integer(0);
                }
                cnt = ++cnt;
                result.put(c, cnt);
            }
        }
        return result;
    }
}
