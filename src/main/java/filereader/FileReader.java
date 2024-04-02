package filereader;

import java.io.*;
import java.util.ArrayList;
import java.io.File;
import java.io.*;
import java.util.ArrayList;

public class FileReader {
    private final String filePath;


    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<ArrayList<Character>> readEnemyDesign() throws IOException {
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found at: " + filePath);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            ArrayList<Character> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i += 1) {
                row.add(line.charAt(i));
            }
            map.add(row);
        }
        reader.close();
        inputStream.close();
        return map;
    }
    public ArrayList<ArrayList<Character>> readShopMapDesign() throws IOException {
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found at: " + filePath);
        }
        try (InputStream inputStream = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Trim leading and trailing whitespace
                ArrayList<Character> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i += 1) {
                    row.add(line.charAt(i));
                }
                map.add(row);
            }
        }
        return map;
    }
}
