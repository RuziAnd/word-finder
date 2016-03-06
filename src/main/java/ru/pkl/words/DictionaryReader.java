package ru.pkl.words;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DictionaryReader implements Closeable {


    private String filename;
    private FileInputStream fstream;
    private BufferedReader br;

    public DictionaryReader(String fileName) {
        this.filename = fileName;
    }


    public String getNextWord() {
        try {
            return br.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try {
            fstream = new FileInputStream(filename);
            br = new BufferedReader(new InputStreamReader(fstream, "UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void close() throws IOException {
        try {
            if (br != null) {
                br.close();
                br = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (fstream != null) {
                fstream.close();
                fstream = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
