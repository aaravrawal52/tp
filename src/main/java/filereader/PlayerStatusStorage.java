package filereader;

import textbox.PlayerStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static filereader.filepath.PlayerStatusPath.PLAYER_STATUS_PATH;
import static main.CalculaChroniclesOfTheAlgorithmicKingdom.START_HEALTH;
import static main.CalculaChroniclesOfTheAlgorithmicKingdom.START_MONEY;
import static main.CalculaChroniclesOfTheAlgorithmicKingdom.START_EXP;
import static main.CalculaChroniclesOfTheAlgorithmicKingdom.START_DAMAGE;
import static main.CalculaChroniclesOfTheAlgorithmicKingdom.PLAYER_INVENTORY;

public class PlayerStatusStorage {
    public PlayerStatus readPlayerStatus() throws FileNotFoundException {
        try{
            Files.createDirectories(Paths.get("./data"));
        } catch (IOException e){
            System.out.println("Fail to create directory!\n" + e.getMessage());
        }

        File file = new File(PLAYER_STATUS_PATH);
        if(!file.exists() || file.length() == 0){
            try {
                Files.createFile(Paths.get(PLAYER_STATUS_PATH));
            } catch (IOException e) {
                System.out.println("Fail to create playerStatus File!\n" + e.getMessage());
            }
            return new PlayerStatus(START_HEALTH, START_MONEY, START_EXP, START_DAMAGE,
                    PLAYER_INVENTORY);
        }

        Scanner fileContent = new Scanner(file);
        int round = 0;
        int startHealth = -1;
        int startMoney = -1;
        int startExp = -1;
        int startDamage = -1;
        while(fileContent.hasNext()){
            int playerStatusData = Integer.parseInt(fileContent.nextLine());
            switch (round){
            case 0:
                startHealth = playerStatusData;
                break;
            case 1:
                startMoney = playerStatusData;
                break;
            case 2:
                startExp = playerStatusData;
                break;
            case 3:
                startDamage = playerStatusData;
                break;
            }
            round++;
        }
        return new PlayerStatus(startHealth, startMoney, startExp, startDamage, PLAYER_INVENTORY);
    }

    public void savePlayerStatus(PlayerStatus playerStatus) throws IOException {
        FileWriter fileWriter = new FileWriter(PLAYER_STATUS_PATH);

        String startHealth = String.valueOf(playerStatus.getPlayerHealth());
        String startMoney = String.valueOf(playerStatus.getPlayerMoney());
        String startExp = String.valueOf(playerStatus.getPlayerExp());
        String startDamage = String.valueOf(playerStatus.getPlayerDamage());

        fileWriter.write(startHealth + System.lineSeparator());
        fileWriter.write(startMoney + System.lineSeparator());
        fileWriter.write(startExp + System.lineSeparator());
        fileWriter.write(startDamage + System.lineSeparator());
        fileWriter.close();
    }
}
