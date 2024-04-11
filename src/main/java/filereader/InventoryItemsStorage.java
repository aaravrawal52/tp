package filereader;

import inventoryitems.Consumable;
import inventoryitems.Item;
import inventoryitems.ShopItem;
import map.PlayerInventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static filereader.filepath.InventoryItemsPath.INVENTORY_ITEMS_PATH;
import static main.CalculaChroniclesOfTheAlgorithmicKingdom.PLAYER_INVENTORY;

public class InventoryItemsStorage {

    public void readFile(PlayerInventory playerInventory) throws FileNotFoundException {
        try {
            Files.createDirectories(Paths.get("./data"));
        } catch (IOException e) {
            System.out.println("Fail to create data directory!\n" + e.getMessage());
        }

        File inventoryItems = new File(INVENTORY_ITEMS_PATH);

        if (!inventoryItems.exists() || inventoryItems.length() == 0) {
            try {
                Files.createFile(Paths.get(INVENTORY_ITEMS_PATH));
            } catch (IOException e) {
                System.out.println("Fail to create inventory text!\n" + e.getMessage());
            }
            return;
        }

        Scanner fileContent = new Scanner(inventoryItems);
        while (fileContent.hasNext()) {
            String originalData = fileContent.nextLine();
            String[] data = originalData.split("\\|");
            Consumable item = new Consumable(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2],
                    data[3], Integer.parseInt(data[4]));
            item.setQuantity(Integer.parseInt(data[5]));
            PLAYER_INVENTORY.loadInventory(item);
        }
        fileContent.close();
    }

    public void saveFile(PlayerInventory playerInventory) throws IOException {
        ArrayList<Item> generalItems = playerInventory.getGeneralItems();
        new FileWriter(INVENTORY_ITEMS_PATH).close();
        FileWriter fileWriter = new FileWriter(INVENTORY_ITEMS_PATH);
        for (Item item : generalItems) {
            String currentItem = "";
            currentItem += item.getHealAmt() + "|";
            currentItem += item.getDamageAmpAmt() + "|";
            currentItem += item.getDescription() + "|";
            currentItem += item.getName() + "|";
            currentItem += item.getSellPrice() + "|";
            currentItem += item.getQuantity();
            fileWriter.write(currentItem + System.lineSeparator());
        }
        fileWriter.close();
    }
}
