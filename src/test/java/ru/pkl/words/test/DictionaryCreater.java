package ru.pkl.words.test;

import java.io.*;
import java.util.StringTokenizer;

public class DictionaryCreater {

    private static final String[] IGNORABLE_CHARS = {",", ".", "-","\"", "'"};
    private static final int MIN_LENGTH = 3;

    public static void main(String[] args) throws IOException {
        try (
                FileInputStream fstream = new FileInputStream("D:\\PKL\\Dev\\java\\book.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

                FileOutputStream outputStream = new FileOutputStream("dict.txt");
                OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                PrintWriter writer = new PrintWriter(streamWriter);
        ) {

            String strLine;

            while ((strLine = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(strLine, " ");
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    for (String c :IGNORABLE_CHARS) {
                        token = token.replace(c ,"");
                    }
                    if (token.length() >= MIN_LENGTH) {
                        writer.println(token);
                    }
                }

            }
        }

    }
}
