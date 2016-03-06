package ru.pkl.words;

import org.apache.commons.cli.*;
import ru.pkl.words.matchers.MapBasedMatcher;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static final String STRINF_TO_TEST_OPTION = "w";
    public static final String DICTIONARY_FILE_MAME_OPTION = "d";

    public static void main(String[] args) throws IOException, ParseException {

//        String stringToTest = "абракадабра";
//        String dictionaryFileName = "dict.txt";

        CommandLineParser parser = new DefaultParser();

        Options options = new Options();
        options.addOption(new Option(STRINF_TO_TEST_OPTION, true, "Text to test"));
        options.addOption(new Option(DICTIONARY_FILE_MAME_OPTION, true, "Dictionary file"));

        CommandLine line = parser.parse( options, args );

        String stringToTest = line.getOptionValue(STRINF_TO_TEST_OPTION, "");
        String dictionaryFileName = line.getOptionValue(DICTIONARY_FILE_MAME_OPTION);
        if (dictionaryFileName == null) {
            throw new IllegalArgumentException("No dictionary filename is specified");
        }





        Set<String> result = new HashSet<>();

        try (DictionaryReader reader = new DictionaryReader(dictionaryFileName)) {
            reader.start();

            Matcher matcher = new MapBasedMatcher(stringToTest);
                while (true) {
                    String word = reader.getNextWord();
                    if (word != null) {
                        if (matcher.match(word)) {
                            result.add(word);
                        }
                    } else {
                        break;
                    }
            }

            for (String s : result) {
                System.out.println(s);
            }
        }
    }


}
