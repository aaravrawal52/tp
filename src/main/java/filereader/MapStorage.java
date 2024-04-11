package filereader;

import map.BaseMap;
import map.FirstMap;
import map.MapGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static filereader.filepath.MapFilePath.BASE_MAP_PATH;
import static filereader.filepath.MapFilePath.MAP_PROPERTY_PATH;

public class MapStorage {

    public BaseMap readFile() throws FileNotFoundException, InterruptedException {
        try {
            Files.createDirectories(Paths.get("./data"));
        } catch (IOException e) {
            System.out.println("Fail to create data directory!\n" + e.getMessage());
        }

        File mapPicture = new File(BASE_MAP_PATH);
        File mapProperty = new File(MAP_PROPERTY_PATH);

        if (!mapPicture.exists() || !mapProperty.exists() || mapPicture.length() == 0 ||
                mapProperty.length() == 0) {
            try {
                Files.createFile(Paths.get(BASE_MAP_PATH));
            } catch (IOException e) {
                System.out.println("Fail to create map text!\n" + e.getMessage());
            }
            try {
                Files.createFile(Paths.get(MAP_PROPERTY_PATH));
            } catch (IOException e) {
                System.out.println("Fail to create map property text!\n" + e.getMessage());
            }
            System.out.print("\n\n\nNo local archives found or the map data is corrupted\n\n\n");
            for (int i = 0; i < 201; i++) {
                Thread.sleep(20);
                System.out.print("Generating maps |" + "#".repeat(i / 6) + " ".repeat(33 - i / 6)
                        + "|" + "%" + i / 2 + "\r");
            }
            System.out.println();
            BaseMap map = new FirstMap();
            MapGenerator.getInstance().generateMap(map);
            return map;
        }
        System.out.print("\n\n\nFind local archive\n\n\n");
        for (int i = 0; i < 201; i++) {
            Thread.sleep(20);
            System.out.print("Loading maps |" + "#".repeat(i / 6) + " ".repeat(33 - i / 6)
                    + "|" + "%" + i / 2 + "\r");
        }
        System.out.println();
        return getBaseMap(mapPicture, mapProperty);
    }

    private BaseMap getBaseMap(File mapPicture, File mapProperty) throws FileNotFoundException {
        Scanner fileContent1 = new Scanner(mapPicture);

        BaseMap map = new FirstMap();
        ArrayList<ArrayList<Character>> mapData = new ArrayList<>();
        while (fileContent1.hasNext()) {
            ArrayList<Character> chars = new ArrayList<>();
            String content = fileContent1.nextLine();
            for (char ch : content.toCharArray()) {
                chars.add(ch);
            }
            mapData.add(chars);
        }
        map.setMapData(mapData);
        fileContent1.close();
        Scanner fileContent2 = new Scanner(mapProperty);
        int round = 0;
        while (fileContent2.hasNext()) {
            int currentData = Integer.parseInt(fileContent2.nextLine());
            switch (round) {
            case 0:
                map.setWidth(currentData);
                break;
            case 1:
                map.setHeight(currentData);
                break;
            case 2:
                map.setPlayerX(currentData);
                break;
            case 3:
                map.setPlayerY(currentData);
                break;
            default:
            }
            round++;
        }
        fileContent2.close();
        return map;
    }

    public void saveMap(BaseMap map) throws IOException {
        FileWriter fileWriter1 = new FileWriter(MAP_PROPERTY_PATH);

        String width = String.valueOf(map.getWidth());
        String height = String.valueOf(map.getHeight());
        String playerX = String.valueOf(map.getPlayerX());
        String playerY = String.valueOf(map.getPlayerY());

        fileWriter1.write(width + System.lineSeparator());
        fileWriter1.write(height + System.lineSeparator());
        fileWriter1.write(playerX + System.lineSeparator());
        fileWriter1.write(playerY + System.lineSeparator());

        fileWriter1.close();
        FileWriter fileWriter2 = new FileWriter(BASE_MAP_PATH);
        ArrayList<ArrayList<Character>> mapData = map.getMapData();
        for (ArrayList<Character> ch : mapData) {
            StringBuilder temp = new StringBuilder();
            for (Character c : ch) {
                temp.append(c);
            }
            fileWriter2.write(temp + System.lineSeparator());
        }
        fileWriter2.close();
    }
}
